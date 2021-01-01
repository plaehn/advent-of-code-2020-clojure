(ns advent.of.code.2020.day4
  (:require [clojure.string :as str]))

(defn parse-fields [fields]
  (vec (str/split fields #"\:")))

(defn parse-passport [passport]
  (into {} (map #(parse-fields %) (str/split passport #"\s+"))))

(def passports
  (map #(parse-passport %) (str/split (slurp "day4.txt") #"\r?\n\r?\n")))


(def required-attrs '("byr" "iyr" "eyr" "hgt" "hcl" "ecl" "pid"))

(defn is-valid? [passport]
  (every? #(contains? passport %) required-attrs))

(def valid-passport-count
  (count (filter #(is-valid? %) passports)))

(println (str "Solution part 1: " valid-passport-count))
(assert (= valid-passport-count 219))