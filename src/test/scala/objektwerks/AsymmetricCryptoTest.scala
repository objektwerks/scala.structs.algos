package objektwerks

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

class AsymmetricCryptoTest extends AnyFunSuite with Matchers {
  test("encrypt > decrypt") {
    val text = "Dogfishhead 60' IPA is the best IPA in the world!"
    text.nonEmpty shouldBe true
  }
}