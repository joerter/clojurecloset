(ns com.clojurecloset.home
  (:require
   [com.biffweb :as biff]
   [com.clojurecloset.settings :as settings]
   [com.clojurecloset.ui :as ui]
   [com.clojurecloset.ui.category :as ui-category]
   [com.clojurecloset.ui.product :as ui-product]
   [com.clojurecloset.ui.home :as ui-home]
   [com.clojurecloset.shopify :as shopify]))

(def email-disabled-notice
  [:.text-sm.mt-3.bg-blue-100.rounded.p-2
   "Until you add API keys for Postmark and reCAPTCHA, we'll print your sign-up "
   "link to the console. See config.edn."])

(defn page-content [& content]
  [:.p-3.mx-auto
   [:main
    content]])

(defn home-page [{:keys [recaptcha/site-key params] :as ctx}]
  (let [products (-> (shopify/get-products ctx) :data :products :edges)]
    (ui/page
     (assoc ctx ::ui/recaptcha true)
     (page-content
      [:div {:class "mx-auto max-w-3xl px-4 sm:px-6 lg:max-w-7xl lg:px-8 pb-32"}
       ui-home/section-hero
       (ui-home/section-products products)]))))

(defn category-page [{:keys [recaptcha/site-key params] :as ctx}]
  (ui/page
   (assoc ctx ::ui/recaptcha true)
   (page-content
    [:div {:class "mx-auto max-w-3xl px-4 sm:px-6 lg:max-w-7xl lg:px-8"}
     ui-category/header
     ui-category/products])))

(defn product-page [{:keys [recaptcha/site-key params] :as ctx}]
  (ui/page
   (assoc ctx ::ui/recaptcha true)
   (page-content
    [:div {:class "mx-auto max-w-7xl sm:px-6 sm:pt-16 lg:px-8"}
     [:div {:class "mx-auto max-w-2xl lg:max-w-none"}
      [:div {:class "lg:grid lg:grid-cols-2 lg:items-start lg:gap-x-8"}
       ui-product/image-gallery
       ui-product/product-info]
      ui-product/details]])))

(defn link-sent [{:keys [params] :as ctx}]
  (ui/page
   ctx
   [:h2.text-xl.font-bold "Check your inbox"]
   [:p "We've sent a sign-in link to " [:span.font-bold (:email params)] "."]))

(defn verify-email-page [{:keys [params] :as ctx}]
  (ui/page
   ctx
   [:h2.text-2xl.font-bold (str "Sign up for " settings/app-name)]
   [:.h-3]
   (biff/form
    {:action "/auth/verify-link"
     :hidden {:token (:token params)}}
    [:div [:label {:for "email"}
           "It looks like you opened this link on a different device or browser than the one "
           "you signed up on. For verification, please enter the email you signed up with:"]]
    [:.h-3]
    [:.flex
     [:input#email {:name "email" :type "email"
                    :placeholder "Enter your email address"}]
     [:.w-3]
     [:button.btn {:type "submit"}
      "Sign in"]])
   (when-some [error (:error params)]
     [:.h-1]
     [:.text-sm.text-red-600
      (case error
        "incorrect-email" "Incorrect email address. Try again."
        "There was an error.")])))

(defn signin-page [{:keys [recaptcha/site-key params] :as ctx}]
  (ui/page
   (assoc ctx ::ui/recaptcha true)
   (biff/form
    {:action "/auth/send-code"
     :id "signin"
     :hidden {:on-error "/signin"}}
    (biff/recaptcha-callback "submitSignin" "signin")
    [:h2.text-2xl.font-bold "Sign in to " settings/app-name]
    [:.h-3]
    [:.flex
     [:input#email {:name "email"
                    :type "email"
                    :autocomplete "email"
                    :placeholder "Enter your email address"}]
     [:.w-3]
     [:button.btn.g-recaptcha
      (merge (when site-key
               {:data-sitekey site-key
                :data-callback "submitSignin"})
             {:type "submit"})
      "Sign in"]]
    (when-some [error (:error params)]
      [:<>
       [:.h-1]
       [:.text-sm.text-red-600
        (case error
          "recaptcha" (str "You failed the recaptcha test. Try again, "
                           "and make sure you aren't blocking scripts from Google.")
          "invalid-email" "Invalid email. Try again with a different address."
          "send-failed" (str "We weren't able to send an email to that address. "
                             "If the problem persists, try another address.")
          "invalid-link" "Invalid or expired link. Sign in to get a new link."
          "not-signed-in" "You must be signed in to view that page."
          "There was an error.")]])
    [:.h-1]
    [:.text-sm "Don't have an account yet? " [:a.link {:href "/"} "Sign up"] "."]
    [:.h-3]
    biff/recaptcha-disclosure
    email-disabled-notice)))

(defn enter-code-page [{:keys [recaptcha/site-key params] :as ctx}]
  (ui/page
   (assoc ctx ::ui/recaptcha true)
   (biff/form
    {:action "/auth/verify-code"
     :id "code-form"
     :hidden {:email (:email params)}}
    (biff/recaptcha-callback "submitCode" "code-form")
    [:div [:label {:for "code"} "Enter the 6-digit code that we sent to "
           [:span.font-bold (:email params)]]]
    [:.h-1]
    [:.flex
     [:input#code {:name "code" :type "text"}]
     [:.w-3]
     [:button.btn.g-recaptcha
      (merge (when site-key
               {:data-sitekey site-key
                :data-callback "submitCode"})
             {:type "submit"})
      "Sign in"]])
   (when-some [error (:error params)]
     [:.h-1]
     [:.text-sm.text-red-600
      (case error
        "invalid-code" "Invalid code."
        "There was an error.")])
   [:.h-3]
   (biff/form
    {:action "/auth/send-code"
     :id "signin"
     :hidden {:email (:email params)
              :on-error "/signin"}}
    (biff/recaptcha-callback "submitSignin" "signin")
    [:button.link.g-recaptcha
     (merge (when site-key
              {:data-sitekey site-key
               :data-callback "submitSignin"})
            {:type "submit"})
     "Send another code"])))

(def module
  {:routes [["/"                   {:get home-page}]
            ["/category/:category" {:get category-page}]
            ["/products/:product" {:get product-page}]
            ["/link-sent"          {:get link-sent}]
            ["/verify-link"        {:get verify-email-page}]
            ["/signin"             {:get signin-page}]
            ["/verify-code"        {:get enter-code-page}]]})
