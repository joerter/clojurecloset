(ns com.clojurecloset.shopify
  (:require [clj-http.client :as http]
            [venia.core :as v]))

(defn get-products-query []
  {:query
   (v/graphql-query
    {:venia/queries
     [[:products {:first 10}
       [[:edges
         [[:node
           [:id
            :handle
            [:featuredImage [:id :url]]
            :descriptionHtml
            :productType
            :title]]]]]]]})})

(defn get-product-query [handle]
  {:query
   (v/graphql-query
    {:venia/queries
     [{:query/data
       [:product {:handle handle}
        [:id
         :title
         :handle
         :description
         :descriptionHtml
         [:variants {:first 20}
          [[:edges
            [[:node
              [:id :title :quantityAvailable [:price [:amount :currencyCode]]]]]]]]
         [:media {:first 10}
          [[:edges
            [[:node
              [:mediaContentType :alt [:fragment/mediaFieldsByType]]]]]]]]]}]
     :venia/fragments
     [{:fragment/name   "mediaFieldsByType"
       :fragment/type   :Media
       :fragment/fields :fragment/mediaImage}
      {:fragment/name "mediaImage"
       :fragment/type :MediaImage
       :fragment/fields [[:image [:url]]]}]})})

(get-product-query "test")

(defn get-products [{:keys [biff/secret shopify/base-url shopify/api-version] :as ctx}]
  (-> (http/post (str "https://" base-url "/api/" api-version "/graphql.json")
                 {:headers {:Content-Type "application/json"
                            :X-Shopify-Storefront-Access-Token (secret :shopify/access-token)}
                  :form-params (get-products-query)
                  :content-type :json
                  :as :auto})
      :body :data :products :edges))

(defn get-product [handle {:keys [biff/secret shopify/base-url shopify/api-version] :as ctx}]
  (-> (http/post (str "https://" base-url "/api/" api-version "/graphql.json")
                 {:headers {:Content-Type "application/json"
                            :X-Shopify-Storefront-Access-Token (secret :shopify/access-token)}
                  :form-params (get-product-query handle)
                  :content-type :json
                  :as :auto})
      :body :data :product))
