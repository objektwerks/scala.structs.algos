package objektwerks

final case class Block[T](hash: Hash,
                          previousHash: Hash,
                          proofOfWork: ProofOfWork,
                          value: T) extends Entity

final case class Chain[T]() extends Entity {
  import scala.collection.mutable

  private val blocks = mutable.Map.empty[Hash, Block[T]]

  def addBlock(block: Block[T]): Unit = blocks += block.hash -> block

  def getBlock(hash: Hash): Option[Block[T]] = blocks.get(hash)
}