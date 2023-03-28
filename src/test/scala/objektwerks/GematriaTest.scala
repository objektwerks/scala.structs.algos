package objektwerks

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

import Gematria.*

class GematriaTest extends AnyFunSuite with Matchers:
  test("ordinal") {
    deciper( ordinalCiper, "hello world") shouldBe 124
  }

  test("reverse ordinal") {
    deciper( reverseOrdinalCipher, "hello world") shouldBe 146
  }

  test("reduction") {
    deciper( reductionCiper, "hello world") shouldBe 52
  }

  test("reverse reduction") {
    deciper( reverseReductionCipher, "hello world") shouldBe 47
  }

  test("standard") {
    deciper( standardCipher, "hello world") shouldBe 817
  }

  test("latin") {
    deciper( latinMap, "hello world") shouldBe 1157
  }