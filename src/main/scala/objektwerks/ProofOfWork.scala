package objektwerks

object ProofOfWork {
  import scala.annotation.tailrec

  type ProofOfWork = Long

  def mine(blockHash: String, difficulty: Int = 4): ProofOfWork = {
    @tailrec
    def loop(hash: String, proof: Long): ProofOfWork =
      if ( isValid(hash, proof, difficulty) ) proof
      else loop(hash, proof + 1)

    loop(blockHash, 0L)
  }

  private def isValid(hash: String, proof: Long, difficulty: Int): Boolean = {
    val guess = hash ++ proof.toString
    val guessHash = Hash.sha3256(guess)
    ( guessHash take difficulty ) == ( "0" * difficulty )
  }
}