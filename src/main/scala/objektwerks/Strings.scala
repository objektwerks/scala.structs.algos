package objektwerks

object Strings {
  import scala.annotation.tailrec

  def countChars(s: String): Map[Char, Int] = {
    @tailrec
    def loop(string: String, acc: Map[Char, Int]): Map[Char, Int] = {
      if (string.isEmpty) acc
      else if (acc.contains(string.head)) {
        val current = acc(string.head)
        loop(string.tail, acc + (string.head -> (current + 1)))
      } else loop(string.tail, acc + (string.head -> 1))
    }
    loop(s, Map.empty[Char, Int])
  }
}