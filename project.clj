(defproject pixels "0.1.0-SNAPSHOT"

  :description "Interview test executable solution simulating bitmaps"

  :dependencies [[midje "1.7.0"]
                 [org.clojure/clojure "1.7.0"]]

  :plugins [[lein-midje "3.1.3"]]

  ;; TODO this is a bad idea but makes the tests portable
  :jvm-opts ["-Dline.separator=\n"])
