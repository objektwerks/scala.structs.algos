package objektwerks

object Numbers {
  import Math._

  import scala.annotation.tailrec
  import scala.util.Random

  def isPrime(n: Int): Boolean = {
    @tailrec
    def loop(current: Int): Boolean = {
      if (current > sqrt(abs(n.toDouble))) true
      else n % current != 0 && loop(current + 1)
    }
    if (n == -1 || n == 0 || n == 1) false else loop(2)
  }

  def listFactors(n: Int): List[Int] = {
    @tailrec
    def loop(remaining: Int, currentDivisor: Int, acc: List[Int]): List[Int] = {
      if (currentDivisor > sqrt(remaining.toDouble)) remaining :: acc
      else if (remaining % currentDivisor == 0) loop(remaining / currentDivisor, currentDivisor, currentDivisor :: acc)
      else loop(remaining, currentDivisor + 1, acc)
    }
    if (n < 1) List.empty[Int] else loop(n, 2, List())
  }

  def approximatePi(points: Int): Double = {
    val random = new Random(System.currentTimeMillis())
    val pointInCircle = (1 to points).map { _ =>
      val x = random.nextDouble()
      val y = random.nextDouble()
      x * x + y * y
    }.count(distance => distance < 1)
    pointInCircle * 4.0 / points
  }

  def fractionToRecurringDecimals(numerator: Long, denominator: Long): String = {
    def fractionToDecimal(n: Long, d: Long): String = {
      @tailrec
      def findStart(digit: Long, digits: List[Long], rem: Long, remainders: List[Long], currentIndex: Int): Int = {
        if (digits.isEmpty || remainders.isEmpty) -1
        else if (digit == digits.head && rem == remainders.head) currentIndex
        else findStart(digit, digits.tail, rem, remainders.tail, currentIndex + 1)
      }
      @tailrec
      def loop(num: Long, denominator: Long, digits: List[Long], remainders: List[Long]): String = {
        val quot = (num * 10) / denominator
        val rem = (num * 10) % denominator
        if (rem == 0) (digits :+ quot).mkString("")
        else {
          val recurrenceStartIndex = findStart(quot, digits, rem, remainders, 0)
          if (recurrenceStartIndex == -1) loop(rem, denominator, digits :+ quot, remainders :+ rem)
          else {
            val (beforeRecurrence, recurrence) = digits.splitAt(recurrenceStartIndex)
            s"${beforeRecurrence.mkString("")}(${recurrence.mkString("")})"
          }
        }
      }
      if (n > 0 && d < 0) s"-${fractionToDecimal(n, -d)}"
      else if (n < 0 && d > 0) s"-${fractionToDecimal(-n, d)}"
      else {
        val quotient = n / d
        val remainder = n % d
        if (remainder == 0) s"$quotient"
        else s"$quotient.${loop(remainder, d, List(), List())}"
      }
    }
    fractionToDecimal(numerator, denominator)
  }

  def largestNumber(numbers: List[Int]): Int = {
    implicit val ordering: Ordering[Int] = Ordering.fromLessThan { (a, b) =>
      val aString = a.toString
      val bString = b.toString
      (aString + bString).compareTo(bString + aString) >= 0
    }
    val largest = numbers.sorted.mkString("")
    if (numbers.isEmpty || largest.charAt(0) == '0') 0
    else largest.toInt
  }

  def reverseInteger(number: Int): Int = {
    @tailrec
    def loop(remaining: Int, acc: Int): Int =
      if (remaining == 0) acc
      else {
        val digit = remaining % 10
        val tentativeResult = acc * 10 + digit
        if ((acc >= 0) != (tentativeResult >= 0)) 0
        else loop(remaining / 10, tentativeResult)
      }
    if (number == Int.MinValue) 0
    else if (number >= 0) loop(number, 0)
    else -loop(-number, 0)
  }

  @tailrec
  def parseInteger(string: String): Int = {
    val WHITESPACE = ' '
    val PLUS = '+'
    val MINUS = '-'
    val DIGITS = "0123456789".toSet

    def integerRangeEnd(sign: Int): Int = if (sign >= 0) Int.MaxValue else Int. MinValue

    @tailrec
    def loop(remainder: String, sign: Int, acc: Int = 0): Int =
      if (remainder.isEmpty || !DIGITS.contains(remainder.charAt(0))) acc
      else {
        val newDigit = remainder.charAt(0) - '0'
        val tentativeResult = acc * 10 + newDigit * sign

        if ((sign >= 0) != (tentativeResult >= 0)) integerRangeEnd(sign)
        else loop(remainder.substring(1), sign, tentativeResult)
      }

    if (string.isEmpty) 0
    else if (string.charAt(0) == WHITESPACE) parseInteger(string.substring(1))
    else if (string.charAt(0) == PLUS) loop(string.substring(1), sign = 1)
    else if (string.charAt(0) == MINUS) loop(string.substring(1), sign = -1)
    else loop(string, sign = 1)
  }
}