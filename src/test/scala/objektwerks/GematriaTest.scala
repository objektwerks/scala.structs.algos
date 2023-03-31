package objektwerks

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

import Gematria.*

class GematriaTest extends AnyFunSuite with Matchers:
  val text = "hello world"

  test("pi") { // see - https://grahamhancock.com/leedsm1/
    encipher( piCiper, "lord") shouldBe 13
    encipher( piCiper, "god") shouldBe 13
    encipher( piCiper, "lordgod") shouldBe 26
    encipher( piCiper, "onetwothreefourfivesix") shouldBe 103 // 103 * 2 = 206, number bones in human skeleton
    encipher( piCiper, "seven") shouldBe 22 // divided by 7 equals 3.14 pi
 }

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

  test("encipher to map") {
    encipherToMap( ordinalCiper, text) shouldBe List(('h',8), ('e',5), ('l',12), ('l',12), ('o',15), ('w',23), ('o',15), ('r',18), ('l',12), ('d',4))
    encipherToMap( ordinalCiper, text).map( (l, i) => l).mkString shouldBe "helloworld"
    encipherToMap( ordinalCiper, text).map( (l, i) => i).mkString shouldBe "85121215231518124"
  }