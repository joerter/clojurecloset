(ns com.clojurecloset.ui.footer)

(def footer
  [:footer
   {:aria-labelledby "footer-heading", :class "bg-gray-900"}
   [:h2 {:id "footer-heading", :class "sr-only"} "Footer"]
   [:div
    {:class "mx-auto max-w-7xl px-4 sm:px-6 lg:px-8"}
    [:div
     {:class "py-20 xl:grid xl:grid-cols-3 xl:gap-8"}
     [:div
      {:class "grid grid-cols-2 gap-8 xl:col-span-2"}
      [:div
       {:class "space-y-12 md:grid md:grid-cols-2 md:gap-8 md:space-y-0"}
       [:div
        [:h3 {:class "text-sm font-medium text-white"} "Shop"]
        [:ul
         {:role "list", :class "mt-6 space-y-6"}
         [:li
          {:class "text-sm"}
          [:a
           {:href "#", :class "text-gray-300 hover:text-white"}
           "Stickers"]]
         [:li
          {:class "text-sm"}
          [:a
           {:href "#", :class "text-gray-300 hover:text-white"}
           "T-Shirts"]]
         [:li
          {:class "text-sm"}
          [:a
           {:href "#", :class "text-gray-300 hover:text-white"}
           "Accessories"]]]]]]]
    [:div
     {:class "border-t border-gray-800 py-10"}
     [:p
      {:class "text-sm text-gray-400"}
      "Copyright © 2024 Clojure Closet"]]]])
