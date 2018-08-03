(ns ft.handler
  (:require [compojure.core :refer :all]
            [compojure.route :as route]
            [ring.middleware.defaults :refer [wrap-defaults site-defaults]]
            [ring.middleware.json :as middleware]
            [ring.util.response :as response]
            [ft.utils :as ftu])
  (:use  [ring.adapter.jetty]))

(defroutes app-routes
  (GET "/"          [] (response/content-type (response/resource-response "index.html" {:root "public"}) "text/html") )
  (route/resources "/")
  (POST "/scramble" req (ftu/scramble? req))
  (route/not-found "Not Found"))

(def app
   (->
      (wrap-defaults app-routes (assoc-in site-defaults [:security :anti-forgery] false))
      (middleware/wrap-json-body {:keywords? true})
      middleware/wrap-json-response))

(defn -main []
   (let [port (Integer/parseInt (get (System/getenv) "PORT" "5000"))]
     (run-jetty app {:port port})))
