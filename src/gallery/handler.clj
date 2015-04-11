(ns gallery.handler
  (:require [compojure.core :refer [defroutes]]
            [compojure.route :as route]
            [compojure.handler :as handler]
            [noir.util.middleware :as noir-middleware]
            [gallery.routes.home :refer [home-routes]]
            [gallery.routes.auth :refer [auth-routes]]))

(defn init []
  (println "gallery is starting"))

(defn destroy []
  (println "gallery is shutting down"))

(defroutes app-routes
  (route/resources "/")
  (route/not-found "Not Found"))

(def app
  (noir-middleware/app-handler
    [auth-routes
     home-routes
     app-routes]))
