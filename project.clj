(defproject ft "0.1.0-TEST"
  :description "FT: a simple clojure+clojurescript test"
  :url "https://github.com/paoloo/ft"
  :min-lein-version "2.0.0"
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [compojure "1.5.1"]
                 [ring/ring-json "0.4.0"]
                 [ring/ring-defaults "0.2.1"]
                 [ring/ring-jetty-adapter "0.3.8"]
                 [org.clojure/clojurescript "1.7.122"]
                 [cljs-ajax "0.5.1"]
                 [prismatic/dommy "1.1.0"]]
  :plugins [[lein-ring "0.9.7"]
            [lein-cljsbuild "1.0.3"]
            [lein-figwheel "0.4.1"]
            [lein-pdo "0.1.1"]]
  :ring {:handler ft.handler/app}
  :aliases {"up" ["pdo" "cljsbuild" "auto" "ft," "ring" "server-headless"]}
  :cljsbuild {
              :builds [ { :id "ft"
                         :source-paths ["src/"]
                         :figwheel true
                         :compiler {:main "client.app"
                                    :asset-path "js/out"
                                    :output-to "resources/public/js/app.js"
                                    :output-dir "resources/public/js/out"}}]}

  :profiles{
  :dev {:dependencies [[javax.servlet/servlet-api "2.5"]
                        [org.clojure/data.json "0.2.6"]
                        [ring/ring-mock "0.3.0"]]}}
  :main ^{:skip-aot true} ft.handler)
