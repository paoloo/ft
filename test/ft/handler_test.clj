(ns ft.handler-test
  (:require [clojure.test :refer :all]
            [ring.mock.request :as mock]
            [clojure.data.json :as json]
            [ft.handler :refer :all]))

(defn post-json-map [req-type uri params]
  {:remote-addr "localhost"
   :headers {"host" "localhost"
             "content-type" "application/json"
             "accept" "application/json"}
   :server-port 80
   :content-type "application/json"
   :uri uri
   :server-name "localhost"
   :query-string nil
   :body (java.io.ByteArrayInputStream. (.getBytes (json/write-str params)))
   :scheme :http
   :request-method req-type})

(deftest test-app
  (testing "main route"
    (let [response (app (mock/request :get "/"))]
      (is (= (:status response) 200))))

  (testing "scrambler route without data"
    (let [response (app (mock/request :post "/scramble"))]
      (is (= (:status response) 400))))

  (testing "not-found route"
    (let [response (app (mock/request :get "/invalid"))]
      (is (= (:status response) 404)))))

(deftest teste-scramble-routes
  (testing "scrambler route with data that cannot scramble"
    (let [response (app (post-json-map :post "/scramble" {:word1 "asdfgh" :word2 "qwert"}))]
      (is (= (:status response) 200))
      (is (= (:body response) "{\"scramble\":false}" ))))

  (testing "scrambler route with data that can scramble"
    (let [response (app (post-json-map :post "/scramble" {:word1 "rekqodlw" :word2 "world"}))]
      (is (= (:status response) 200))
      (is (= (:body response) "{\"scramble\":true}" )))))
