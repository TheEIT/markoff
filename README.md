# markoff

A Clojure implementation of the [string rewriting system](http://en.wikipedia.org/wiki/String_rewriting_system) from Donald Knuth's [_The Art of Computer Programming_](http://en.wikipedia.org/wiki/The_Art_of_Computer_Programming), section 1.1. Knuth attributes the system to A.A. Markov the younger. It is not identical to the system of "Rules" given in the [Markov algorithm](http://en.wikipedia.org/wiki/Markov_algorithm) Wikipedia article, and I have not personally verified its Turing-completeness.

Pull requests are welcome, as is feedback in any form.

## Usage

	(require [markoff.core :refer :all])
	(markoff input-str pgm-vec)

`Input-str` should be a string. `Pgm-vec` should be a vector of maps, each containing the following keys:
- :theta, the substring to search for
- :phi, the substring with which to replace theta if it is found
- :a, the index of the map to jump to if theta is not found
- :b, the index of the map to jump to if theta is found and replaced

The names of the keys are taken from the symbols used by Knuth in the text. Right now, the absence of any of these four keys from any map may cause the program to crash.

Execution begins with the first map in the vector, whose index is 0.

After each step, the working string is printed to standard output if it has changed.

Execution terminates when directed to a map whose :a and :b tags both point to the map itself. Here is a simple example:

	(def simple-pgm
	  [{:theta "foo" :phi "bar" :a 1 :b 0} ; Replace all instances of "foo" with "bar", then...
	   {:theta "baz" :phi "quux" :a 2 :b 1} ; Replace all instances of "baz" with "quux", then...
	   {:theta "" :phi "Voila!" :a 2 :b 2}]) ; Prepend "Voila!" to the working string and halt.

At present, the program does not provide for any other mode of termination.

## Sample Application: Greatest Common Divisor Calculator

The `markoff.gcd` namespace contains a Markoff program implementing Euclid's algorithm for finding the greatest common divisor of two integers, as given in the solution to Knuth's exercise 1.1-8 (2nd edition).

### Usage

	(gcd m n)

This will construct a working string of m a's followed by n b's. The final output will be a string of GCD(m, n) a's.

Entering `lein run` while in the `markoff` directory will demonstrate the use of `markoff.gcd` to calculate the GCD of 48 and 28.

## Possible future improvements

### Possible improvements to markoff.core

- Proper failure when given incorrect type of input
- Detection and handling of missing keys in program
- Optional limit on time spent, or number or duration of steps taken
-- What behavior when the limit is reached?
- Output options: show working string at each step (as at present), only upon change, or not at all
- More verbose final output: number of steps, max length of string, proportion of search misses...
- Additional sample applications
-- Might this program actually be useful for something?
- Public API
- Web interface
- Generalization to other kinds of collections
- Demonstration of Turing-completeness

### Possible improvements to markoff.gcd

- Proper failure on invalid input (negative or non-integer numbers, etc.)
- Option to use different letters than "a" and "b"
- Legible final output ("The GCD of 48 and 28 is 4")

## Version history

v0.1.0 released 6 March 2014 (first working version of core and gcd)
v0.1.1 released 3 April 2014 (suppressed printing of unchanged working string)

## License

Copyright © 2014 Dell Adams, EIT

Distributed under the Eclipse Public License either version 1.0 or (at
your option) any later version.
