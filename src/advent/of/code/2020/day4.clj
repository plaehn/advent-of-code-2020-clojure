(ns advent.of.code.2020.day4
  (:require [clojure.string :as str]
            [common.text :as txt]))

(defn parse-fields [fields]
  (vec (str/split fields #"\:")))

(defn parse-passport [passport]
  (into {} (map #(parse-fields %) (str/split passport #"\s+"))))

(def passports
  (map #(parse-passport %) (txt/group-by-blank-lines (slurp "day4.txt"))))


(def field-validators {"byr" #(<= 1920 (read-string %) 2002)
                       "iyr" #(<= 2010 (read-string %) 2020)
                       "eyr" #(<= 2020 (read-string %) 2030)
                       "hgt" #(let [unit-start (- (count %) 2)
                                    unit (subs % unit-start)
                                    size-str (subs % 0 unit-start)
                                    size (if (empty? size-str)
                                           0
                                           (read-string size-str))]
                                (or
                                  (and (= unit "cm") (<= 150 size 193))
                                  (and (= unit "in") (<= 59 size 76))))
                       "hcl" #(re-matches #"\#[0-9a-f]{6}" %)
                       "ecl" #(contains? #{"amb" "blu" "brn" "gry" "grn" "hzl" "oth"} %)
                       "pid" #(re-matches #"[0-9]{9}" %)})

(defn contains-required-keys? [passport]
  (every? #(contains? passport %) (keys field-validators)))

(defn valid? [attr value]
  ((field-validators attr) value))

(defn values-are-valid? [passport]
  (every? #(valid? % (passport %)) (keys field-validators)))

(defn contains-required-keys-and-values-are-valid? [passport]
  (and (contains-required-keys? passport) (values-are-valid? passport)))

(def valid-passport-count-1
  (count (filter #(contains-required-keys? %) passports)))
(println (str "Solution part 1: " valid-passport-count-1))
(assert (= valid-passport-count-1 219))

(def valid-passport-count-2
  (count (filter #(contains-required-keys-and-values-are-valid? %) passports)))
(println (str "Solution part 2: " valid-passport-count-2))
(assert (= valid-passport-count-2 127))