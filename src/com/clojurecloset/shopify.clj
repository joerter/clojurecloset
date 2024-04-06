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
              [:id :title :quantityAvailable]]]]]]
         [:media {:first 10}
          [[:edges
            [[:node
              [:mediaContentType :alt]]]]]]]]}]})})

(comment (get-product-query "test"))

(defn old-get-product-query [handle]
  {:query (str "query getProduct {
  product(handle:\"" handle "\") {
		id
        title
		handle
		priceRange {
			 maxVariantPrice {
				amount
				currencyCode
			}
			minVariantPrice {
				amount
				currencyCode
			}
		}
    description
    descriptionHtml
    media(first: 10) {
      edges {
        node {
          mediaContentType
          alt
          ...mediaFieldsByType
        }
      }
    }
	}
}

fragment mediaFieldsByType on Media {
  ... on ExternalVideo {
    id
    embeddedUrl
  }
  ... on MediaImage {
    image {
      id
      altText
      url
    }
  }
  ... on Model3d {
    sources {
      url
      mimeType
      format
      filesize
    }
  }
  ... on Video {
    sources {
      url
      mimeType
      format
      height
      width
    }
  }
}")})

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
