package objektwerks

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

class ProofOfWorkTest extends AnyFunSuite with Matchers {
  test("proof of work") {
    val text = "Dogfishhead 60' IPA is the best IPA in the world!"
    val hash = Hash.sha3256(text)
    ProofOfWork.mine(hash, 4) > 0 shouldBe true
  }
}