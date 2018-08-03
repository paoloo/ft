(ns ft.utils
  (:require [clojure.string :as cstr]
            [ft.core :as ftc]))

(defn output-json
  ([information status-code]
  {:status status-code
   :headers {"Content-Type" "application/json"}
   :body information })
  ([information]
  (output-json information 200)))


(defn scramble?
  [x]
  (if (and (get-in x [:body :word1]) (get-in x [:body :word2]))
    (output-json {:scramble (ftc/scramble? (get-in x [:body :word1]) (get-in x [:body :word2]))})
    (output-json {:error "A parameter is missing on the request." } 400)))

