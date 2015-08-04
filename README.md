# pixels

A Clojure library designed to simulate operations on pixmaps

## Usage

    (require '[pixels.core])
    
Create a new image:

    (def image (I 3 4))
    => [["O" "O" "O"] ["O" "O" "O"] ["O" "O" "O"] ["O" "O" "O"]]
    
Colour a pixel:
 
    (L image 2 2 "X")
    => [["O" "O" "O"] ["O" "X" "O"] ["O" "O" "O"] ["O" "O" "O"]]
    
Paint a vertical segment:

    (V image 3 2 4 "X")
    => [["O" "O" "O"] ["O" "O" "X"] ["O" "O" "X"] ["O" "O" "X"]] 
    
Paint a horizontal segment:

    (H image 1 3 2 "X")
    => [["O" "O" "O"] ["X" "X" "X"] ["O" "O" "O"] ["O" "O" "O"]]
    
Flood fill a region:

    (def question-mark [["X" "X" "X"]
                        ["X" "O" "X"]
                        ["O" "X" "X"]
                        ["O" "X" "O"]])
                        
    (F question-mark 2 3 "I")
    => [["I" "I" "I"] ["I" "O" "I"] ["O" "I" "I"] ["O" "I" "O"]]

## License

Copyright © 2015 Conan Cook
