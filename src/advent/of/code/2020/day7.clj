(ns advent.of.code.2020.day7
  (:require [clojure.string :as str]))

(defn parse-contained-bag [contained-bag]
  (let [bag-count (read-string contained-bag)
        bag-color (str/replace-first contained-bag #"\d+\s+" "")]
    [bag-count bag-color]))

(defn parse-contained-bags [contained-bags]
  (if (= (get contained-bags 0) "no other")
    '()
    (map #(parse-contained-bag %) contained-bags)))

(defn parse-bag [line]
  (let [[container contained-str] (str/split line #" bags contain ")
        contained (str/split contained-str #" bags?, | bags?\.")]
    [container (parse-contained-bags contained)]))

(def bags
  (into {} (map #(parse-bag %) (str/split-lines (slurp "day7.txt")))))

(prn bags)

(def count-part-1 1)
(println (str "Solution part 1: " count-part-1))
(assert (= count-part-1 6726))
