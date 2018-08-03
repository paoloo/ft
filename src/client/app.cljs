(ns client.app
    (:require [ajax.core :refer [POST]]
              [dommy.core :as dommy :refer-macros [sel sel1]]))

(.log js/console "clojure scriptie loaded! yey!")

(defn error-handler
  [{:keys [status status-text]}]
  (.log js/console (str "ERROR: " status " " status-text)))

(defn handler
  [response]
  (-> (sel1 :.scrambleresult)
      (dommy/set-text!
       (str (clj->js (get response "scramble") )))))

(defn ^:export scramble [word1 word2]
  (POST "/scramble"
        {:params {:word1 word1 :word2 word2}
         :handler handler
         :error-handler error-handler
         :format :json
         :response-format :json}))

