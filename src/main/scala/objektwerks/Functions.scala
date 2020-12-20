package objektwerks

object Functions {
  import scala.annotation.tailrec
  import scala.util.Try

  @tailrec
  def sum(xs: List[Int], acc: Int = 0): Int = xs match {
    case Nil => acc
    case head :: tail => sum(tail, acc + head)
  }

  @tailrec
  def product(xs: List[Int], acc: Int = 1): Int = xs match {
    case Nil => acc
    case head :: tail => product(tail, acc * head)
  }

  @tailrec
  def reverse[A](list: List[A], acc: List[A] = List.empty[A]): List[A] = list match {
    case Nil => acc
    case head :: tail => reverse(tail, head :: acc)
  }

  def findNthElementFromRight[A](list: List[A], nthElement: Int): Option[A] = {
    @tailrec
    def reverse(list: List[A], acc: List[A] = List.empty[A]): List[A] = list match {
      case Nil => acc
      case head :: tail => reverse(tail, head :: acc)
    }
    Try { reverse(list)(nthElement - 1) }.toOption
  }

  @tailrec
  final def factorial(n: Int, acc: Int = 1): Int = n match {
    case i if i < 1 => acc
    case _ => factorial(n - 1, acc * n)
  }

  def fibonacci(n: Long): BigInt = {
    @tailrec
    def loop(n: Long, a: Long, b: Long): BigInt = n match {
      case 0 => a
      case _ => loop(n - 1, b, a + b)
    }
    loop(n, 0, 1)
  }

  @tailrec
  final def intersectLists[A](listA: List[A],
                              listB: List[A],
                              acc: List[A] = List.empty[A]): List[A] =
    listA match {
      case Nil => acc
      case head :: tail =>
        if (listB.contains(head)) intersectLists(tail, listB, acc :+ head)
        else intersectLists(tail, listB, acc)
    }
}