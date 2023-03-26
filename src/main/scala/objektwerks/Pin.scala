package objektwerks

import scala.util.Random

object Pin:
  private val specialChars = "~!@#$%^&*-+=<>?/:;".toList
  private val limit = specialChars.length
  private val random = new Random

  private def newSpecialChar: Char = specialChars(random.nextInt(limit))

  /**
   * 26 letters + 10 numbers + 18 special characters = 54 combinations
   * 7 alphanumeric char pin = 54^7 ( 1,338,925,209,984 )
   */
  def apply(): String =
    Random.shuffle(
      Random
        .alphanumeric
        .take(5)
        .mkString
        .prepended(newSpecialChar)
        .appended(newSpecialChar)
    ).mkString