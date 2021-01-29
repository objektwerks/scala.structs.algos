package objektwerks

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

class ProofOfWorkTest extends AnyFunSuite with Matchers {
  test("proof of work") {
    val text = "Dogfishhead 60' IPA is the best IPA in the world!"
    val proofOfWork = ProofOfWork.mine( Hash.sha3256(text), 4 )

    proofOfWork shouldBe ProofOfWork.mine( Hash.sha3256(text), 4 )
    proofOfWork should not be ProofOfWork.mine( Hash.sha3256(text + "!!"), 4 )
  }
}