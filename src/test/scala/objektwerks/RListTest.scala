package objektwerks

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

import RList._

class RListTest extends AnyFunSuite with Matchers {
  test("list") {
    val cons = 1 :: 2 :: 3 :: RNil
    cons.isEmpty shouldBe false
  }
}