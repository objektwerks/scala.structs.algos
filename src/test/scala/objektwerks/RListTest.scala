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
  }
}