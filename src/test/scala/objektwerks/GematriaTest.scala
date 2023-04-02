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

  test("primes") {
    encipher( primesCiper, text) shouldBe 386
  }

  test("reverse primes") {
    encipher( reversePrimesCiper, text) shouldBe 474
  }

  test("fibonacci") {
    encipher( fibonacciCiper, text) shouldBe 786
  }

  test("squares") {
    encipher( squaresCiper, text) shouldBe 1840
  }

  test("reverse squares") {
    encipher( reverseSquaresCiper, text) shouldBe 2434
  }

  test("trigonal") {
    encipher( trigonalCiper, text) shouldBe 982
  }

  test("reverse trigonal") {
    encipher( reverseTrigonalCiper, text) shouldBe 1290
  }

  test("single reduction") {
    encipher( singleReductionCiper, text) shouldBe 52
  }

  test("reverse single reduction") {
    encipher( reverseSingleReductionCiper, text) shouldBe 56
  }

  test("keypad") {
    encipher( keypadCiper, text) shouldBe 53
  }

  test("septenary") {
    /* see:
      1. https://grahamhancock.com/leedsm1/
      2. https://truthscrambler.com/2019/03/24/the-predominance-and-occultation-of-the-septenary-cypher-marty-leeds/
    */
    encipher( septenaryCiper, "lord") shouldBe 13
    encipher( septenaryCiper, "god") shouldBe 13
    encipher( septenaryCiper, "lordgod") shouldBe 26
    encipher( septenaryCiper, "adameve") shouldBe 22
    encipher( septenaryCiper, "jesus") shouldBe 27 // 3 x 3 x 3

    val dice = "onetwothreefourfivesix"
    encipher( septenaryCiper, dice) shouldBe 103 // 103
    encipher( septenaryCiper, dice + dice) shouldBe 206 // 103 * 2 = 206, number of bones in a human skeleton

    encipher( septenaryCiper, "abcdef") shouldBe 21
    encipher( septenaryCiper, "g") shouldBe 7
    encipher( septenaryCiper, "hijklm") shouldBe 21

    encipher( septenaryCiper, "nopqrs") shouldBe 21
    encipher( septenaryCiper, "t") shouldBe 7
    encipher( septenaryCiper, "uvwxyz") shouldBe 21

    encipher( septenaryCiper, "seven") shouldBe 22 // divided by 7 equals 3.14 pi
 }

  test("encipher to map") {
    encipherToMap( ordinalCiper, text) shouldBe List(('h',8), ('e',5), ('l',12), ('l',12), ('o',15), ('w',23), ('o',15), ('r',18), ('l',12), ('d',4))
    encipherToMap( ordinalCiper, text).map( (l, i) => l).mkString shouldBe "helloworld"
    encipherToMap( ordinalCiper, text).map( (l, i) => i).mkString shouldBe "85121215231518124"
  }