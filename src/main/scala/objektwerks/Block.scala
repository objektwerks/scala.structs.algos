package objektwerks

import objektwerks.Entity._

sealed trait Entity extends Product with Serializable
object Entity {
  import java.sql.Timestamp
  import java.time.Instant

  def timeStamp: Long = Timestamp.from(Instant.now).getTime

  def hash(value: String): String = Hash.sha3256(value)

  def proof(hash: String): Long = ProofOfWork.proof(hash)
}

final case class Block[T](timestamp: Long = timeStamp,
                          hash: String,
                          previousHash: String,
                          proof: Long,
                          data: List[Transaction[T]]) extends Entity

final case class Transaction[T](timestamp: Long = timeStamp,
                                hash: String,
                                data: T) extends Entity

final case class Chain[T](timestamp: Long = timeStamp,
                          hash: String,
                          blocks: List[Block[T]]) extends Entity