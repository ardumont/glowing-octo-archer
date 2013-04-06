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
    (d/set-text! (d/by-id "cpos") (str as " at " (name current) " since " period))))

(defn render-previous-positions
  []
  (let [previous-pos (get-in cv/cv [:jobs :previous-pos])
        positions (map (fn [p]
                         (let [{:keys [as period]} (get-in cv/cv [:jobs p])]
                           [as "at" (name p) "for" period]))
                       previous-pos)]
    (doseq [p positions]
      (d/append! (d/by-id "ppos") (str "<div>" (s/join " " p) "</div>")))))

(defn render-formations
  []
  (let [form (:formations cv/cv)
        period (:period form)
        {:keys [title college]} (:master form)
        formation [title "at" college "for" period]]
    (d/set-text! (d/by-id "formation") (s/join " " formation))))

(defn render-hobbies
  []
  (let [hobbies (:hobbies cv/cv)]
    (d/set-text! (d/by-id "hobbies") (s/join ", " hobbies))))

(defn render-misc
  []
  (let [misc (:misc cv/cv)]
    (d/set-text! (d/by-id "misc") (s/join ", " misc))))

(defn render-skills
  []
  (let [skills (-> cv/cv :skills)
        skills-keys (keys skills)
        all-skills (for [k skills-keys]
                     [(name k) (skills k)])]
    (doseq [[s sks] all-skills]
      (d/append! (d/by-id "skills") (str "<div>" s ": " (s/join ", " sks) "</div>")))))

(defn render-profiles
  []
  (let [profiles (-> cv/cv :profiles)
        profiles-keys (keys profiles)
        all-profiles (for [k profiles-keys]
                     [(name k) (profiles k)])]
    (doseq [[p ps] all-profiles]
      (d/append! (d/by-id "profiles") (str "<div>" p ": " "<a href=\"" ps "\">" ps "</a></div>")))))

(defn render-projects
  []
  (let [projects (-> cv/cv :projects)
        projects-keys (keys projects)
        all-projects (for [k projects-keys]
                     [(name k) (projects k)])]
    (doseq [[p ps] all-projects]
      (d/append! (d/by-id "projects") (str "<div>" p ": " "<a href=\"" ps "\">" ps "</a></div>")))))

(defn ^:export main []
  (render-identity)
  (render-current-position)
  (render-previous-positions)
  (render-skills)
  (render-profiles)
  (render-projects)
  (render-formations)
  (render-hobbies)
  (render-misc))
