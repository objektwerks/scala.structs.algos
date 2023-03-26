package objektwerks

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

class PalindromeTest extends AnyFunSuite with Matchers:
  test("palindrome") {
    import Palindrome.*

    isPalindrome( List('a', 'a', 'b', 'b')) shouldBe false
  }