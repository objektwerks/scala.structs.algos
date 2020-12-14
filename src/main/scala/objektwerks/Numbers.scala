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
    loop(2)
  }
}