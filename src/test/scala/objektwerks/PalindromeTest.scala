package objektwerks

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

import Palindrome.*

class PalindromeTest extends AnyFunSuite with Matchers:
  test("palindrome") {
    isPalindrome( List('a', 'a', 'b', 'b')) shouldBe false
    isPalindrome( List('a', 'b', 'a')) shouldBe true
  }