package objektwerks

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

import BigO._

class BigOTest extends AnyFunSuite with Matchers {
  test("constant time - O(1)") {
    constantTime((1 to 10).toArray[Int])
  }

  test("linear time - O(n)") {
    linearTime((1 to 10).toArray[Int])
  }

  test("binary search - O(log N)") {
    val values = List( Value(10), Value(9), Value(8), Value(7), Value(6), Value(5), Value(4), Value(3), Value(3), Value(2), Value(1) )
    values.sorted
    values.sorted.search(Value(3))
  }

  test("quadratic time - O(n^2)") {
    quadraticTime()
  }

  test("exponential time - O(2^N)") {
    fibonacci(39)
  }

  test("factorial time - O(N!)") {
    val listA = (1 to 10).toList
    val listB = (5 to 15).toList
    val intersectListsResult = intersectLists(listA, listB)
    val intersectSdkResult = listA intersect listB
    intersectListsResult == intersectSdkResult
  }
}