package objektwerks

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

import Gematria.*

class GematriaTest extends AnyFunSuite with Matchers:
  val string = "hello world"

  test("ordinal") {
    encipher( ordinalCiper, string) shouldBe 124
  }

  test("reverse ordinal") {
    encipher( reverseOrdinalCipher, string) shouldBe 146
  }

  test("reduction") {
    encipher( reductionCiper, string) shouldBe 52
  }

  test("reverse reduction") {
    encipher( reverseReductionCipher, string) shouldBe 47
  }

  test("standard") {
    encipher( standardCipher, string) shouldBe 817
  }

  test("reverse standard") {
    encipher( reverseStandardCipher, string) shouldBe 1253
  }

  test("latin") {
    encipher( latinCipher, string) shouldBe 1157
  }

  test("sumerian") {
    encipher( sumerianCipher, string) shouldBe 744
  }

  test("reverse sumerian") {
    encipher( reverseSumerianCipher, string) shouldBe 876
  }