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

  def checkAnagrams(first: String, second: String): Boolean = countChars(first) == countChars(second)
  def checkSortedAnagrams(first: String, second: String): Boolean = first.sorted == second.sorted

  def containsBalancedParens(string: String): Boolean = {
    @tailrec
    def loop(remaining: String, openParens: Int): Boolean = {
      if (remaining.isEmpty) openParens == 0
      else if (openParens == 0 && remaining.head == ')') false
      else if (remaining.head == '(') loop(remaining.tail, openParens + 1)
      else loop(remaining.tail, openParens - 1)
    }
    loop(string, 0)
  }
}