(ns com.clojurecloset.ui.header)

(def header
  [:header
   {:class "relative z-10 bg-gray-900"}
   [:nav
    {:aria-label "Top"}
    [:div
     {:class "bg-gray-900"}
     [:div
      {:class
       "mx-auto flex h-10 max-w-7xl items-center justify-between px-4 sm:px-6 lg:px-8"}
      [:form
       [:div
        [:label {:for "desktop-currency", :class "sr-only"} "Currency"]
        [:div
         {:class
          "group relative -ml-2 rounded-md border-transparent bg-gray-900 focus-within:ring-2 focus-within:ring-white"}
         [:select
          {:id "desktop-currency",
           :name "currency",
           :class
           "flex items-center rounded-md border-transparent bg-gray-900 bg-none py-0.5 pl-2 pr-5 text-sm font-medium text-white focus:border-transparent focus:outline-none focus:ring-0 group-hover:text-gray-100"}
          [:option "CAD"]
          [:option "USD"]
          [:option "AUD"]
          [:option "EUR"]
          [:option "GBP"]]
         [:div
          {:class
           "pointer-events-none absolute inset-y-0 right-0 flex items-center"}
          [:svg
           {:class "h-5 w-5 text-gray-300",
            :viewBox "0 0 20 20",
            :fill "currentColor",
            :aria-hidden "true"}
           [:path
            {:fill-rule "evenodd",
             :d
             "M5.23 7.21a.75.75 0 011.06.02L10 11.168l3.71-3.938a.75.75 0 111.08 1.04l-4.25 4.5a.75.75 0 01-1.08 0l-4.25-4.5a.75.75 0 01.02-1.06z",
             :clip-rule "evenodd"}]]]]]]
      [:div
       {:class "flex items-center space-x-6"}
       [:a
        {:href "#",
         :class "text-sm font-medium text-white hover:text-gray-100"}
        "Sign in"]
       [:a
        {:href "#",
         :class "text-sm font-medium text-white hover:text-gray-100"}
        "Create an account"]]]]
    [:div
     {:class "bg-white bg-opacity-10 backdrop-blur-md backdrop-filter"}
     [:div
      {:class "mx-auto max-w-7xl px-4 sm:px-6 lg:px-8"}
      [:div
       [:div
        {:class "flex h-16 items-center justify-between"}
        [:div
         {:class "hidden lg:flex lg:flex-1 lg:items-center"}
         [:a
          {:href "#"}
          [:span {:class "sr-only"} "Your Company"]
          [:img
           {:class "h-8 w-auto",
            :src "https://tailwindui.com/img/logos/mark.svg?color=white",
            :alt ""}]]]
        [:div
         {:class "flex flex-1 items-center lg:hidden"}
         [:button
          {:type "button", :class "-ml-2 p-2 text-white"}
          [:span {:class "sr-only"} "Open menu"]
          [:svg
           {:class "h-6 w-6",
            :fill "none",
            :viewBox "0 0 24 24",
            :stroke-width "1.5",
            :stroke "currentColor",
            :aria-hidden "true"}
           [:path
            {:stroke-linecap "round",
             :stroke-linejoin "round",
             :d "M3.75 6.75h16.5M3.75 12h16.5m-16.5 5.25h16.5"}]]]]

        [:div
         {:class "flex flex-1 items-center justify-end"}
         [:div
          {:class "flex items-center lg:ml-8"}
          [:a
           {:href "#", :class "p-2 text-white lg:hidden"}
           [:span {:class "sr-only"} "Help"]
           [:svg
            {:class "h-6 w-6",
             :fill "none",
             :viewBox "0 0 24 24",
             :stroke-width "1.5",
             :stroke "currentColor",
             :aria-hidden "true"}
            [:path
             {:stroke-linecap "round",
              :stroke-linejoin "round",
              :d
              "M9.879 7.519c1.171-1.025 3.071-1.025 4.242 0 1.172 1.025 1.172 2.687 0 3.712-.203.179-.43.326-.67.442-.745.361-1.45.999-1.45 1.827v.75M21 12a9 9 0 11-18 0 9 9 0 0118 0zm-9 5.25h.008v.008H12v-.008z"}]]]
          [:div
           {:class "ml-4 flow-root lg:ml-8"}
           [:a
            {:href "#", :class "group -m-2 flex items-center p-2"}
            [:svg
             {:class "h-6 w-6 flex-shrink-0 text-white",
              :fill "none",
              :viewBox "0 0 24 24",
              :stroke-width "1.5",
              :stroke "currentColor",
              :aria-hidden "true"}
             [:path
              {:stroke-linecap "round",
               :stroke-linejoin "round",
               :d
               "M15.75 10.5V6a3.75 3.75 0 10-7.5 0v4.5m11.356-1.993l1.263 12c.07.665-.45 1.243-1.119 1.243H4.25a1.125 1.125 0 01-1.12-1.243l1.264-12A1.125 1.125 0 015.513 7.5h12.974c.576 0 1.059.435 1.119 1.007zM8.625 10.5a.375.375 0 11-.75 0 .375.375 0 01.75 0zm7.5 0a.375.375 0 11-.75 0 .375.375 0 01.75 0z"}]]
            [:span {:class "ml-2 text-sm font-medium text-white"} "0"]
            [:span {:class "sr-only"} "items in cart, view bag"]]]]]]]]]]])
