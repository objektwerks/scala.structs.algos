package objektwerks

import Entity.dateTimeInMillis
import Hash.Hash
import ProofOfWork.ProofOfWork

sealed trait Entity extends Product with Serializable
object Entity {
  import java.sql.Timestamp
  import java.util.Date

  def dateTimeInMillis: Long = new Timestamp(new Date().getTime).getTime
}

final case class Block[T](timestamp: Long,
                          hash: Hash,
                          previousHash: Hash,
                          proofOfWork: ProofOfWork,
                          value: T) extends Entity

object Block {
  def apply[T](previousHash: Hash, value: T): Block[T] = {
    val timestamp = dateTimeInMillis
    val hash = Hash.sha3256( timestamp.toString + previousHash + value.toString )
    val proofOfWork = ProofOfWork.solve(hash)
    Block[T](timestamp, hash, previousHash, proofOfWork, value)
  }
}

final case class Chain[T](timestamp: Long = dateTimeInMillis) extends Entity {
  import scala.collection.mutable

  private val chain = mutable.LinkedHashMap.empty[Hash, Block[T]]

  def hash: String = Hash.sha3256( chain.keys.fold( dateTimeInMillis.toString )(_ + _) )

  def genesis: (Hash, Block[T]) = chain.head

  def last: (Hash, Block[T]) = chain.last

  def add(block: Block[T]): Boolean =
    if ( chain.contains(block.hash) ) {
      false
    } else {
      chain += block.hash -> block
      true
    }

  def get(hash: Hash): Option[Block[T]] = chain.get(hash)

  def list: Map[Hash, Block[T]] = chain.toMap
}