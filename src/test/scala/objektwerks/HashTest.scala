package objektwerks

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

class HashTest extends AnyFunSuite with Matchers {
  test("hash") {
    val text = "Dogfishhead 60' IPA is the best IPA in the world!"
    val hash = Hash.sha3256(text)

    hash.length shouldBe 64
    hash shouldBe Hash.sha3256(text)
    hash should not be Hash.sha3256(text + "!!")
  }
}