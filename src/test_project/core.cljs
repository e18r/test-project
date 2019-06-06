(ns test-project.core
  (:require-macros [cljs.core.async.macros :refer [go]])
  (:require [reagent.core :as reagent :refer [atom]]
            [unfurl.api :as uf]
            [cljs.core.async :refer [<!]]))

(enable-console-print!)

(println "This text is printed from src/test-project/core.cljs. Go ahead and edit it and see reloading in action.")

;; define your app data so that it doesn't get over-written on reload

(defonce app-state (atom {:text "Hello world!"}))


(defn hello-world [text]
  [:div
   [:h1 (:text @app-state)]
   [:h3 text]])

(go (let [response (<! (uf/unfurl "http://localhost:8888/facebook"))
          text (:title response)]
      (reagent/render-component [hello-world text]
                                (. js/document (getElementById "app")))))

(defn on-js-reload []
  ;; optionally touch your app-state to force rerendering depending on
  ;; your application
  ;; (swap! app-state update-in [:__figwheel_counter] inc)
)
