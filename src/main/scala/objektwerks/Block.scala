package objektwerks

sealed trait Entity extends Product with Serializable
object Entity {
  import java.sql.Timestamp
  import java.time.Instant

  def timeStamp: Long = Timestamp.from(Instant.now).getTime

  def hash(value: String): String = Hash.sha3256(value)

  def proof(hash: String): Long = ProofOfWork.proof(hash)
}

final case class Block[T](timestamp: Long,
                          hash: String,
                          previousHash: String,
                          proof: Long,
                          transactions: List[Transaction[T]]) extends Entity

final case class Transaction[T](timestamp: Long,
                                hash: String,
                                data: T) extends Entity

final case class Chain[T](timestamp: Long,
                          hash: String,
                          blocks: List[Block[T]]) extends Entity