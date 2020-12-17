package objektwerks

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

import Numbers._

class NumbersTest extends AnyFunSuite with Matchers {
  test("is prime") {
    isPrime(11) shouldBe true
    isPrime(15) shouldBe false
    isPrime(0) shouldBe false
    isPrime(1) shouldBe false
    isPrime(-1) shouldBe false
  }

  test("listFactors") {
    listFactors(11) shouldBe List(11)
    listFactors(15) shouldBe List(5, 3)
    listFactors(21) shouldBe List(7, 3)
    listFactors(0) shouldBe List()
  }

  test("approximatePi") {
    approximatePi(100_000) should be >  3.1
  }
}