package objektwerks

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

class BlockBlockChainTest extends AnyFunSuite with Matchers {
  test("blockchain") {
    val genesis = Block[String](previousHash = Hash.sha3256("genesis"), value = "genesis")

    val blockChain = new BlockChain[String](genesis)
    blockChain.list.size shouldBe 1
    val blockChainHash = blockChain.hash

    val firstBlock = Block[String](previousHash = blockChain.last.hash, value = "first")
    firstBlock.previousHash shouldBe genesis.hash

    blockChain.add(firstBlock) shouldBe true
    blockChain.list.size shouldBe 2
    (blockChainHash != blockChain.hash) shouldBe true
    blockChain.get(firstBlock.hash) shouldBe Some(firstBlock)

    val lastHashBlock = blockChain.last
    lastHashBlock.hash shouldBe firstBlock.hash
    lastHashBlock.block shouldBe firstBlock
  }
}