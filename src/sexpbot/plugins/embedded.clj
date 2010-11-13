(ns sexpbot.plugins.embedded
  (:use [sexpbot plugin]))

(defplugin
  (:hook
   :on-message
   (fn [{:keys [message bot] :as irc-map}]
     (doseq [x (reverse (re-seq #"\$#(.*?)#\$" message))]
       (->> x second (-> @bot :config :prepends first str)
            (assoc irc-map :message) try-handle)))))