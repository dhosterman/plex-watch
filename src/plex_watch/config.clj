(ns plex-watch.config
  (:require [carica.core :refer [configurer resources overrider]]
            [clojure.java.io :refer [as-url file]]))

(def config (configurer (as-url (file (str (System/getenv "HOME") "/.plex_watch.edn")))))
(def override-config (overrider config))
