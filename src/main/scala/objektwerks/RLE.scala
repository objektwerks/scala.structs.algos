package objektwerks

import scala.annotation.tailrec

object RLE:
  case class Encoding(char: Char, count: Int) extends Product with Serializable

  // Only encodes letters.
  def encode(string: String): String =
    def group(chars: List[Char]): List[List[Char]] =
      if chars.isEmpty then List(List())
      else
        val (matchingChars, remainingChars) = chars span { char => char == chars.head }
        if remainingChars == Nil then List(matchingChars)
        else matchingChars :: group(remainingChars) // not tail-recursive

    val letters = string.toCharArray.toList.filter(char => char.isLetter )
    letters match
      case Nil => ""
      case _ =>
        val encodings = group(letters) map { chars => Encoding(chars.head, chars.length) }
        val encodedStrings = encodings map { encoding => encoding.char.toString + encoding.count.toString }
        encodedStrings.mkString

  // Only decodes letter-number pairs, multiplying letters by 1-2 digit numbers ( 1 - 99 ).
  def decode(string: String): String =
    @tailrec
    def loop(chars: List[Char], acc: StringBuilder): String =
      chars match
        case Nil => acc.mkString
        case head :: tail =>
          if head.isDigit then
            if tail.headOption.nonEmpty && tail.head.isDigit then
              val times = head.asDigit.toString + tail.head.asDigit.toString
              loop(tail.tail, acc.append( acc.lastOption.getOrElse("").toString * ( times.toInt - 1 ) ) )
            else loop(tail, acc.append( acc.lastOption.getOrElse("").toString * ( head.asDigit - 1 ) ) )
          else loop(tail, acc.append(head))

    loop(string.toCharArray.toList, new StringBuilder())