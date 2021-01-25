package objektwerks

object ProofOfWorkBuilder {
  import scala.annotation.tailrec

  def build(lastHash: String): Long = {
    @tailrec
    def loop(lastHash: String, proof: Long): Long = {
      if ( isProofValid(lastHash, proof) )
        proof
      else
        loop(lastHash, proof + 1)
    }
    loop(lastHash, 0)
  }

  private def isProofValid(lastHash: String, proof: Long): Boolean = {
    val guess = lastHash ++ proof.toString
    val guessHash = HashBuilder.build(guess)
    (guessHash take 4) == "0000"
  }
}