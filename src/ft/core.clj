(ns ft.core
  (:require [clojure.string :as cstr]))

(defn match-and-cut
  [current-char word status]
    {:word (cstr/replace-first word current-char "")
     :status (conj status (contains? (set (cstr/split word #"")) current-char))})

(defn rearrange?
  [first-word details]
  (if-not (nil? first-word)
        (rearrange? (next first-word)
                    (match-and-cut (first first-word) (:word details) (:status details [])))
        (:status details)))

(defn scramble?
  [str1 str2]
  (every?
    true?
    (rearrange? (cstr/split str2 #"") {:word str1 :status []})))
