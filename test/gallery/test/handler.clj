(ns gallery.test.handler
  (:use clojure.test
        ring.mock.request
        gallery.handler))

(deftest test-app
  (testing "Homepage presence and title"
    (let [response (app (request :get "/"))]
      (is (= (:status response) 200))
      (is (.contains (:body response) "Home"))))

  (testing "Registration page presence and title"
    (let [response (app (request :get "/register"))]
      (is (= (:status response) 200))
      (is (.contains (:body response) "Register"))))

  (testing "Non-existent route"
    (let [response (app (request :get "/invalid"))]
      (is (= (:status response) 404)))))
