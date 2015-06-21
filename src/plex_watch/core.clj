(ns plex-watch.core
  (:gen-class)
  (:require [hawk.core :as hawk]
            [carica.core :refer [config]]))

(defn is_video? [filename]
  (some? (re-matches #".*\.mkv|.*\.mp4|.*\.avi" filename)))

(defn watch_handler [ctx e]
  (let [filename (.getName(:file e))] 
    (if (is_video? filename)
      (println filename)))
  ctx)

(defn watch []
  (hawk/watch! [{:paths ["/home/dhosterman"]
                 :filter (and hawk/file? hawk/created?)
                 :handler watch_handler}]))

(defn -main
  [& args]
  (watch))
