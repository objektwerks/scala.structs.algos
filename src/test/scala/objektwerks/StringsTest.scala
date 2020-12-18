package objektwerks

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

import Strings._

class StringsTest extends AnyFunSuite with Matchers {
  test("countChars") {
    countChars("scala") shouldBe Map('a' -> 2, 'c' -> 1, 'l' -> 1, 's' -> 1)
  }

  test("checkAnagrams") {
    checkAnagrams("desserts","stressed") shouldBe true
    checkAnagrams("scala", "haskell") shouldBe false
  }

  test("checkSortedAnagrams") {
    checkSortedAnagrams("desserts","stressed") shouldBe true
    checkSortedAnagrams("scala", "haskell") shouldBe false
  }

  test("containsBalancedParens") {
    containsBalancedParens("()") shouldBe true
    containsBalancedParens("(") shouldBe false
  }
}