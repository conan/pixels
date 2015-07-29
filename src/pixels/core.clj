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
  (let [X (dec X)
        Y (dec Y)]
    (assoc-in pixmap [X Y] C)))


(defn V
  "Draw a vertical segment of colour C in column X between rows Y1 and Y2 (inclusive)."
  [pixmap X Y1 Y2 C]
  (let [X         (dec X)
        Y1        (dec Y1)
        Y2        (dec Y2)
        to-update (set (range Y1 (inc Y2))) ;; end of range is exclusive, we want inclusive
        update-fn (fn [i row]
                    (if (to-update i)
                      (assoc-in row [X] C)
                      row))]
    (vec (map-indexed update-fn pixmap))))


(defn H
  "Draw a horizontal segment of colour C in row Y between columns X1 and X (inclusive)."
  [pixmap X1 X2 Y C]
  (let [X1        (dec X1)
        X2        (dec X2)
        Y         (dec Y)
        length    (count (range X1 (inc X2)))
        update-fn (fn [i row]
                    (if (= i Y)
                      (vec (concat (take X1 row) (repeat length C) (drop (inc X2) row)))
                      row))]
    (vec (map-indexed update-fn pixmap))))


(defn count-duplicates
  "Counts how many times the first value in a seq appears consecutively after the first.
  e.g. (count-duplicates [4 4 4 3 1 4]) => 2
  because the number 4 appears twice after the initial 4 at the head of the seq"
  [s]
  (->> s
       (partition-by #(= (first s) %))
       first
       count
       dec))


(defn row-fills
  "Given a row and an index i, returns a seq of all the indices of contiguous elements in the row connected to the
  element at i by the same colour.
  e.g. (row-fills [3 1 3 3 3] 2) => (2 3 4)
  because the elements at positions 2, 3 and 4 in the row are the same colour as position 2 and are contiguous"
  [row i]
  (let [colour       (get row i)
        start-offset (count-duplicates (->> row (take (inc i)) reverse))
        start        (- i start-offset)
        end-offset   (count-duplicates (drop i row))
        end          (+ i end-offset)]
    (range start (inc end))))


(defn F
  "Fill the region R with the colour C. R is defined as: Pixel (X,Y) belongs to R. Any other pixel which is the same
  colour as (X,Y) and shares a common side with any pixel in R also belongs to this region."
  [pixmap X Y C]
  (let [X         (dec X)
        Y         (dec Y)
        XY        (get-in pixmap [X Y])
        rows-up   (reverse (range 0 Y))
        rows-down (range (inc Y) (count pixmap))]))




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