package objektwerks

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

import Recursion.*

final class RecursionTest extends AnyFunSuite with Matchers:
  test("sum"):
    sum( Nil ) shouldBe 0
    sum( List(1, 2, 3) ) shouldBe 6

  test("product"):
    product( Nil ) shouldBe 1
    product( List(1, 2, 3) ) shouldBe 6

  test("reverse"):
    reverse( List(1, 2, 3) ) shouldBe List(3, 2, 1)

  test("find nth element from right"):
    val xs = (1 to 10).toList
    findNthElementFromRight(xs, 4) shouldBe Some(7)
    findNthElementFromRight(xs, 15) shouldBe None

  test("factorial"):
    factorial(3) shouldBe 6

  test("fibbonaci"):
    fibonacci(39) shouldBe 63245986

  test("intersect lists"):
    val listA = (1 to 10).toList
    val listB = (5 to 15).toList
    val listIntersection = List(5, 6, 7, 8, 9, 10)
    intersectLists(listA, listB) shouldBe listIntersection
    ( listA intersect listB ) shouldBe listIntersection