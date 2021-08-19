package objektwerks

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

class RLETest extends AnyFunSuite with Matchers {
  test("encode") {
    println( s" *** Run Length Encoding: ${ RLE.encode("aaaabbcccaeeeee") }" )
    RLE.encode("aaaabbcccaeeeee") shouldBe "a4b2c3a1e5"
  }

  test("decode") {
    println( s" *** Run Length Decoding: ${ RLE.decode("a4b2c3a1e5") }" )
    RLE.decode("a4b2c3a1e5") shouldBe "aaaabbcccaeeeee"
  }
}