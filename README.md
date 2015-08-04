# pixels

A Clojure library designed to simulate operations on pixmaps.  Note that the pixmaps are represented by vectors
of vectors, which means in the code the coordinates are reversed to allow use of things like `get-in`.

## Prerequisites

The following are required to run this tool:

* [Java SE Development Kit 8](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)
* [Leiningen 2.5.1](http://leiningen.org/)

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
    
## Running tests

Tests are written using [Midje](https://github.com/marick/Midje)

    lein midje

## License

Copyright © 2015 Conan Cook
