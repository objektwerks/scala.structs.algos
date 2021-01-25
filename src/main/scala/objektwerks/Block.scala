package objektwerks

import Hash.Hash
import ProofOfWork.ProofOfWork

trait Entity extends Product with Serializable {
  import java.sql.Timestamp
  import java.time.Instant

  val timestamp = Timestamp.from(Instant.now).getTime
}

final case class Block[T](hash: Hash,
                          previousHash: Hash,
                          proofOfWork: ProofOfWork,
                          value: T) extends Entity

object Block {
  def apply[T](previousHash: Hash, value: T): Block[T] = {
    val hash = Hash.sha3256(value.toString)
    val proofOfWork = ProofOfWork.solve(hash)
    Block[T](hash, previousHash, proofOfWork, value)
  }
}

final case class Chain[T]() extends Entity {
  import scala.collection.mutable

  private val chain = mutable.Map.empty[Hash, Block[T]]

  def addBlock(block: Block[T]): Unit = chain += block.hash -> block

  def getBlock(hash: Hash): Option[Block[T]] = chain.get(hash)

  def getBlocks: Map[Hash, Block[T]] = chain.toMap
}