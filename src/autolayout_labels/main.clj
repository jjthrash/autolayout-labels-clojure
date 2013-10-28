(ns autolayout-labels.main
  (:use [clojure.tools.cli :only (cli)])
  (:require [clojure.string :as s]
            [clojure.java.io :as io])
  (:gen-class))

(def MSV ["Bob" "Fred" "Sue" "George" "Henry" "Horatio"
           "Sally" "Sarah" "Rosie" "Chip" "Daisy"])

(def ADDR_REGEX_STR "0x[0-9a-f]{7,}")

(defn strip-layout-addresses
  "Strip out NSLayoutConstraint addresses since we don't want to name them"
  [string]
  (s/replace string
             (re-pattern (format "NS(AutoresizingMask)?LayoutConstraint:%s" ADDR_REGEX_STR))
             ""))

(defn build-map-from-tokens
  "Build a map of address -> friendly name given a list of names and addresses"
  [vars tokens]
  (first
    (reduce (fn [[h msv] match]
              (if (contains? h match)
                [h msv]
                (let [new-map (assoc h match (first msv))
                      new-msv (rest msv)]
                  [new-map new-msv])))
            [{} vars]
            tokens)))

(defn build-replacement-map
  "Create a map of address -> friendly name from a list of names and a string containing addresses"
  [vars string]
  (->> (strip-layout-addresses string)
       (re-seq (re-pattern ADDR_REGEX_STR))
       (build-map-from-tokens vars)))

(defn apply-replacement-map
  "Use a map of address -> friendly name to replace all the addresses in the given string"
  [replacement-map string]
  (reduce-kv s/replace
             string
             replacement-map))

(defn give-names-to-addresses
  "Give addresses like 0x1234abcd and 0x1234abc friendly names to help with debugging"
  [string]
  (-> (build-replacement-map MSV string)
      (apply-replacement-map string)))

(defn interact
  "Like Haskell's"
  [f]
  (let [lines (line-seq (io/reader *in*))]
    (dorun
      (map (comp println f)
           lines))))

(defn -main
  [& args]
  (interact give-names-to-addresses))
