(ns com.clojurecloset.ui.footer)

(def footer
  [:footer
   {:aria-labelledby "footer-heading", :class "bg-gray-900"}
   [:h2 {:id "footer-heading", :class "sr-only"} "Footer"]
   [:div
    {:class "mx-auto max-w-7xl px-4 sm:px-6 lg:px-8"}
    [:div
     {:class "border-t border-gray-800 py-10"}
     [:p
      {:class "text-sm text-gray-400"}
      "Copyright © 2024 Clojure Closet"]]]])
