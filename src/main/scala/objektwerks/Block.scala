package objektwerks

final case class Block[T](timestamp: Long,
                          hash: String,
                          previousHash: String,
                          data: List[T])