package objektwerks

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

class FizzBuzzTest extends AnyFunSuite with Matchers:
  test("fizzbuzz") {
    import FizzBuzz.*

    fizzbuzz(9) shouldBe "fizz"
    fizzbuzz(10) shouldBe "buzz"
    fizzbuzz(15) shouldBe "fizzbuzz"
  }