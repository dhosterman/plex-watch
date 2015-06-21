(ns plex-watch.core-test
  (:require [clojure.test :refer :all]
            [plex-watch.core :refer :all]))

(deftest is_video?_test
  (testing "test.avi is a video"
    (is (= (is_video? "test.avi") true))
  (testing "test.txt is not a video"
    (is (= (is_video? "test.txt") false)))))
