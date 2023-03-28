package objektwerks

import scala.collection.immutable.SortedMap

object Gematria:
  val alphabet = "abcdefghijklmnopqrstuvwxyz".toCharArray.map(_.toString).toList
  val ordinalMap = SortedMap.from( alphabet.zipWithIndex.map( (value, index) => value -> (index + 1) ) )
  val reverseOrdinalMap = SortedMap.from( alphabet.reverse.zipWithIndex.map( (value, index) => value -> (index + 1) ) )