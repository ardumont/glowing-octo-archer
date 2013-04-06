; Copyright 2013 Relevance, Inc.

; The use and distribution terms for this software are covered by the
; Eclipse Public License 1.0 (http://opensource.org/licenses/eclipse-1.0)
; which can be found in the file epl-v10.html at the root of this distribution.
;
; By using this software in any fashion, you are agreeing to be bound by
; the terms of this license.
;
; You must not remove this notice, or any other, from this software.

(ns curriculum-app.app
  (:require [domina :as d]
            [curriculum-app.cv :as cv]
            [clojure.string :as s]))

(defn render-identity
  []
  (let [{:keys [name
                first-name
                middle-name
                address
                city
                country
                birth
                emails
                phone]} (:identity cv/cv)]

    (d/set-text! (d/by-id "firstname-name")
                 (->> (interpose " " [first-name middle-name name])
                      (s/join "")))
    (d/add-class! (d/by-id "firstname-name") "title")
    (d/set-text! (d/by-id "address")
                 (->> (interpose " - " [address city country])
                      (s/join "")))
    (d/set-text! (d/by-id "birth") birth)
    (d/set-text! (d/by-id "email") (s/join " - " emails))
    (d/set-text! (d/by-id "phone") phone)))

(defn render-current-position
  []
  (let [{:keys [current] :as current-pos} (:jobs cv/cv)
        {:keys [as period]} (current-pos current)]
    (d/set-text! (d/by-id "as") as)
    (d/set-text! (d/by-id "current") (name current))
    (d/set-text! (d/by-id "period") period)))

(defn render-previous-positions
  []
  (let [previous-pos (get-in cv/cv [:jobs :previous-pos])
        positions (map (fn [p]
                         (let [{:keys [as period]} (get-in cv/cv [:jobs p])]
                           [as "at" (name p) "for" period]))
                       previous-pos)]
    (doseq [p positions]
      (d/append! (d/by-id "ppos") (str "<div>" (s/join " " p) "</div>")))
    ))

(defn ^:export main []
  (render-identity)
  (render-current-position)
  (render-previous-positions))
