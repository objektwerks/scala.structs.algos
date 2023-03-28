package objektwerks


object Gematria:
  val alphabet = "abcdefghijklmnopqrstuvwxyz".toCharArray.map(_.toString).toIndexedSeq
  val ordinalMap = alphabet.zipWithIndex.map( (value, index) => value -> index ).toMap