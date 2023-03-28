package objektwerks

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

import Gematria.*

class GematriaTest extends AnyFunSuite with Matchers:
  test("ordinal") {
    deciper( ordinalMap, "hello world") shouldBe 124
    deciper( reverseOrdinalMap, "hello world") shouldBe 146
  }