(ns com.clojurecloset.ui.home)

(defn category-item [{:keys [name]}]
  [:a
   {:href "#",
    :class
    "relative flex h-80 w-56 flex-col overflow-hidden rounded-lg p-6 hover:opacity-75 xl:w-auto"}
   [:span
    {:aria-hidden "true", :class "absolute inset-0"}
    [:img
     {:src
      "https://tailwindui.com/img/ecommerce-images/home-page-01-category-01.jpg",
      :alt "",
      :class "h-full w-full object-cover object-center"}]]
   [:span
    {:aria-hidden "true",
     :class
     "absolute inset-x-0 bottom-0 h-2/3 bg-gradient-to-t from-gray-800 opacity-50"}]
   [:span
    {:class
     "relative mt-auto text-center text-xl font-bold text-white"}
    name]])

(defn categories-row [categories]
  [:div {:class "mt-4 flow-root"}
   [:div {:class "-my-2"}
    [:div
     {:class
      "relative box-content h-80 overflow-x-auto py-2 xl:overflow-visible"}
     (into
      [:div
       {:class
        "absolute flex space-x-8 px-4 sm:px-6 lg:px-8 xl:relative xl:grid xl:grid-cols-5 xl:gap-x-8 xl:space-x-0 xl:px-0"}]
      (map category-item categories))]]])

(defn section-shop-by-category
  [type type-plural categories]
  [:section
   {:aria-lablelledby "category-heading"
    :class "pt-24 sm:pt-32 xl:mx-auto xl:max-w-7xl xl:px-8"}
   [:div
    {:class
     "px-4 sm:flex sm:items-center sm:justify-between sm:px-6 lg:px-8 xl:px-0"}
    [:h2
     {:id "category-heading",
      :class "text-2xl font-bold tracking-tight text-gray-900"}
     (str "Shop by " type)]
    [:a
     {:href "#",
      :class
      "hidden text-sm font-semibold text-indigo-600 hover:text-indigo-500 sm:block"}
     (str "Browse all " type-plural)
     [:span {:aria-hidden "true"} "→"]]]
   (categories-row categories)])

(def section-hero
  [:div {:class '["relative" "bg-gray-900"]}
   [:div
    {:aria-hidden "true", :class "absolute inset-0 overflow-hidden"}
    [:img
     {:src
      "https://tailwindui.com/img/ecommerce-images/home-page-01-hero-full-width.jpg",
      :alt "",
      :class "h-full w-full object-cover object-center"}]]
   [:div
    {:aria-hidden "true",
     :class "absolute inset-0 bg-gray-900 opacity-50"}]
   [:div
    {:class
     "relative mx-auto flex max-w-3xl flex-col items-center px-6 py-32 text-center sm:py-64 lg:px-0"}
    [:h1
     {:class "text-4xl font-bold tracking-tight text-white lg:text-6xl"}
     "New arrivals are here"]
    [:p
     {:class "mt-4 text-xl text-white"}
     "The new arrivals have, well, newly arrived. Check out the latest options from our summer small-batch release while they&#39;re still in stock."]
    [:a
     {:href "#",
      :class
      "mt-8 inline-block rounded-md border border-transparent bg-white px-8 py-3 text-base font-medium text-gray-900 hover:bg-gray-100"}
     "Shop New Arrivals"]]])

(def section-support-projects
  [:section
 {:aria-labelledby "comfort-heading",
  :class "mx-auto max-w-7xl px-4 py-24 sm:px-6 sm:py-32 lg:px-8"}
 [:div
  {:class "relative overflow-hidden rounded-lg"}
  [:div
   {:class "absolute inset-0"}
   [:img
    {:src
     "https://tailwindui.com/img/ecommerce-images/home-page-01-feature-section-02.jpg",
     :alt "",
     :class "h-full w-full object-cover object-center"}]]
  [:div
   {:class
    "relative bg-gray-900 bg-opacity-75 px-6 py-32 sm:px-12 sm:py-40 lg:px-16"}
   [:div
    {:class
     "relative mx-auto flex max-w-3xl flex-col items-center text-center"}
    [:h2
     {:id "comfort-heading",
      :class
      "text-3xl font-bold tracking-tight text-white sm:text-4xl"}
     "Simple productivity"]
    [:p
     {:class "mt-3 text-xl text-white"}
     "Endless tasks, limited hours, a single piece of paper. Not really a haiku, but we&#39;re doing our best here. No kanban boards, burndown charts, or tangled flowcharts with our Focus system. Just the undeniable urge to fill empty circles."]
    [:a
     {:href "#",
      :class
      "mt-8 block w-full rounded-md border border-transparent bg-white px-8 py-3 text-base font-medium text-gray-900 hover:bg-gray-100 sm:w-auto"}
     "Shop Focus"]]]]])

(comment
  (vec (map category-item [{:name "john"}])))
