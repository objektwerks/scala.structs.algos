package objektwerks

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

import Strings.*

class StringsTest extends AnyFunSuite with Matchers:
  test("reverse words") {
    reverseWords("scala hello") shouldBe "hello scala"
  }

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

  test("buildListOfAllValidParens") {
    buildListOfAllValidParens(0) shouldBe List()
    buildListOfAllValidParens(1) shouldBe List("()")
    buildListOfAllValidParens(2) shouldBe List("()()", "(())")
  }

  test("justifyText") {
    justify(app.JustifyTextApp.text, 40).nonEmpty shouldBe true
  }