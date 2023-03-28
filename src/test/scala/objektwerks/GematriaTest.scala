package objektwerks

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

import Gematria.*

class GematriaTest extends AnyFunSuite with Matchers:
  test("ordinal") {
    deciper( ordinalMap, "hello world") shouldBe 124
  }

  test("reverse ordinal") {
    deciper( reverseOrdinalMap, "hello world") shouldBe 146
  }

  test("reduction") {
    deciper( reductionMap, "hello world") shouldBe 52
  }

  test("reverse reduction") {
    deciper( reverseReductionMap, "hello world") shouldBe 47
  }

  test("standard") {
    deciper( standardMap, "hello world") shouldBe 817
  }