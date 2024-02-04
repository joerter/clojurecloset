(ns com.clojurecloset.ui.category)

(def header
  [:div
   {:class "py-24 text-center"}
   [:h1
    {:class "text-4xl font-bold tracking-tight text-gray-900"}
    "New Arrivals"]
   [:p
    {:class "mx-auto mt-4 max-w-3xl text-base text-gray-500"}
    "Thoughtfully designed objects for the workspace, home, and travel."]])

(def products
  [:section
   {:aria-labelledby "products-heading", :class "mt-8"}
   [:h2 {:id "products-heading", :class "sr-only"} "Products"]
   [:div
    {:class
     "grid grid-cols-1 gap-x-6 gap-y-10 sm:grid-cols-2 lg:grid-cols-3 xl:gap-x-8"}
    [:a
     {:href "#", :class "group"}
     [:div
      {:class
       "aspect-h-1 aspect-w-1 w-full overflow-hidden rounded-lg sm:aspect-h-3 sm:aspect-w-2"}
      [:img
       {:src
        "https://tailwindui.com/img/ecommerce-images/category-page-01-image-card-01.jpg",
        :alt
        "Person using a pen to cross a task off a productivity paper card.",
        :class
        "h-full w-full object-cover object-center group-hover:opacity-75"}]]
     [:div
      {:class
       "mt-4 flex items-center justify-between text-base font-medium text-gray-900"}
      [:h3 "Focus Paper Refill"]
      [:p "$13"]]
     [:p
      {:class "mt-1 text-sm italic text-gray-500"}
      "3 sizes available"]]
    [:a
     {:href "#", :class "group"}
     [:div
      {:class
       "aspect-h-1 aspect-w-1 w-full overflow-hidden rounded-lg sm:aspect-h-3 sm:aspect-w-2"}
      [:img
       {:src
        "https://tailwindui.com/img/ecommerce-images/category-page-01-image-card-02.jpg",
        :alt "Paper card sitting upright in walnut card holder on desk.",
        :class
        "h-full w-full object-cover object-center group-hover:opacity-75"}]]
     [:div
      {:class
       "mt-4 flex items-center justify-between text-base font-medium text-gray-900"}
      [:h3 "Focus Card Holder"]
      [:p "$64"]]
     [:p {:class "mt-1 text-sm italic text-gray-500"} "Walnut"]]
    [:a
     {:href "#", :class "group"}
     [:div
      {:class
       "aspect-h-1 aspect-w-1 w-full overflow-hidden rounded-lg sm:aspect-h-3 sm:aspect-w-2"}
      [:img
       {:src
        "https://tailwindui.com/img/ecommerce-images/category-page-01-image-card-03.jpg",
        :alt
        "Textured gray felt pouch for paper cards with snap button flap and elastic pen holder loop.",
        :class
        "h-full w-full object-cover object-center group-hover:opacity-75"}]]
     [:div
      {:class
       "mt-4 flex items-center justify-between text-base font-medium text-gray-900"}
      [:h3 "Focus Carry Pouch"]
      [:p "$32"]]
     [:p {:class "mt-1 text-sm italic text-gray-500"} "Heather Gray"]]
    (comment "More products...")]])
