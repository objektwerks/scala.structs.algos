package objektwerks

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

import Recursion.*

class RecursionTest extends AnyFunSuite with Matchers:
  test("sum") {
    sum( Nil ) shouldBe 0
    sum( List(1, 2, 3) ) shouldBe 6
  }

  test("product") {
    product( Nil ) shouldBe 1
    product( List(1, 2, 3) ) shouldBe 6
  }

  test("reverse") {
    reverse( List(1, 2, 3) ) shouldBe List(3, 2, 1)
  }

  test("find nth element from right") {
    val xs = (1 to 10).toList
    findNthElementFromRight(xs, 4) shouldBe Some(7)
    findNthElementFromRight(xs, 15) shouldBe None
  }

  test("factorial") {
    factorial(3) shouldBe 6
  }

  test("fibbonaci") {
    fibonacci(39) shouldBe 63245986
  }

  test("intersect lists") {
    val listA = (1 to 10).toList
    val listB = (5 to 15).toList
    val listIntersection = List(5, 6, 7, 8, 9, 10)
    intersectLists(listA, listB) shouldBe listIntersection
    ( listA intersect listB ) shouldBe listIntersection
  }

  test("find unpaired item") {
    findUnpairedItem(List(1, 2, 1)).get shouldBe 2
    findUnpairedItem(List(1, 2, 1, 1, 1)).get shouldBe 2
    findUnpairedItem(List(1, 1, 1, 1, 1)).get shouldBe 1
    findUnpairedItem(List(1, 1, 1, 1)).isEmpty shouldBe true
    findUnpairedItem(List(1, 2, 3, 1, 2)).get shouldBe 3
    findUnpairedItem(List(1, 2, 3, 3, 1, 2)).isEmpty shouldBe true
  }

  test("find max profit") {
    findMaxProfit(Array(163, 112, 105, 100, 151)).get shouldBe 51
    findMaxProfit(Array(1)).isEmpty shouldBe true
    findMaxProfit(Array(1, 2)).get shouldBe 1
    findMaxProfit(Array(2, 1)).isEmpty shouldBe true
  }