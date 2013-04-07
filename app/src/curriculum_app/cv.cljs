(ns curriculum-app.cv
  "My cv as pure data. After all, everything is data!"
  (:require [clojure.string :as s]))

(def cv
  {:identity     {:name        "DUMONT"
                  :first-name  "Antoine"
                  :middle-name "Romain"
                  :title       "Software Engineer/Functional Developer/Devops"
                  :xp          "6,5 years of experience"
                  :birth       "10/22/1982 - Roubaix (59)"
                  :address     "43 bis boulevard Jean Moulin"
                  :city        "93190 Livry Gargan"
                  :country     "FRANCE"
                  :emails      (map #(s/join " [ a t ] " %) [["antoine.romain.dumont" "gmail.com"]])}

   :jobs         {:current      :sfeir
                  :sfeir        {:as     "Software Engineer/Developer/Devops"
                                 :period "2011"}
                  :previous-pos [:sopra-group :lcfrance]
                  :sopra-group  {:as     "Software Engineer"
                                 :period "2007-2011"}
                  :lcfrance     {:as     "Web developer"
                                 :period "2005-2006"}
                  :linkedbynet  {:as     "Intranet developer"
                                 :period "2005-2005"}}

   :formations   {:period "2001-2007"
                  :deug {:period "2001-2004"
                         :title   "DEUG MIAS option informatique"
                         :college "Université Paris 7 Denis Diderot"}
                  :licence {:period "2005"
                            :title   "Licence informatique"
                            :college "Université Paris 7 Denis Diderot"}
                  :master {:period "2006-2007"
                           :title   "Master Ingénierie Informatique (II), Systémes, Réseaux et Internet"
                           :college "Université Paris 7 Denis Diderot"}}

   :xp           {:sfeir       ["Java development"
                                "Development tools"
                                "Devops tools development"
                                "Cluster installation"
                                "POC"]
                  :sopra-group ["Maintien en conditions opérationnelles de multiples applications"]
                  :lcfrance    ["Web development"]
                  :linkbynet   ["Intranet tools development"]}

   :skills       {:programming-languages    ["Clojure" "Haskell" "Ruby" "*sh" "C" "PHP" "*SQL" "XHTML" "Java" "etc..."]
                  :other-languages          ["Emacs-lisp" "Common-lisp" "Clojurescript" "Objective-c" "Arduino programming language"]
                  :DVCS                     ["Git"]
                  :tools                    ["GNU/Linux" "Emacs" "Leiningen" "Eclipse" "Apache Maven" "Apache Ant" "Makefile"]
                  :configuration-management ["Puppet" "Pallet"]
                  :nosql-databases          ["Hadoop" "Datomic"]
                  :sql-databases            ["Mysql" "Postgresql" "Oracle"]
                  :spoken-languages         ["English"]}

   :profiles     {:github            "https://github.com/ardumont"
                  :twitter           "http://twitter.com/@ardumont"
                  :gravatar          "http://en.gravatar.com/ardumont"
                  :linkedin          "http://www.linkedin.com/pub/antoine-romain-dumont/5/158/655"
                  :viadeo            "http://www.viadeo.com/fr/profile/antoine-romain.dumont"
                  :facebook          "https://www.facebook.com/profile.php?id=642018541"}

   :projects     {:go-see-github     "https://github.com/ardumont"
                  :my-blog           "http://adumont.fr/blog"
                  :4-clojure         "http://www.4clojure.com/user/ardumont"
                  :project-euler     "http://projecteuler.net/profile/ardumont.png"
                  :clojure-paris-ug  "https://groups.google.com/forum/?fromgroups#!forum/clojure-paris-user-group"
                  :projectsion       "http://projectsion.sourceforge.net/"}

   :misc         ["Father of 2" "Geek and proud of it" "Linuxian" "Clojurian" "Emacs user" "Free Software" "Simplicity Matters" "Functional"]

   :hobbies      ["Clojure*" "Coding" "Reading" "Running" "Roller" "Guitar" "Drum"]})
