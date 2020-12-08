package objektwerks

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

import RList._

class RListTest extends AnyFunSuite with Matchers {
  test("rlist") {
    val rlist = 1 :: 2 :: 3 :: RNil
    rlist.isEmpty shouldBe false
    rlist.length shouldBe 3
    rlist(2) shouldBe 3
    rlist.reverse shouldBe 3 :: 2 :: 1 :: RNil
    (rlist ++ (4 :: RNil)) shouldBe 1 :: 2 :: 3 :: 4 :: RNil
    (rlist -= 2) shouldBe 1 :: 2 :: RNil
  }

  test("iterable") {
    val rlist = RList.iterable(1 to 1000)
    rlist.length shouldBe 1000
    rlist(500) shouldBe 500
  }
}