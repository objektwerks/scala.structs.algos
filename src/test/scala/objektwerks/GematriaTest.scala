package objektwerks

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

import Gematria.*

class GematriaTest extends AnyFunSuite with Matchers:
  val string = "hello world"

  test("ordinal") {
    deciper( ordinalCiper, string) shouldBe 124
  }

  test("reverse ordinal") {
    deciper( reverseOrdinalCipher, string) shouldBe 146
  }

  test("reduction") {
    deciper( reductionCiper, string) shouldBe 52
  }

  test("reverse reduction") {
    deciper( reverseReductionCipher, string) shouldBe 47
  }

  test("standard") {
    deciper( standardCipher, string) shouldBe 817
  }

  test("latin") {
    deciper( latinCipher, string) shouldBe 1157
  }