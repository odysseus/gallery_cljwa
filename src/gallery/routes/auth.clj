(ns gallery.routes.auth
  (:require [hiccup.form :refer :all]
            [compojure.core :refer :all]
            [gallery.routes.home :refer :all]
            [gallery.views.layout :as layout]
            [noir.session :as session]
            [noir.response :as resp]
            [noir.validation :as valid]))

(defn valid? [username pass passcheck]
  (valid/rule (valid/has-value? username)
              [:username "username is required"])
  (valid/rule (valid/min-length? pass 6)
              [:pass "Password must be at least 6 characters"])
  (valid/rule (= pass passcheck)
              [:pass "Passwords do not match"])
  (not (valid/errors? :username :pass :passcheck)))

(defn error-item [[error]]
  [:div.error error])

(defn control [id label field]
  (list
    (valid/on-error id error-item)
    label
    [:br]
    field
    [:br]))

(defn registration-page [& [username]]
  (layout/base "Register"
    (form-to [:post "/register"]
             (control :username
                      (label "username" "Username:")
                      (text-field {:tabindex 1} "username" username))
             (control :pass
                      (label "pass" "Password:")
                      (password-field {:tabindex 2} "pass"))
             (control :passcheck
                      (label "passcheck" "Retype Password:")
                      (password-field {:tabindex 3} "passcheck"))
             (submit-button {:tabindex 4} "Create Account"))))

(defn handle-registration [username pass passcheck]
  (if (valid? username pass passcheck)
    (do (session/put! :user username)
        (resp/redirect "/"))
    (registration-page username)))

(defroutes auth-routes
  (GET "/register" []
       (registration-page))

  (POST "/register" [username pass passcheck]
        (handle-registration username pass passcheck)))
