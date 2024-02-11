(ns com.clojurecloset.shopify
  (:require [clj-http.client :as client]))

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

(defn get-products [{:keys [biff/secret shopify/base-url shopify/api-version] :as ctx}]
  (client/post (str "https://" base-url "/api/" api-version "/graphql.json")
               {:headers {:Content-Type "application/json"
                          :X-Shopify-Storefront-Access-Token (secret :shopify/access-token)}
                :form-params get-products-query
                :content-type :json
                :as :auto}))
