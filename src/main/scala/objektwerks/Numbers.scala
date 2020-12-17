package objektwerks

object Numbers {
  import Math._
  import scala.annotation.tailrec

  def isPrime(n: Int): Boolean = {
    @tailrec
    def loop(current: Int): Boolean = {
      if (current > sqrt(abs(n.toDouble))) true
      else n % current != 0 && loop(current + 1)
    }
    if (n == -1 || n == 0 || n == 1) false else loop(2)
  }

  def listFactors(n: Int): List[Int] = {
    require( n > 0, s"$n must be greater than 0." )
    @tailrec
    def loop(remaining: Int, currentDivisor: Int, acc: List[Int]): List[Int] = {
      if (currentDivisor > sqrt(remaining.toDouble)) remaining :: acc
      else if (remaining % currentDivisor == 0) loop(remaining / currentDivisor, currentDivisor, currentDivisor :: acc)
      else loop(remaining, currentDivisor + 1, acc)
    }
    loop(n, 2, List())
  }
}