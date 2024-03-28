(ns com.clojurecloset.ui.product
  (:require
   [com.clojurecloset.util :as util]
   [com.biffweb :as biff]))

(defn image-gallery [{:keys [media]}]
  (let [images (:edges media)]
    [:div
     {:class "flex flex-col-reverse"
      :x-data "productImages"}

     [:div
      {:class
       "mx-auto mt-6 hidden w-full max-w-2xl sm:block lg:max-w-none"}
      (into [:div
             {:class "grid grid-cols-4 gap-6",
              :aria-orientation "horizontal",
              :role "tablist"}]
            (map-indexed (fn [i n]
                           [:button
                            {:id (str "tabs-2-tab-" i)
                             :class
                             "relative flex h-24 cursor-pointer items-center justify-center rounded-md bg-white text-sm font-medium uppercase text-gray-900 hover:bg-gray-50 focus:outline-none focus:ring focus:ring-opacity-50 focus:ring-offset-4",
                             :aria-controls (str "tabs-2-panel-" i)
                             :role "tab",
                             :type "button"
                             "@click" (str "show(" i ")")}
                            [:span {:class "sr-only"} (-> n :node :image :altText)]
                            [:span
                             {:class "absolute inset-0 overflow-hidden rounded-md"}
                             [:img
                              {:src (-> n :node :image :url)
                               :alt (-> n :node :image :altText) ,
                               :class "h-full w-full object-cover object-center"}]]
                            [:span
                             {:class
                              "pointer-events-none absolute inset-0 rounded-md ring-2 ring-offset-2",
                              :aria-hidden "true"
                              :x-bind:class (str "isSelected(" i ") ? 'ring-indigo-500' : 'ring-transparent'")}]]) images))]
     (into
      [:div
       {:class "aspect-h-1 aspect-w-1 w-full"}]
      (map-indexed (fn [i n]
                     [:div
                      {:id (str "tabs-2-panel-" i)
                       :aria-labelledby (str "tabs-2-tab-" i)
                       :role "tabpanel",
                       :tabindex i
                       :x-show (str "images[" i "]")
                       :x-transition ""}
                      [:img
                       {:src
                        (-> n :node :image :url)
                        :alt (-> n :node :image :altText) ,
                        :class "h-full w-full object-cover object-center sm:rounded-lg"}]]) images))]))

(defn product-info [{:keys [id title descriptionHtml priceRange]}]
  [:div
   {:class "mt-10 px-4 sm:mt-16 sm:px-0 lg:mt-0"}
   [:h1
    {:class "text-3xl font-bold tracking-tight text-gray-900"}
    title]
   [:div
    {:class "mt-3"}
    [:h2 {:class "sr-only"} "Product information"]
    [:p {:class "text-3xl tracking-tight text-gray-900"} (-> priceRange :maxVariantPrice :amount read-string util/format-dollars)]]
   [:div
    {:class "mt-6"}
    [:h3 {:class "sr-only"} "Description"]
    [:div
     {:class "space-y-6 text-base text-gray-700"}
     [:p {:dangerouslySetInnerHTML {:__html descriptionHtml}}]]]
   (biff/form
    {:hidden {:variant-id "gid://shopify/ProductVariant/47922325324076"
              :product-id id}
     :method "POST"
     :action "/cart"}
    [:div
     {:class "mt-10 flex"}
     [:button
      {:type "submit",
       :class
       "flex max-w-xs flex-1 items-center justify-center rounded-md border border-transparent bg-indigo-600 px-8 py-3 text-base font-medium text-white hover:bg-indigo-700 focus:outline-none focus:ring-2 focus:ring-indigo-500 focus:ring-offset-2 focus:ring-offset-gray-50 sm:w-full"}
      "Add to cart"]])])

(def details
  [:div
   {:class
    "mx-auto max-w-2xl px-4 sm:px-6 lg:max-w-7xl lg:px-8"}
   [:section
    {:aria-labelledby "policy-heading", :class "mt-16 lg:mt-24"}
    [:h2 {:id "policy-heading", :class "sr-only"} "Our policies"]
    [:div
     {:class
      "grid grid-cols-1 gap-y-12 sm:grid-cols-2 sm:gap-x-6 lg:grid-cols-4 lg:gap-x-8"}
     [:div
      [:img
       {:src
        "https://tailwindui.com/img/ecommerce/icons/icon-delivery-light.svg",
        :alt "",
        :class "h-24 w-auto"}]
      [:h3
       {:class "mt-6 text-base font-medium text-gray-900"}
       "Free delivery all year long"]
      [:p
       {:class "mt-3 text-base text-gray-500"}
       "Name another place that offers year long free delivery? We’ll be waiting. Order now and you’ll get delivery absolutely free."]]
     [:div
      [:img
       {:src
        "https://tailwindui.com/img/ecommerce/icons/icon-chat-light.svg",
        :alt "",
        :class "h-24 w-auto"}]
      [:h3
       {:class "mt-6 text-base font-medium text-gray-900"}
       "24/7 Customer Support"]
      [:p
       {:class "mt-3 text-base text-gray-500"}
       "Or so we want you to believe. In reality our chat widget is powered by a naive series of if/else statements that churn out canned responses. Guaranteed to irritate."]]
     [:div
      [:img
       {:src
        "https://tailwindui.com/img/ecommerce/icons/icon-fast-checkout-light.svg",
        :alt "",
        :class "h-24 w-auto"}]
      [:h3
       {:class "mt-6 text-base font-medium text-gray-900"}
       "Fast Shopping Cart"]
      [:p
       {:class "mt-3 text-base text-gray-500"}
       "Look at the cart in that icon, there&#39;s never been a faster cart. What does this mean for the actual checkout experience? I don&#39;t know."]]
     [:div
      [:img
       {:src
        "https://tailwindui.com/img/ecommerce/icons/icon-gift-card-light.svg",
        :alt "",
        :class "h-24 w-auto"}]
      [:h3
       {:class "mt-6 text-base font-medium text-gray-900"}
       "Gift Cards"]
      [:p
       {:class "mt-3 text-base text-gray-500"}
       "We sell these hoping that you will buy them for your friends and they will never actually use it. Free money for us, it&#39;s great."]]]]])
