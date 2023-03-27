package objektwerks

import scala.annotation.tailrec
import scala.collection.mutable.ArrayBuffer

object BigO:
  // O(1) - Constant Time
  def constantTimeGetByIndex(array: Array[Int], index: Int): Int = array(index)

  // O(n) - Linear Time
  def linearTimeSum(array: Array[Int]): Int =
    var sum = 0
    for i <- array do sum = sum + i
    sum

  // O(log N) - Binary Search
  case class Value(number: Int)

  implicit def ordering: Ordering[Value] = Ordering.by(_.number)
  
  def binarySearch(buffer: ArrayBuffer[Value], value: Value): Int = buffer.search(value).insertionPoint

  // O(n^2) - Quadratic Time
  def quadraticTimeBuildMatrix(): Array[Array[Int]] =
    val matrix = Array.ofDim[Int](3, 3)
    for i <- 0 until matrix.length - 1 do
      for j <- 0 until matrix.length - 1 do
        matrix(i)(j) = j
    matrix

  // O(2^N) - Exponential Time
  def exponentialTimeFibonacci(n: Long): BigInt =
    @tailrec
    def loop(n: Long, a: Long, b: Long): BigInt =
      n match
        case 0 => a
        case _ => loop(n - 1, b, a + b)

    loop(n, 0, 1)

  // O(N!) - Factorial Time
  @tailrec
  final def factorialTimeIntersectLists[A](listA: List[A],
                                           listB: List[A],
                                           acc: List[A] = List.empty[A]): List[A] =
    listA match
      case Nil => acc
      case head :: tail =>
        if listB.contains(head) then
          factorialTimeIntersectLists(tail, listB, acc :+ head)
        else
          factorialTimeIntersectLists(tail, listB, acc)