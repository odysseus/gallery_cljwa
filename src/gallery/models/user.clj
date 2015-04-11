(ns gallery.models.user
  (:require [gallery.models.db :refer :all]))

(defn create-users-table []
  (sql/with-connection db
    (sql/create-table
      :users
      [:id "SERIAL PRIMARY KEY"]
      [:username "varchar(32) UNIQUE"]
      [:pass "varchar(100)"])))

(defn create-user [user]
  (sql/with-connection db
    (sql/insert-record :users user)))
