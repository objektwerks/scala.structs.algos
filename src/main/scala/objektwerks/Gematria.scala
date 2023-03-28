package objektwerks

import scala.collection.immutable.SortedMap

object Gematria:
  val alphabet = "abcdefghijklmnopqrstuvwxyz".toList
  val ordinalMap = SortedMap.from(
    alphabet
      .zipWithIndex
      .map( (char, index) => char -> (index + 1) )
  )
  val reverseOrdinalMap = SortedMap.from( alphabet.reverse.zipWithIndex.map( (char, index) => char -> (index + 1) ) )

  def deciper(cipher: SortedMap[Char, Int], string: String): Int =
    string
      .toCharArray
      .filter(char => char.isLetter)
      .map(letter => cipher.getOrElse(letter, 0))
      .sum