package objektwerks

import scala.annotation.tailrec

object RLE {
  final case class Encoding(char: Char, count: Int) extends Product with Serializable

  // Only encodes letters.
  def encode(value: String): String = {
    def group(chars: List[Char]): List[List[Char]] = {
      if (chars.isEmpty) List(List())
      else {
        val (grouped, next) = chars span { char => char == chars.head }
        if (next == Nil) List(grouped)
        else grouped :: group(next)
      }
    }
    val letters = value.toCharArray.toList.filter( char => char.isLetter )
    letters match {
      case Nil => ""
      case _ =>
        val encodings = group(letters) map { chars => Encoding(chars.head, chars.length) }
        val encodedValues = encodings map { group =>
          group.char.toString + group.count.toString
        }
        encodedValues.mkString
    }
  }

  // Only decodes letter-number pairs, expanding letters up to 2 digit places ( 1 - 99 ).
  def decode(value: String): String = {
    @tailrec
    def loop(chars: List[Char], acc: StringBuilder ): String = {
      chars match {
        case Nil => acc.mkString
        case head :: tail =>
          if (head.isDigit) {
            if (tail.headOption.nonEmpty && tail.head.isDigit) {
              val times = head.asDigit.toString + tail.head.asDigit.toString
              loop(tail.tail, acc.append( acc.lastOption.getOrElse("").toString * ( times.toInt - 1 ) ) )
            } else loop(tail, acc.append( acc.lastOption.getOrElse("").toString * ( head.asDigit - 1 ) ) )
          } else loop(tail, acc.append(head))
      }
    }
    loop(value.toCharArray.toList, new StringBuilder())
  }
}