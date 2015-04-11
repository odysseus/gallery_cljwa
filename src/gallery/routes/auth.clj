(ns gallery.routes.auth
  (:require [hiccup.form :refer :all]
            [compojure.core :refer :all]
            [gallery.routes.home :refer :all]
            [gallery.views.layout :as layout]
            [noir.session :as session]
            [noir.response :as resp]))

(defn registration-page [& [username]]
  (layout/common
    (form-to [:post "/register"]
             (label "username" "username")
             (text-field "username" username)
             [:br]
             (label "pass" "password")
             (password-field "pass")
             [:br]
             (label "passcheck" "retype password")
             (password-field "passcheck")
             [:br]
             (submit-button "create account"))))

(defn handle-registration [username pass passcheck]
  (session/put! :user username)
  (resp/redirect "/"))

(defroutes auth-routes
  (GET "/register" []
       (registration-page))

  (POST "/register" [username pass passcheck]
        (handle-registration username pass passcheck)))
