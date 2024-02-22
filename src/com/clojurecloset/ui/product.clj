(ns com.clojurecloset.ui.product)

(defn image-gallery [{:keys [media]}]
  (let [[firstImage & restImages] (:edges media)]
    [:div
     {:class "flex flex-col-reverse"}

     [:div
      {:class
       "mx-auto mt-6 hidden w-full max-w-2xl sm:block lg:max-w-none"}
      (into [:div
             {:class "grid grid-cols-4 gap-6",
              :aria-orientation "horizontal",
              :role "tablist"}]
            (map (fn [n]
                   [:button
                    {:id "tabs-2-tab-1",
                     :class
                     "relative flex h-24 cursor-pointer items-center justify-center rounded-md bg-white text-sm font-medium uppercase text-gray-900 hover:bg-gray-50 focus:outline-none focus:ring focus:ring-opacity-50 focus:ring-offset-4",
                     :aria-controls "tabs-2-panel-1",
                     :role "tab",
                     :type "button"}
                    [:span {:class "sr-only"} (-> n :node :image :altText)]
                    [:span
                     {:class "absolute inset-0 overflow-hidden rounded-md"}
                     [:img
                      {:src (-> n :node :image :url)
                       :alt (-> n :node :image :altText) ,
                       :class "h-full w-full object-cover object-center"}]]
                    [:span
                     {:class
                      "ring-transparent pointer-events-none absolute inset-0 rounded-md ring-2 ring-offset-2",
                      :aria-hidden "true"}]]) restImages))]
     [:div
      {:class "aspect-h-1 aspect-w-1 w-full"}
      [:div
       {:id "tabs-2-panel-1",
        :aria-labelledby "tabs-2-tab-1",
        :role "tabpanel",
        :tabindex "0"}
       [:img
        {:src
         (-> firstImage :node :image :url)
         :alt "Angled front view with bag zipped and handles upright.",
         :class "h-full w-full object-cover object-center sm:rounded-lg"}]]]]))

(defn product-info [{:keys [title descriptionHtml priceRange]}]
  [:div
   {:class "mt-10 px-4 sm:mt-16 sm:px-0 lg:mt-0"}
   [:h1
    {:class "text-3xl font-bold tracking-tight text-gray-900"}
    title]
   [:div
    {:class "mt-3"}
    [:h2 {:class "sr-only"} "Product information"]
    [:p {:class "text-3xl tracking-tight text-gray-900"} (str "$" (-> priceRange :maxVariantPrice :amount))]]
   [:div
    {:class "mt-6"}
    [:h3 {:class "sr-only"} "Description"]
    [:div
     {:class "space-y-6 text-base text-gray-700"}
     [:p {:dangerouslySetInnerHTML {:__html descriptionHtml}}]]]
   [:form
    {:class "mt-6"}
    [:div
     [:h3 {:class "text-sm text-gray-600"} "Color"]
     [:fieldset
      {:class "mt-2"}
      [:legend {:class "sr-only"} "Choose a color"]
      [:div
       {:class "flex items-center space-x-3"}
       [:label
        {:class
         "relative -m-0.5 flex cursor-pointer items-center justify-center rounded-full p-0.5 focus:outline-none ring-gray-700"}
        [:input
         {:type "radio",
          :name "color-choice",
          :value "Washed Black",
          :class "sr-only",
          :aria-labelledby "color-choice-0-label"}]
        [:span
         {:id "color-choice-0-label", :class "sr-only"}
         "Washed Black"]
        [:span
         {:aria-hidden "true",
          :class
          "h-8 w-8 bg-gray-700 rounded-full border border-black border-opacity-10"}]]
       [:label
        {:class
         "relative -m-0.5 flex cursor-pointer items-center justify-center rounded-full p-0.5 focus:outline-none ring-gray-400"}
        [:input
         {:type "radio",
          :name "color-choice",
          :value "White",
          :class "sr-only",
          :aria-labelledby "color-choice-1-label"}]
        [:span {:id "color-choice-1-label", :class "sr-only"} "White"]
        [:span
         {:aria-hidden "true",
          :class
          "h-8 w-8 bg-white rounded-full border border-black border-opacity-10"}]]
       (comment
         "Active and Checked: \"ring ring-offset-1\"\n                    Not Active and Checked: \"ring-2\"")
       [:label
        {:class
         "relative -m-0.5 flex cursor-pointer items-center justify-center rounded-full p-0.5 focus:outline-none ring-gray-500"}
        [:input
         {:type "radio",
          :name "color-choice",
          :value "Washed Gray",
          :class "sr-only",
          :aria-labelledby "color-choice-2-label"}]
        [:span
         {:id "color-choice-2-label", :class "sr-only"}
         "Washed Gray"]
        [:span
         {:aria-hidden "true",
          :class
          "h-8 w-8 bg-gray-500 rounded-full border border-black border-opacity-10"}]]]]]
    [:div
     {:class "mt-10 flex"}
     [:button
      {:type "submit",
       :class
       "flex max-w-xs flex-1 items-center justify-center rounded-md border border-transparent bg-indigo-600 px-8 py-3 text-base font-medium text-white hover:bg-indigo-700 focus:outline-none focus:ring-2 focus:ring-indigo-500 focus:ring-offset-2 focus:ring-offset-gray-50 sm:w-full"}
      "Add to bag"]
     [:button
      {:type "button",
       :class
       "ml-4 flex items-center justify-center rounded-md px-3 py-3 text-gray-400 hover:bg-gray-100 hover:text-gray-500"}
      [:svg
       {:class "h-6 w-6 flex-shrink-0",
        :fill "none",
        :viewBox "0 0 24 24",
        :stroke-width "1.5",
        :stroke "currentColor",
        :aria-hidden "true"}
       [:path
        {:stroke-linecap "round",
         :stroke-linejoin "round",
         :d
         "M21 8.25c0-2.485-2.099-4.5-4.688-4.5-1.935 0-3.597 1.126-4.312 2.733-.715-1.607-2.377-2.733-4.313-2.733C5.1 3.75 3 5.765 3 8.25c0 7.22 9 12 9 12s9-4.78 9-12z"}]]
      [:span {:class "sr-only"} "Add to favorites"]]]]
   [:section
    {:aria-labelledby "details-heading", :class "mt-12"}
    [:h2 {:id "details-heading", :class "sr-only"} "Additional details"]
    [:div
     {:class "divide-y divide-gray-200 border-t"}
     [:div
      [:h3
       (comment "Expand/collapse question button")
       [:button
        {:type "button",
         :class
         "group relative flex w-full items-center justify-between py-6 text-left",
         :aria-controls "disclosure-1",
         :aria-expanded "false"}
        (comment "Open: \"text-indigo-600\", Closed: \"text-gray-900\"")
        [:span {:class "text-gray-900 text-sm font-medium"} "Features"]
        [:span
         {:class "ml-6 flex items-center"}
         (comment "Open: \"hidden\", Closed: \"block\"")
         [:svg
          {:class
           "block h-6 w-6 text-gray-400 group-hover:text-gray-500",
           :fill "none",
           :viewBox "0 0 24 24",
           :stroke-width "1.5",
           :stroke "currentColor",
           :aria-hidden "true"}
          [:path
           {:stroke-linecap "round",
            :stroke-linejoin "round",
            :d "M12 4.5v15m7.5-7.5h-15"}]]
         (comment "Open: \"block\", Closed: \"hidden\"")
         [:svg
          {:class
           "hidden h-6 w-6 text-indigo-400 group-hover:text-indigo-500",
           :fill "none",
           :viewBox "0 0 24 24",
           :stroke-width "1.5",
           :stroke "currentColor",
           :aria-hidden "true"}
          [:path
           {:stroke-linecap "round",
            :stroke-linejoin "round",
            :d "M19.5 12h-15"}]]]]]
      [:div
       {:class "prose prose-sm pb-6", :id "disclosure-1"}
       [:ul
        {:role "list"}
        [:li "Multiple strap configurations"]
        [:li "Spacious interior with top zip"]
        [:li "Leather handle and tabs"]
        [:li "Interior dividers"]
        [:li "Stainless strap loops"]
        [:li "Double stitched construction"]
        [:li "Water-resistant"]]]]
     (comment "More sections...")]]])

(def details
  [:div
   {:class
    "mx-auto max-w-2xl px-4 py-24 sm:px-6 sm:py-32 lg:max-w-7xl lg:px-8"}
   (comment "Details section")
   [:section
    {:aria-labelledby "details-heading"}
    [:div
     {:class "flex flex-col items-center text-center"}
     [:h2
      {:id "details-heading",
       :class
       "text-3xl font-bold tracking-tight text-gray-900 sm:text-4xl"}
      "The Fine Details"]
     [:p
      {:class "mt-3 max-w-3xl text-lg text-gray-600"}
      "Our patented padded snack sleeve construction protects your favorite treats from getting smooshed during all-day adventures, long shifts at work, and tough travel schedules."]]
    [:div
     {:class "mt-16 grid grid-cols-1 gap-y-16 lg:grid-cols-2 lg:gap-x-8"}
     [:div
      [:div
       {:class "aspect-h-2 aspect-w-3 w-full overflow-hidden rounded-lg"}
       [:img
        {:src
         "https://tailwindui.com/img/ecommerce-images/product-page-04-detail-product-shot-01.jpg",
         :alt
         "Drawstring top with elastic loop closure and textured interior padding.",
         :class "h-full w-full object-cover object-center"}]]
      [:p
       {:class "mt-8 text-base text-gray-500"}
       "The 20L model has enough space for 370 candy bars, 6 cylinders of chips, 1,220 standard gumballs, or any combination of on-the-go treats that your heart desires. Yes, we did the math."]]
     [:div
      [:div
       {:class "aspect-h-2 aspect-w-3 w-full overflow-hidden rounded-lg"}
       [:img
        {:src
         "https://tailwindui.com/img/ecommerce-images/product-page-04-detail-product-shot-02.jpg",
         :alt "Front zipper pouch with included key ring.",
         :class "h-full w-full object-cover object-center"}]]
      [:p
       {:class "mt-8 text-base text-gray-500"}
       "Up your snack organization game with multiple compartment options. The quick-access stash pouch is ready for even the most unexpected snack attacks and sharing needs."]]]]
   (comment "Policies section")
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
