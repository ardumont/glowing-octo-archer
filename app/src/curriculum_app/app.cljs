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

(defn render
  []
  (let [{:keys [name first-name middle-name address city country birth emails phone]} (get-in cv/cv [:identity])]
    (d/set-text! (d/by-id "firstname-name")
                 (->> (interpose " " [first-name middle-name name])
                      (s/join "")))
    (d/add-class! (d/by-id "firstname-name") "title")
    (d/set-text! (d/by-id "address")
                 (->> (interpose " - " [address city country])
                      (s/join "")))
    (d/set-text! (d/by-id "birth") birth)
    (d/set-text! (d/by-id "email") (s/join " - " emails))
    (d/set-text! (d/by-id "phone") phone)
    ))

(defn ^:export main []
  (render))
