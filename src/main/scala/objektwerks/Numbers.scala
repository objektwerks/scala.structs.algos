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
}