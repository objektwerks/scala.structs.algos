package objektwerks

import Entity.dateTimeInMillis
import Hash.Hash
import ProofOfWork.ProofOfWork

sealed trait Entity extends Product with Serializable
object Entity {
  import java.sql.Timestamp
  import java.util.Date

  def dateTimeInMillis: Long = new Timestamp( new Date().getTime ).getTime
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

final case class HashBlock[T](hash: Hash, block: Block[T]) extends Entity

final case class BlockChain[T](timestamp: Long = dateTimeInMillis) extends Entity {
  import scala.collection.mutable

  private val chain = mutable.LinkedHashMap.empty[Hash, Block[T]]

  def hash: String = Hash.sha3256( chain.keys.fold( dateTimeInMillis.toString )(_ + _) )

  def genesis: HashBlock[T] = toHashBlock( chain.head )

  def last: HashBlock[T] = toHashBlock( chain.last )

  def add(block: Block[T]): Boolean =
    if ( chain.contains(block.hash) ) {
      false
    } else {
      chain += block.hash -> block
      true
    }

  def get(hash: Hash): Option[Block[T]] = chain.get(hash)

  def list: Map[Hash, Block[T]] = chain.toMap

  private def toHashBlock(tuple: (Hash, Block[T])): HashBlock[T] = HashBlock( tuple._1, tuple._2 )
}