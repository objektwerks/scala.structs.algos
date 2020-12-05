package objektwerks

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

import scala.collection.mutable.ArrayBuffer

class BigOTest extends AnyFunSuite with Matchers {
  import BigO._

  test("constant time - O(1)") {
    constantTimeGetByIndex((1 to 10).toArray[Int], 3) shouldBe 4
  }

  test("linear time - O(n)") {
    linearTimeSum( (0 to 9).toArray[Int] ) shouldBe 45
  }

  test("binary search - O(log N)") {
    val buffer = ArrayBuffer[Value]()
    for ( i <- 0 to 9 ) buffer.addOne( Value(i) )
    binarySearch(buffer, Value(3)) shouldBe 3
  }

  test("quadratic time - O(n^2)") {
    quadraticTimeBuildMatrix().length shouldBe 3
  }

  test("exponential time - O(2^N)") {
    exponentialTimeFibonacci(39) shouldBe 63245986
  }

  test("factorial time - O(N!)") {
    val listA = (1 to 10).toList
    val listB = (5 to 15).toList
    val intersectListsResult = factorialTimeIntersectLists(listA, listB)
    val intersectSdkResult = listA intersect listB
    intersectListsResult shouldEqual intersectSdkResult
  }
}