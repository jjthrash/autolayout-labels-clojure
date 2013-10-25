(ns autolayout-labels.main
  (:use [clojure.tools.cli :only (cli)]
        [clojure.string :as string])
  (:gen-class))

(def MSV '("Bob" "Fred" "Sue" "George" "Henry" "Horatio"
           "Sally" "Sarah" "Rosie" "Chip" "Daisy"))

(defn strip-layout-addresses [string]
  (string/replace string #"NSLayoutConstraint:0x[0-9a-f]{7,8}" ""))

(defn build-replacement-map [vars string]
  (let [tmp (strip-layout-addresses string)]
    (let [tokens (re-seq #"0x[0-9a-f]{7,8}" tmp)]
      (first
        (reduce (fn [[h msv] match]
                  (if (contains? h match)
                    [h msv]
                    [(assoc h match (first msv))
                     (rest msv)]))
                [{} vars]
                tokens)))))

(defn apply-replacement-map [map string]
  (reduce (fn [t [k v]]
            (string/replace t k v))
          string
          map))

(defn foo
  "Friendlify autolayout error output"
  [string]
  (let [le-map (build-replacement-map MSV string)]
    (apply-replacement-map le-map string)))

(defn -main
  [& args]
  (println (foo (read))))
