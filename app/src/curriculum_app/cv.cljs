(ns curriculum-app.cv
  "My cv as pure data. After all, everything is data!"
  (:require [clojure.string :as s]))

(def cv
  {:identity     {:name        "DUMONT"
                  :first-name  "Antoine"
                  :middle-name "Romain"
                  :title       "Software Engineer/Functional Developer/Devops"
                  :xp          "13 years of experience"
                  :birth       "10/22/1982 - Roubaix (59)"
                  :address     "43 bis boulevard Jean Moulin"
                  :city        "93190 Livry Gargan"
                  :country     "FRANCE"
                  :emails      (map #(s/join " [ a t ] " %) [["antoine.romain.dumont" "gmail.com"]])}

   :jobs         {:current      :inria
                  :inria        {:as     "Software Engineer/Developer/Devops"
                                 :period "2015"}
                  :previous-pos [:sfeir :sopra-group :lcfrance :linkbynet]
                  :sfeir        {:as     "Software Engineer/Developer/Devops"
                                 :period "2011-2015"}
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

   :xp           {:inria       ["Development"
                                "Devops"
                                "Environment Deployment"
                                "Continuous Integration"
                                "Increasing applications' test coverage"
                                "Increase Archive Coverage"
                                "Maintenance in operational conditions the Source code Archive"
                                ]
                  :sfeir       ["Development (frontend, backend, RESTful applications)"
                                "Tools for developers, Automation (shell scripts, Makefile, documentation, etc...)"
                                "Continuous integration (Cloudbees, Jenkins, Bamboo, etc...)"
                                "Devops tools development (Puppet, scripts, READMEs)"
                                "Environment deployment (cluster included)"
                                "Proof of Concepts (P.O.C.)"
                                "Technical leader"
                                "etc..."]
                  :sopra-group ["Maintenance in operational conditions miscellaneous applications"
                                "Technical leader"]
                  :lcfrance    ["Web development"]
                  :linkbynet   ["Intranet tools development"]}

   :skills       {:programming-languages    ["Python" "Haskell" "Ruby" "*sh" "C" "PHP" "*SQL" "etc..."]
                  :other-languages          ["Emacs-lisp" "Clojure" "Common-lisp" "OCaml" "Scheme"]
                  :DVCS                     ["Git" "Mercurial"]
                  :RCS                      ["Apache Subversion" "CVS"]
                  :distribution             ["NixOS" "Debian" "Gentoo"]
                  :tools                    ["GNU/Linux" "Emacs" "Apache Kafka" "Elk" "Grafana" "Sentry" "Makefile" "Nix"]
                  :configuration-management ["Puppet" "Terraform"]
                  :nosql-databases          ["Hadoop" "Datomic"]
                  :sql-databases            ["Postgresql" "Mysql/MariaDb" "Oracle"]
                  :methodologies            ["Agilily (Scrum)" "pair programming" "remote programming"]
                  :spoken-languages         ["English" "French"]
                  :others                   ["autodidact" "continuous learning" "functional developer"]}

   :profiles     {:github            "https://github.com/ardumont"
                  :twitter           "http://twitter.com/@ardumont"
                  :gravatar          "http://en.gravatar.com/ardumont"
                  :linkedin          "http://fr.linkedin.com/in/ardumont"
                  :viadeo            "http://www.viadeo.com/fr/profile/antoine-romain.dumont"}

   :projects     {:org-trello        "http://org-trello.github.io"
                  :my-blog           "http://ardumont.github.io"
                  :go-see-github     "https://github.com/ardumont"}

   :old-projects {:projectsion       "http://projectsion.sourceforge.net/"
                  :4-clojure         "http://www.4clojure.com/user/ardumont"
                  :project-euler     "http://projecteuler.net/profile/ardumont.png"}

   :misc         ["Father of 4"
                  "Geek"
                  "Linuxian"
                  "Clojurian"
                  "Emacs user"
                  "Free Software"
                  "Functional"
                  "Simplicity Matters"]

   :hobbies      ["Learning"
                  "Solving problems"
                  "Emacs"
                  "Haskell"
                  "*Lisp"
                  "Coding"
                  "Judo"
                  "Running"
                  "Books"
                  "Guitar"
                  "Roller"
                  "Skate"]})
