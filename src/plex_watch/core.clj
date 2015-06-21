(ns plex-watch.core
  (:gen-class)
  (:require [hawk.core :as hawk]))

(defn watch []
  (hawk/watch! [{:paths ["/home/dhosterman"]
                 :filter (and hawk/file? hawk/created?)
                 :handler (fn [ctx e]
                            (println "event: " e)
                            (println "conext: " ctx)
                            ctx)}]))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (watch))
