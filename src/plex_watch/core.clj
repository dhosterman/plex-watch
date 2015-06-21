(ns plex-watch.core
  (:gen-class)
  (:require [hawk.core :as hawk]
            [carica.core :refer [config]]
            [clj-http.client :as client]))

(defn is_video? [filename]
  (some? (re-matches #".*\.mkv|.*\.mp4|.*\.avi" filename)))

(defn send_push [filename]
  (client/post 
    "https://api.pushbullet.com/v2/pushes"
    {:headers {:Authorization (str "Bearer " (config :token))}
     :content-type :json
     :form-params {:type "note"
                   :title "New video available"
                   :body filename}}))

(defn watch_handler [ctx e]
  (let [filename (.getName(:file e))] 
    (if (is_video? filename)
      (send_push filename)))
  ctx)

(defn watch []
  (hawk/watch! [{:paths ["/home/dhosterman"]
                 :filter (and hawk/file? hawk/created?)
                 :handler watch_handler}]))

(defn -main
  [& args]
  (watch))
