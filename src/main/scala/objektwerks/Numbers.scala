package objektwerks

import Math.*

import scala.annotation.tailrec
import scala.util.Random

object Numbers:
  def isSquare(n: Int): Boolean =
    val square = Math.sqrt(n)
    square - Math.floor(square) == 0

  def isStar(n: Int): Boolean =
    val star = ( 6 + Math.sqrt( (24 * n) + 12 ) ) / 6
    (star - star.toInt) == 0

  @tailrec
  def isTriangular(n: Int, counter: Int = 1, sum: Int = 0): Boolean =
    if n < 1 then false
    else if sum == n then true
    else if counter > n then false
    else isTriangular(n, counter + 1, sum + counter)

  def isPrime(n: Int): Boolean =
    @tailrec
    def loop(current: Int): Boolean =
      if current > sqrt(abs(n.toDouble)) then true
      else n % current != 0 && loop(current + 1)

    if n == -1 || n == 0 || n == 1 then false
    else loop(2)

  def findTetrahedral(n: Int): Int = (n * (n + 1) * (n + 2)) / 6

  def listFactors(n: Int): List[Int] =
    @tailrec
    def loop(remaining: Int, currentDivisor: Int, acc: List[Int]): List[Int] =
      if currentDivisor > sqrt(remaining.toDouble) then remaining :: acc
      else if remaining % currentDivisor == 0 then loop(remaining / currentDivisor, currentDivisor, currentDivisor :: acc)
      else loop(remaining, currentDivisor + 1, acc)

    if n < 1 then List.empty[Int] else loop(n, 2, List())

  def approximatePi(points: Int): Double =
    val random = new Random(System.currentTimeMillis())
    val pointInCircle = (1 to points).map { _ =>
      val x = random.nextDouble()
      val y = random.nextDouble()
      x * x + y * y
    }.count(distance => distance < 1)
    pointInCircle * 4.0 / points

  def fractionToRecurringDecimals(numerator: Long, denominator: Long): String =
    def fractionToDecimal(n: Long, d: Long): String =
      @tailrec
      def findStart(digit: Long,
                    digits: List[Long],
                    rem: Long,
                    remainders: List[Long],
                    currentIndex: Int): Int =
        if digits.isEmpty || remainders.isEmpty then -1
        else if digit == digits.head && rem == remainders.head then currentIndex
        else findStart(digit, digits.tail, rem, remainders.tail, currentIndex + 1)

      @tailrec
      def loop(num: Long,
               denominator: Long,
               digits: List[Long],
               remainders: List[Long]): String =
        val quot = (num * 10) / denominator
        val rem = (num * 10) % denominator
        if rem == 0 then (digits :+ quot).mkString("")
        else
          val recurrenceStartIndex = findStart(quot, digits, rem, remainders, 0)
          if recurrenceStartIndex == -1 then loop(rem, denominator, digits :+ quot, remainders :+ rem)
          else
            val (beforeRecurrence, recurrence) = digits.splitAt(recurrenceStartIndex)
            s"${beforeRecurrence.mkString("")}(${recurrence.mkString("")})"

      if n > 0 && d < 0 then s"-${fractionToDecimal(n, -d)}"
      else if n < 0 && d > 0 then s"-${fractionToDecimal(-n, d)}"
      else
        val quotient = n / d
        val remainder = n % d
        if remainder == 0 then s"$quotient"
        else s"$quotient.${loop(remainder, d, List(), List())}"
    
    fractionToDecimal(numerator, denominator)

  def largestNumber(numbers: List[Int]): Int =
    given ordering: Ordering[Int] = Ordering.fromLessThan { (a, b) =>
      val aString = a.toString
      val bString = b.toString
      (aString + bString).compareTo(bString + aString) >= 0
    }
    val largest = numbers.sorted.mkString("")
    if numbers.isEmpty || largest.charAt(0) == '0' then 0
    else largest.toInt

  def reverseInteger(number: Int): Int =
    @tailrec
    def loop(remaining: Int, acc: Int): Int =
      if remaining == 0 then acc
      else
        val digit = remaining % 10
        val tentativeResult = acc * 10 + digit
        if (acc >= 0) != (tentativeResult >= 0) then 0
        else loop(remaining / 10, tentativeResult)
      
    if number == Int.MinValue then 0
    else if number >= 0 then loop(number, 0)
    else -loop(-number, 0)

  @tailrec
  def parseInteger(string: String): Int =
    val whitespace = ' '
    val plus = '+'
    val minus = '-'
    val digits = "0123456789".toSet

    def integerRangeEnd(sign: Int): Int =
      if sign >= 0 then Int.MaxValue
      else Int. MinValue

    @tailrec
    def loop(remainder: String, sign: Int, acc: Int = 0): Int =
      if remainder.isEmpty || !digits.contains(remainder.charAt(0)) then acc
      else
        val newDigit = remainder.charAt(0) - '0'
        val tentativeResult = acc * 10 + newDigit * sign

        if (sign >= 0) != (tentativeResult >= 0) then integerRangeEnd(sign)
        else loop(remainder.substring(1), sign, tentativeResult)

    if string.isEmpty then 0
    else if string.charAt(0) == whitespace then parseInteger(string.substring(1))
    else if string.charAt(0) == plus then loop(string.substring(1), sign = 1)
    else if string.charAt(0) == minus then loop(string.substring(1), sign = -1)
    else loop(string, sign = 1)