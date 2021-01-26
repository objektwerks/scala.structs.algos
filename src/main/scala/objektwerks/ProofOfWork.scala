package objektwerks

object ProofOfWork {
  type ProofOfWork = Long

  import scala.annotation.tailrec

  def solve(lastHash: String, difficulty: Int = 4): ProofOfWork = {
    @tailrec
    def loop(lastHash: String, proof: Long): ProofOfWork = {
      if ( isValid(lastHash, proof, difficulty) )
        proof
      else
        loop(lastHash, proof + 1)
    }
    loop(lastHash, 0)
  }

  private def isValid(lastHash: String, proof: Long, difficulty: Int): Boolean = {
    val guess = lastHash ++ proof.toString
    val guessHash = Hash.sha3256(guess)
    ( guessHash take difficulty ) == ( "0" * difficulty )
  }
}