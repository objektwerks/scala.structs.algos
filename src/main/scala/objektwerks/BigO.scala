package objektwerks

import scala.annotation.tailrec

object BigO {
  // O(1) - Constant Time
  def constantTime(array: Array[Int]): Int = {
    array(0)
  }

  // O(n) - Linear Time
  def linearTime(array: Array[Int]): Int = {
    var sum = 0
    for (i <- array) {
      sum = sum + i
    }
    sum
  }

  // O(log N) - Binary Search
  case class Value(number: Int)
  implicit def ordering: Ordering[Value] = Ordering.by(_.number)

  // O(n^2) - Quadratic Time
  def quadraticTime(): Array[Array[Int]] = {
    val matrix = Array.ofDim[Int](3, 3)
    for (i <- 0 to matrix.length - 1) {
      for (j <- 0 to matrix.length - 1) {
        matrix(i)(j) = j
      }
    }
    matrix
  }

  // O(2^N) - Exponential Time
  def fibonacci(n: Long): BigInt = {
    @tailrec
    def loop(n: Long, a: Long, b: Long): BigInt = n match {
      case 0 => a
      case _ => loop(n - 1, b, a + b)
    }
    loop(n, 0, 1)
  }

  // O(N!) - Factorial Time
  @tailrec
  final def intersectLists[A](listA: List[A],
                              listB: List[A],
                              acc: List[A] = List.empty[A]): List[A] =
    listA match {
      case Nil => acc
      case head :: tail =>
        if (listB.contains(head)) {
          intersectLists(tail, listB, acc :+ head)
        } else {
          intersectLists(tail, listB, acc)
        }
    }
}