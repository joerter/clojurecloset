(ns com.clojurecloset.util
  (:import java.text.NumberFormat))

(defn format-dollars [amount]
  {:pre [(number? amount)]}
  (let [format (NumberFormat/getCurrencyInstance java.util.Locale/US)]
    (.format format amount)))

(comment (format-dollars "10"))
