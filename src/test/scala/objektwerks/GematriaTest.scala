package objektwerks

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

import Gematria.*

class GematriaTest extends AnyFunSuite with Matchers:
  test("ordinal") {
    deciper( ordinalMap, "hello world") shouldBe 124
    deciper( reverseOrdinalMap, "hello world") shouldBe 146
    deciper( reductionMap, "hello world") shouldBe 52
    deciper( reverseReductionMap, "hello world") shouldBe 47
  }