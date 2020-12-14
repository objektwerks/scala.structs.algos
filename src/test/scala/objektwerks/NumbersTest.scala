package objektwerks

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

import Numbers._

class NumbersTest extends AnyFunSuite with Matchers {
  test("is prime") {
    isPrime(11) shouldBe true
  }
}