package objektwerks

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

import Strings._

class StringsTest extends AnyFunSuite with Matchers {
  test("count chars") {
    countChars("scala") shouldBe Map('a' -> 2, 'c' -> 1, 'l' -> 1, 's' -> 1)
  }
}