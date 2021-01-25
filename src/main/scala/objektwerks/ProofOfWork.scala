package objektwerks

final case class ProofOfWork(value: Long) extends Entity

object ProofOfWork {
  import scala.annotation.tailrec

  def solve(lastHash: String): ProofOfWork = {
    @tailrec
    def loop(lastHash: String, proof: Long): ProofOfWork = {
      if ( isValid(lastHash, proof) )
        ProofOfWork( proof )
      else
        loop(lastHash, proof + 1)
    }
    loop(lastHash, 0)
  }

  private def isValid(lastHash: String, proof: Long): Boolean = {
    val guess = lastHash ++ proof.toString
    val guessHash = Hash.sha3256(guess)
    (guessHash.value take 4) == "0000"
  }
}