package objektwerks


object Gematria:
  val alphabet = "abcdefghijklmnopqrstuvwxyz".toCharArray.map(_.toString).toList
  val ordinalMap = alphabet.zipWithIndex(1)