package objektwerks

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

import FizzBuzz.*

final class FizzBuzzTest extends AnyFunSuite with Matchers:
  test("fizzbuzz"):
    fizzbuzz(9) shouldBe "fizz"
    fizzbuzz(10) shouldBe "buzz"
    fizzbuzz(15) shouldBe "fizzbuzz"
    fizzbuzz(7) shouldBe "7"