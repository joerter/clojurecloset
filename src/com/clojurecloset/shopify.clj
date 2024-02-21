(ns com.clojurecloset.shopify
  (:require [clj-http.client :as http]))

(def get-products-query {:query
"query getProducts {
  products(first:10) {
		edges {
			node {
				id
				featuredImage {
					id
					url
				}
				descriptionHtml
				productType
				title
			}
		}
	}
}"})

(defn get-product-query [id]
{:query (str "query getProduct {
  product(id:\"gid://shopify/Product/" id "\") {
		id
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
    featuredImage {
      id
    }
    images(first:3) {
      edges {
        node {
          id
        }
      }
    }
	}
}")})

(get-product-query "34")

(defn get-products [{:keys [biff/secret shopify/base-url shopify/api-version] :as ctx}]
  (-> (http/post (str "https://" base-url "/api/" api-version "/graphql.json")
               {:headers {:Content-Type "application/json"
                          :X-Shopify-Storefront-Access-Token (secret :shopify/access-token)}
                :form-params get-products-query
                :content-type :json
                :as :auto})))

(defn get-product [id {:keys [biff/secret shopify/base-url shopify/api-version] :as ctx}]
  (-> (http/post (str "https://" base-url "/api/" api-version "/graphql.json")
               {:headers {:Content-Type "application/json"
                          :X-Shopify-Storefront-Access-Token (secret :shopify/access-token)}
                :form-params (get-product-query id)
                :content-type :json
                :as :auto})))
