(ns pixels.core)


(defn I
  "Create a new M x N image with all pixels coloured white (O)."
  [M N]
  (->> "O"
    (repeat M)
    vec
    (repeat N)
    vec))


(defn C
  "Clears the table, setting all pixels to white (O)."
  [pixmap]
  (let [M (count (first pixmap))
        N (count pixmap)]
    (I M N)))


(defn L
  "Colours the pixel (X,Y) with colour C."
  [pixmap X Y C]
  (assoc-in pixmap [X Y] C))


(defn V
  "Draw a vertical segment of colour C in column X between rows Y1 and Y2 (inclusive)."
  [pixmap X Y1 Y2 C]
  ;; tODO this only iterates over the range, we need to iterate over the whole pixmap
  (vec (for [Y (range Y1 Y2)]
         (assoc-in pixmap [X Y] C))))