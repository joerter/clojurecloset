(ns com.clojurecloset
  (:require [com.biffweb :as biff]
            [com.clojurecloset.email :as email]
            [com.clojurecloset.app :as app]
            [com.clojurecloset.home :as home]
            [com.clojurecloset.middleware :as mid]
            [com.clojurecloset.ui :as ui]
            [com.clojurecloset.worker :as worker]
            [com.clojurecloset.schema :as schema]
            [clojure.test :as test]
            [clojure.tools.logging :as log]
            [clojure.tools.namespace.repl :as tn-repl]
            [malli.core :as malc]
            [malli.registry :as malr]
            [nrepl.cmdline :as nrepl-cmd])
  (:gen-class))

(def plugins
  [app/plugin
   (biff/authentication-plugin {})
   home/plugin
   schema/plugin
   worker/plugin])

(def routes [["" {:middleware [mid/wrap-site-defaults]}
              (keep :routes plugins)]
             ["" {:middleware [mid/wrap-api-defaults]}
              (keep :api-routes plugins)]])

(def handler (-> (biff/reitit-handler {:routes routes})
                 mid/wrap-base-defaults))

(def static-pages (apply biff/safe-merge (map :static plugins)))

(defn generate-assets! [ctx]
  (biff/export-rum static-pages "target/resources/public")
  (biff/delete-old-files {:dir "target/resources/public"
                          :exts [".html"]}))

(defn on-save [ctx]
  (biff/add-libs)
  (biff/eval-files! ctx)
  (generate-assets! ctx)
  (biff/catchall (require 'com.clojurecloset-test))
  (test/run-all-tests #"com.clojurecloset.*-test"))

(def malli-opts
  {:registry (malr/composite-registry
              malc/default-registry
              (apply biff/safe-merge
                     (keep :schema plugins)))})

(def initial-system
  {:biff/plugins #'plugins
   :biff/send-email #'email/send-email
   :biff/handler #'handler
   :biff/malli-opts #'malli-opts
   :biff.beholder/on-save #'on-save
   :biff.middleware/on-error #'ui/on-error
   :biff.xtdb/tx-fns biff/tx-fns
   :com.clojurecloset/chat-clients (atom #{})})

(defonce system (atom {}))

(def components
  [biff/use-aero-config
   biff/use-xt
   biff/use-queues
   biff/use-tx-listener
   biff/use-jetty
   biff/use-chime
   biff/use-beholder])

(defn start []
  (let [new-system (reduce (fn [system component]
                             (log/info "starting:" (str component))
                             (component system))
                           initial-system
                           components)]
    (reset! system new-system)
    (generate-assets! new-system)
    (log/info "System started.")
    (log/info "Go to" (:biff/base-url new-system))
    new-system))

(defn -main []
  (let [{:keys [biff.nrepl/args]} (start)]
    (apply nrepl-cmd/-main args)))

(defn refresh []
  (doseq [f (:biff/stop @system)]
    (log/info "stopping:" (str f))
    (f))
  (tn-repl/refresh :after `start))
