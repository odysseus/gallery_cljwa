(ns gallery.views.layout
  (:require [hiccup.page :refer [html5 include-css]]
            [hiccup.element :refer [link-to]]
            [noir.session :as session]))

(defn base [title & body]
  (html5
    [:head
     [:title title]
     (include-css "/css/screen.css")]
    [:body body]))

(defn common [title & content]
  (base title
        (if-let [user (session/get :user)]
          [:p user]
          (link-to "/register" "Register"))
        content))
