package objektwerks

final case class Block[T](timestamp: Long,
                          hash: String,
                          previousHash: String,
                          data: List[Transaction[T]])

final case class Transaction[T](timestamp: Long, hash: String, data: T)

final case class Chain[T](blocks: List[Block[T]])