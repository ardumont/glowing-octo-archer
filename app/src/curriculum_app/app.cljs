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

(defn render-html!
  "Given the html data, modify the dom id html-id with such data"
  [html-data html-id]
  (d/append! (d/by-id html-id) (t/node html-data)))

(defn render-identity! []
  (let [{:keys [name first-name middle-name address city country emails phone title xp]} (:identity cv/cv)
        profile [[:div.title (s/join " " [first-name middle-name name])]
                 [:div title]
                 [:div (str " (" xp ")")]
                 [:div (s/join " - " [address city country])]
                 [:div (s/join " - " emails)]
                 [:div phone]
                 [:span.photo]]]
    (doseq [p profile] (render-html! p "profile"))))

(defn render-current-position! []
  (let [{:keys [current] :as current-pos} (:jobs cv/cv)
        {:keys [as period]} (current-pos current)]
    (render-html! [:div (str as " at " (-> current name s/capitalize) " since " period)] "cpos")))

(defn render-previous-positions! []
  (let [previous-pos (get-in cv/cv [:jobs :previous-pos])
        positions (map (fn [p] (let [{:keys [as period]} (get-in cv/cv [:jobs p])]
                                [as "at" (-> p name s/capitalize) "for the period" period]))
                       previous-pos)]
    (doseq [p positions] (render-html! [:div (s/join " " p)] "ppos"))))

(defn render-formations! []
  (let [form (:formations cv/cv)
        period (:period form)
        {:keys [title college]} (:master form)]
    (render-html! [:div (str title " at " college " for " period)] "formation")))

(defn render-hobbies! []
  (render-html! [:div (s/join ", " (:hobbies cv/cv))] "hobbies"))

(defn render-misc! []
  (render-html! [:div (s/join ", " (:misc cv/cv))] "misc"))

(defn render-key-csv
  "Given a key and comma separated values, render a html li data structure of k - v0, v1, ..."
  [k v]
  [:li.dash (str k " - " (s/join ", " v))])

(defn render-key-csv-href
  "Given a key and comma separated values containing href, render a html li data structure of k - v0href, v1href, ..."
  [k vh]
  [:li.dash (str k " - ") [:span [:a {:href vh} vh]]])

(defn compute-map-key-csv
  "Given a map with key and list as values, compute the html data"
  [map-data render-html-fn]
  (let [keys (keys map-data)
        all (for [k keys]
              [(name k) (map-data k)])]
    [:div (for [[k v] all] (render-html-fn k v))]))

(defn render-skills! []
  (-> cv/cv :skills (compute-map-key-csv render-key-csv) (render-html! "skills")))

(defn render-xp! []
    (-> cv/cv :xp (compute-map-key-csv render-key-csv) (render-html! "xp")))

(defn render-projects! []
  (-> cv/cv :projects (compute-map-key-csv render-key-csv-href) (render-html! "projects")))

(defn render-old-projects! []
  (-> cv/cv :old-projects (compute-map-key-csv render-key-csv-href) (render-html! "old-projects")))

(defn render-profiles! []
    (-> cv/cv :profiles (compute-map-key-csv render-key-csv-href) (render-html! "profiles")))

(defn ^:export main []
  (render-identity!)
  (render-current-position!)
  (render-previous-positions!)
  (render-xp!)
  (render-skills!)
  (render-profiles!)
  (render-projects!)
  (render-old-projects!)
  (render-formations!)
  (render-hobbies!)
  (render-misc!))
