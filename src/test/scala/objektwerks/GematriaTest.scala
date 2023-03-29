package objektwerks

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

import Gematria.*

class GematriaTest extends AnyFunSuite with Matchers:
  val text = "hello world"

  test("ordinal") {
    encipher( ordinalCiper, text) shouldBe 124
  }

  test("reverse ordinal") {
    encipher( reverseOrdinalCipher, text) shouldBe 146
  }

  test("reduction") {
    encipher( reductionCiper, text) shouldBe 52
  }

  test("reverse reduction") {
    encipher( reverseReductionCipher, text) shouldBe 47
  }

  test("standard") {
    encipher( standardCipher, text) shouldBe 817
  }

  test("reverse standard") {
    encipher( reverseStandardCipher, text) shouldBe 1253
  }

  test("latin") {
    encipher( latinCipher, text) shouldBe 1157
  }

  test("sumerian") {
    encipher( sumerianCipher, text) shouldBe 744
  }

  test("reverse sumerian") {
    encipher( reverseSumerianCipher, text) shouldBe 876
  }