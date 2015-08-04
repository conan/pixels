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
  (let [x (dec Y)
        y (dec X)]
    (assoc-in pixmap [x y] C)))


(defn V
  "Draw a vertical segment of colour C in column X between rows Y1 and Y2 (inclusive)."
  [pixmap X Y1 Y2 C]
  (let [y         (dec X)
        x1        (dec Y1)
        x2        (dec Y2)
        to-update (set (range x1 (inc x2))) ;; end of range is exclusive, we want inclusive
        paint-fn  (fn [i row]
                    (if (to-update i)
                      (assoc-in row [y] C)
                      row))]
    (vec (map-indexed paint-fn pixmap))))


(defn H
  "Draw a horizontal segment of colour C in row Y between columns X1 and X (inclusive)."
  [pixmap X1 X2 Y C]
  (let [y1       (dec X1)
        y2       (dec X2)
        x        (dec Y)
        length   (count (range y1 (inc y2)))
        paint-fn (fn [i row]
                   (if (= i x)
                     (vec (concat (take y1 row) (repeat length C) (drop (inc y2) row)))
                     row))]
    (vec (map-indexed paint-fn pixmap))))


(defn neighbours
  [pixmap pixel]
  (let [x (first pixel)
        y (second pixel)]
    (->> [(when (<= x (count (first pixmap))) [(inc x) y])
          (when (<= y (count pixmap)) [x (inc y)])
          (when (> x 0) [(dec x) y])
          (when (> y 0) [x (dec y)])]
         (remove nil?)
         set)))


(defn F
  "Fill the region R with the colour C. R is defined as: Pixel (X,Y) belongs to R. Any other pixel which is
  the same colour as (X,Y) and shares a common side with any pixel in R also belongs to this region."
  [pixmap X Y C]
  (let [x        (dec Y)
        y        (dec X)
        original (get-in pixmap [x y])]
    (loop [visited   #{}
           unvisited #{[x y]}
           result    pixmap]
      (if (seq unvisited)
        (let [pixel      (first unvisited)
              neighbours (->> pixel
                              (neighbours pixmap)
                              (filter #(= original (get-in pixmap %)))
                              (filter (complement visited)))
              visited    (conj visited pixel)
              unvisited  (apply conj (disj unvisited pixel) neighbours)
              result     (assoc-in result pixel C)]
          (recur visited unvisited result))
        result))))


(defn S
  "Show the contents of the current image"
  [pixmap]
  (doseq [row pixmap]
    (apply print row)
    (println)))


(defn X
  "Terminate the session"
  []
  (System/exit 0))