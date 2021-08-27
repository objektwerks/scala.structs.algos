package objektwerks

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

class RLETest extends AnyFunSuite with Matchers {
  test("encode") {
    println( s"*** RLE of aaaabbcccaeeeee : ${ RLE.encode("aaaabbcccaeeeee") }" )
    RLE.encode("aaaabbcccaeeeee") shouldBe "a4b2c3a1e5"
    RLE.encode("") shouldBe ""
    println( s"*** RLE of aaaaaaaaaaaaaaaaa : ${ RLE.encode("aaaaaaaaaaaaaaaaa") }" )
    RLE.encode("aaaaaaaaaaaaaaaaa") shouldBe "a17"
  }

  test("decode") {
    println( s"*** RLE of a4b2c3a1e5 : ${ RLE.decode("a4b2c3a1e5") }" )
    RLE.decode("a4b2c3a1e5") shouldBe "aaaabbcccaeeeee"
    RLE.decode("") shouldBe ""
    println( s"*** RRLE of a17 : ${ RLE.decodex("a17") }" )
    RLE.decodex("a17").length shouldBe 17
  }
}