package objektwerks

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

import Eval.*
import Numbers.*

class NumbersTest extends AnyFunSuite with Matchers:
  test("is prime") {
    isPrime(11) shouldBe true
    isPrime(15) shouldBe false
    isPrime(0) shouldBe false
    isPrime(1) shouldBe false
    isPrime(-1) shouldBe false
  }

  test("list primes") {
    listPrimes(0 to 1000).length shouldBe 168
    toPrimes(0 to 1000).length shouldBe 168
  }

  test("find prime rank") {
    findPrimeRank( listPrimes(0 to 1000), 101 ) shouldBe 26
    findPrimeRank( listPrimes(0 to 100), 101 ) shouldBe 0
  }

  test("is square") {
    isSquare(4) shouldBe true
    isSquare(3) shouldBe false
  }

  test("is cube") {
    isCube(64) shouldBe true
    isCube(9) shouldBe false
  }

  test("is star") {
    isStar(37) shouldBe true
    isStar(10) shouldBe false
  }

  test("is triangualar") {
    isTriangular(1) shouldBe true
    isTriangular(10) shouldBe true
    isTriangular(0) shouldBe false
    isTriangular(9) shouldBe false
  }

  test("find tetrahedral") {
    findTetrahedral(5) shouldBe 35
    findTetrahedral(6) shouldBe 56
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

  test("fractionToRecurringDecimals") {
    fractionToRecurringDecimals(1, 3) shouldBe "0.(3)"
    fractionToRecurringDecimals(1, 2) shouldBe "0.5"
    fractionToRecurringDecimals(1, 6) shouldBe "0.1(6)"
    fractionToRecurringDecimals(4, 2) shouldBe "2"
    fractionToRecurringDecimals(-1, 2) shouldBe "-0.5"
  }

  test("eval") {
    eval("1 + 2") shouldBe 3
    eval("4 - 1") shouldBe 3
    eval("1 * 3") shouldBe 3
    eval("9 / 3") shouldBe 3
    eval("1 + 2 * 3 + 4 / 5 + 6 * 7 - 8") shouldBe 41
  }

  test("largest number") {
    largestNumber(List()) shouldBe 0
    largestNumber(List(1)) shouldBe 1
    largestNumber(List(11, 1)) shouldBe 111
    largestNumber(List(1, 2, 3, 4, 5, 6)) shouldBe 654321
  }

  test("reverse integer") {
    reverseInteger(0) shouldBe 0
    reverseInteger(3) shouldBe 3
    reverseInteger(36) shouldBe 63
    reverseInteger(540) shouldBe 45
    reverseInteger(-9) shouldBe -9
    reverseInteger(-53) shouldBe -35
  }

  test("parse integer") {
    parseInteger("") shouldBe 0
    parseInteger("s") shouldBe 0
    parseInteger("1") shouldBe 1
    parseInteger("-1") -1
    parseInteger("   369") shouldBe 369
    parseInteger("   -963") shouldBe -963
  }