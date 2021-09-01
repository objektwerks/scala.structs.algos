package objektwerks

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

import scala.util.Random

class RLETest extends AnyFunSuite with Matchers {
  test("encode") {
    println( s"*** RLE of aaaabbcccaeeeee : ${ RLE.encode("aaaabbcccaeeeee") }" )
    RLE.encode("aaaabbcccaeeeee") shouldBe "a4b2c3a1e5"

    println( s"*** RLE of empty string : ${ RLE.encode("") }" )
    RLE.encode("") shouldBe ""

    println( s"*** RLE of aaaaaaaaaaaaaaaaa : ${ RLE.encode("aaaaaaaaaaaaaaaaa") }" )
    RLE.encode("aaaaaaaaaaaaaaaaa") shouldBe "a17"

    println( s"*** RLE of 123 : ${ RLE.encode("123") }" )
    RLE.encode("123") shouldBe ""
  }

  test("decode") {
    println( s"*** RLD of a4b2c3a1e5 : ${ RLE.decode("a4b2c3a1e5") }" )
    RLE.decode("a4b2c3a1e5") shouldBe "aaaabbcccaeeeee"

    println( s"*** RLD of empty string : ${ RLE.decode("") }" )
    RLE.decode("") shouldBe ""

    println( s"*** RLD of a17 : ${ RLE.decode("a17") }" )
    RLE.decode("a17").length shouldBe 17

    println( s"*** RLD of 112131 : ${ RLE.decode("112131") }" )
    RLE.decode("112131") shouldBe ""
  }

  test("encode > decode letters") {
    for( i <- 1 to 5 ) {
      val random = Random.alphanumeric.filter(_.isLetter).map(_.toString * i).take(i).mkString
      val encoded = RLE.encode(random)
      val decoded = RLE.decode(encoded)
      println(s"$i random: $random - encoded: $encoded - decoded: $decoded")
      random shouldBe decoded
    }
  }

  test("encode > decode digits") {
    for( i <- 1 to 5 ) {
      val random = Random.alphanumeric.filter(_.isDigit).map(_.toString * i).take(i).mkString
      val encoded = RLE.encode(random)
      val decoded = RLE.decode(encoded)
      println(s"$i random: $random - encoded: $encoded - decoded: $decoded")
      encoded shouldBe ""
      decoded shouldBe ""
    }
  }

  test("pipe > tap > pipe > tap") {
    import scala.util.chaining._
    "aaaabbcccaeeeee"
      .pipe(RLE.encode)
      .tap(encoded => encoded shouldBe "a4b2c3a1e5")
      .pipe(RLE.decode)
      .tap(decoded => decoded shouldBe "aaaabbcccaeeeee")
  }
}