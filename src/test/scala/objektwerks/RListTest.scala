package objektwerks

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

import RList._

class RListTest extends AnyFunSuite with Matchers {
  test("list") {
    val cons = 1 :: 2 :: 3 :: RNil
    cons.isEmpty shouldBe false
    cons.length shouldBe 3
    cons(2) shouldBe 3
    cons.reverse shouldBe 3 :: 2 :: 1 :: RNil
    (cons ++ (4 :: RNil)) shouldBe 1 :: 2 :: 3 :: 4 :: RNil
    (cons -= 2) shouldBe 1 :: 2 :: RNil
  }

  test("iterable") {
    val cons = RList.from(1 to 1000)
    cons.length shouldBe 1000
    cons(500) shouldBe 500
  }
}