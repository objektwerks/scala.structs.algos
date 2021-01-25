package objektwerks

object ProofOfWork {
  type ProofOfWork = Long

  import scala.annotation.tailrec

  def solve(lastHash: String): ProofOfWork = {
    @tailrec
    def loop(lastHash: String, proof: Long): ProofOfWork = {
      if ( isValid(lastHash, proof) )
        proof
      else
        loop(lastHash, proof + 1)
    }
    loop(lastHash, 0)
  }

  private def isValid(lastHash: String, proof: Long): Boolean = {
    val guess = lastHash ++ proof.toString
    val guessHash = Hash.sha3256(guess)
    (guessHash take 4) == "0000"
  }
}