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
  (:require [domina            :as d]
            [dommy.template    :as t]
            [curriculum-app.cv :as cv]
            [clojure.string    :as s]))

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

    (d/append! (d/by-id "profile") (t/node [:div.title (s/join " " [first-name middle-name name])]))
    (d/append! (d/by-id "profile") (t/node [:div birth]))
    (d/append! (d/by-id "profile") (t/node [:div (s/join " - " [address city country])]))
    (d/append! (d/by-id "profile") (t/node [:div (s/join " - " emails)]))
    (d/append! (d/by-id "profile") (t/node [:div phone]))
    (d/append! (d/by-id "profile") (t/node [:span.photo]))))

(defn render-current-position
  []
  (let [{:keys [current] :as current-pos} (:jobs cv/cv)
        {:keys [as period]} (current-pos current)]
    (d/append! (d/by-id "cpos")
               (t/node [:div (str as " at " (-> current name s/capitalize) " since " period)]))))

(defn render-previous-positions
  []
  (let [previous-pos (get-in cv/cv [:jobs :previous-pos])
        positions (map (fn [p]
                         (let [{:keys [as period]} (get-in cv/cv [:jobs p])]
                           [as "at" (-> p name s/capitalize) "for the period" period]))
                       previous-pos)]
    (doseq [p positions]
      (d/append! (d/by-id "ppos")
                 (t/node [:div (s/join " " p)])))))

(defn render-formations
  []
  (let [form (:formations cv/cv)
        period (:period form)
        {:keys [title college]} (:master form)
        formation [title "at" college "for" period]]
    (d/append! (d/by-id "formation")
               (t/node [:div (s/join " " formation)]))))

(defn render-hobbies
  []
  (let [hobbies (:hobbies cv/cv)]
    (d/append! (d/by-id "hobbies")
               (t/node [:div (s/join ", " hobbies)]))))

(defn render-misc
  []
  (let [misc (:misc cv/cv)]
    (d/append! (d/by-id "misc")
               (t/node [:div (s/join ", " misc)]))))

(defn render-skills
  []
  (let [skills (:skills cv/cv)
        skills-keys (keys skills)
        all-skills (for [k skills-keys]
                     [(name k) (skills k)])]
    (doseq [[s sks] all-skills]
      (d/append! (d/by-id "skills")
                 (t/node [:div (str s ": " (s/join ", " sks))])))))

(defn render-profiles
  []
  (let [profiles (:profiles cv/cv)
        profiles-keys (keys profiles)
        all-profiles (for [k profiles-keys]
                     [(name k) (profiles k)])]
    (doseq [[p ps] all-profiles]
      (d/append! (d/by-id "profiles")
                 (t/node [:div (str p ": ") [:span [:a {:href ps} ps]]])))))

(defn render-projects
  []
  (let [projects (:projects cv/cv)
        projects-keys (keys projects)
        all-projects (for [k projects-keys]
                     [(name k) (projects k)])]
    (doseq [[p ps] all-projects]
      (d/append! (d/by-id "projects")
                 (t/node [:div (str p ": ") [:span [:a {:href ps} ps]]])))))

(defn render-xp
  []
  (let [experiences (:xp cv/cv)
        experiences-keys (keys experiences)
        all-experiences (for [k experiences-keys]
                          [(-> k name s/capitalize) (experiences k)])]
    (doseq [[x xp] all-experiences]
      (d/append! (d/by-id "xp")
                 (t/node [:div (str x ": " (s/join ", " xp))])))))

(defn ^:export main []
  (render-identity)
  (render-current-position)
  (render-previous-positions)
  (render-xp)
  (render-skills)
  (render-profiles)
  (render-projects)
  (render-formations)
  (render-hobbies)
  (render-misc))
