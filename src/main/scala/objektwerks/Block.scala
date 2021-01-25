package objektwerks

sealed trait Entity extends Product with Serializable

final case class Block[T](timestamp: Long,
                          hash: String,
                          previousHash: String,
                          data: List[Transaction[T]]) extends Entity

final case class Transaction[T](timestamp: Long,
                                hash: String,
                                data: T) extends Entity

final case class Chain[T](timestamp: Long,
                          hash: String,
                          blocks: List[Block[T]]) extends Entity