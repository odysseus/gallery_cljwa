(ns gallery.models.schema
  (:require [gallery.models.db :refer :all]
            [clojure.java.jdbc :as sql]))

(defn create-users-table []
  (sql/with-connection db
    (sql/create-table
      :users
      [:id "SERIAL PRIMARY KEY"]
      [:username "varchar(32) UNIQUE"]
      [:pass "varchar(100)"])))


