package objektwerks

object ProofOfWork {
  import scala.annotation.tailrec

  def proof(lastHash: String): Long = {
    @tailrec
    def loop(lastHash: String, proof: Long): Long = {
      if ( isProofValid(lastHash, proof) )
        proof
      else
        loop(lastHash, proof + 1)
    }
    loop(lastHash, 0)
  }

  def isProofValid(lastHash: String, proof: Long): Boolean = {
    val guess = lastHash ++ proof.toString
    val guessHash = Hash.sha3256(guess)
    (guessHash take 4) == "0000"
  }
}