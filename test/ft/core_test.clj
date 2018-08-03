(ns ft.core-test
  (:require [clojure.test :refer :all]
            [ft.core :refer :all]))

(deftest core-implementation
  (testing "scramble correct value"
    (is (= (scramble? "rekqodlw" "world") true))
    (is (= (scramble? "cedewaraaossoqqyt" "codewars") true)))

  (testing "scramble incorrect value"
    (is (= (scramble? "katas" "steak") false))
    (is (= (scramble? "qwertyui" "asdfg") false)))

  (testing "scramble missing multiple character"
    (is (= (scramble? "abcec" "cabeca") false))))
