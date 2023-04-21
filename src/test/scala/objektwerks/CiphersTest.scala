package objektwerks

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

import Ciphers.*

class CiphersTest extends AnyFunSuite with Matchers:
  val text = "hello world"

  test("ordinal") {
    encipher(ordinalCipher, text) shouldBe 124
  }

  test("reverse ordinal") {
    encipher(reverseOrdinalCipher, text) shouldBe 146
  }

  test("reduction") {
    encipher(reductionCipher, text) shouldBe 52
  }

  test("reverse reduction") {
    encipher(reverseReductionCipher, text) shouldBe 47
  }

  test("standard") {
    encipher(standardCipher, text) shouldBe 817
  }

  test("reverse standard") {
    encipher(reverseStandardCipher, text) shouldBe 1253
  }

  test("latin") {
    encipher(latinCipher, text) shouldBe 1157
  }

  test("sumerian") {
    encipher(sumerianCipher, text) shouldBe 744
  }

  test("reverse sumerian") {
    encipher(reverseSumerianCipher, text) shouldBe 876
  }

  test("primes") {
    encipher(primesCipher, text) shouldBe 386
  }

  test("reverse primes") {
    encipher(reversePrimesCipher, text) shouldBe 474
  }

  test("fibonacci") {
    encipher(fibonacciCipher, text) shouldBe 786
  }

  test("squares") {
    encipher(squaresCipher, text) shouldBe 1840
  }

  test("reverse squares") {
    encipher(reverseSquaresCipher, text) shouldBe 2434
  }

  test("trigonal") {
    encipher(trigonalCipher, text) shouldBe 982
  }

  test("reverse trigonal") {
    encipher(reverseTrigonalCipher, text) shouldBe 1290
  }

  test("single reduction") {
    encipher(singleReductionCipher, text) shouldBe 52
  }

  test("reverse single reduction") {
    encipher(reverseSingleReductionCipher, text) shouldBe 56
  }

  test("keypad") {
    encipher(keypadCipher, text) shouldBe 53
  }

  test("chaldean") {
    encipher(chaldeanCipher, text) shouldBe 45
  }

  test("septenary") {
    /* see:
      1. https://grahamhancock.com/leedsm1/
      2. https://truthscrambler.com/2019/03/24/the-predominance-and-occultation-of-the-septenary-cypher-marty-leeds/
    */
    encipher(septenaryCipher, "lord") shouldBe 13
    encipher(septenaryCipher, "god") shouldBe 13
    encipher(septenaryCipher, "lordgod") shouldBe 26
    encipher(septenaryCipher, "adameve") shouldBe 22
    encipher(septenaryCipher, "jesus") shouldBe 27 // 3 x 3 x 3

    val dice = "onetwothreefourfivesix"
    encipher(septenaryCipher, dice) shouldBe 103 // 103
    encipher(septenaryCipher, dice + dice) shouldBe 206 // 103 * 2 = 206, number of bones in a human skeleton

    encipher(septenaryCipher, "abcdef") shouldBe 21
    encipher(septenaryCipher, "g") shouldBe 7
    encipher(septenaryCipher, "hijklm") shouldBe 21

    encipher(septenaryCipher, "nopqrs") shouldBe 21
    encipher(septenaryCipher, "t") shouldBe 7
    encipher(septenaryCipher, "uvwxyz") shouldBe 21

    encipher(septenaryCipher, "seven") shouldBe 22 // divided by 7 equals 3.14 pi
  }

  test("satanic") {
    encipher(satanicCipher, text) shouldBe 474
  }

  test("reverse satanic") {
    encipher(reverseSatanicCipher, text) shouldBe 496
  }

  test("encipher to map") {
    encipherToMap( ordinalCipher, text) shouldBe List(('h',8), ('e',5), ('l',12), ('l',12), ('o',15), ('w',23), ('o',15), ('r',18), ('l',12), ('d',4))
    encipherToMap( ordinalCipher, text).map( (l, i) => l).mkString shouldBe "helloworld"
    encipherToMap( ordinalCipher, text).map( (l, i) => i).mkString shouldBe "85121215231518124"
  }