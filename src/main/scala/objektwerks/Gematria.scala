package objektwerks

import scala.collection.immutable.SortedMap

object Gematria:
  val alphabet = "abcdefghijklmnopqrstuvwxyz".toList

  val ordinalCiper = SortedMap.from(
    alphabet
      .view
      .zipWithIndex
      .map( (char, index) => char -> (index + 1) )
  )

  val reverseOrdinalCipher = SortedMap.from(
    alphabet
      .reverse
      .view
      .zipWithIndex
      .map( (char, index) => char -> (index + 1) )
  )

  val reductionCiper = Map( 'a' -> 1, 'b' -> 2, 'c' -> 3, 'd' -> 4, 'e' -> 5, 'f' -> 6, 'g' -> 7, 'h' -> 8, 'i' -> 9,
                            'j' -> 1, 'k' -> 2, 'l' -> 3, 'm' -> 4, 'n' -> 5, 'o' -> 6, 'p' -> 7, 'q' -> 8, 'r' -> 9,
                            's' -> 1, 't' -> 2, 'u' -> 3, 'v' -> 4, 'w' -> 5, 'x' -> 6, 'y' -> 7, 'z' -> 8 )
  
  val reverseReductionCipher = Map( 'a' -> 8, 'b' -> 7, 'c' -> 6, 'd' -> 5, 'e' -> 4, 'f' -> 3, 'g' -> 2, 'h' -> 1, 'i' -> 9,
                                    'j' -> 8, 'k' -> 7, 'l' -> 6, 'm' -> 5, 'n' -> 4, 'o' -> 3, 'p' -> 2, 'q' -> 1, 'r' -> 9,
                                    's' -> 8, 't' -> 7, 'u' -> 6, 'v' -> 5, 'w' -> 4, 'x' -> 3, 'y' -> 2, 'z' -> 1 )
  
  val standardCipher = Map( 'a' -> 1, 'b' -> 2, 'c' -> 3, 'd' -> 4, 'e' -> 5, 'f' -> 6, 'g' -> 7, 'h' -> 8, 'i' -> 9,
                            'j' -> 10, 'k' -> 20, 'l' -> 30, 'm' -> 40, 'n' -> 50, 'o' -> 60, 'p' -> 70, 'q' -> 80, 'r' -> 90,
                            's' -> 100, 't' -> 200, 'u' -> 300, 'v' -> 400, 'w' -> 500, 'x' -> 600, 'y' -> 700, 'z' -> 800 )
  
  val latinCipher = Map( 'a' -> 1, 'b' -> 2, 'c' -> 3, 'd' -> 4, 'e' -> 5, 'f' -> 6, 'g' -> 7, 'h' -> 8, 'i' -> 9,
                         'j' -> 600, 'k' -> 10, 'l' -> 20, 'm' -> 30, 'n' -> 40, 'o' -> 50, 'p' -> 60, 'q' -> 70, 'r' -> 80,
                         's' -> 90, 't' -> 100, 'u' -> 200, 'v' -> 700, 'w' -> 900, 'x' -> 300, 'y' -> 400, 'z' -> 500 )
                         
  def deciper(cipher: Map[Char, Int], string: String): Int =
    string
      .toCharArray
      .view
      .filter(char => char.isLetter)
      .map(letter => cipher.getOrElse(letter, 0))
      .sum