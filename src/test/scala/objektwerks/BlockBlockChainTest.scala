package objektwerks

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

class BlockBlockChainTest extends AnyFunSuite with Matchers {
  test("blockchain") {
    val genesisBlock = Block[String](previousHash = "0", value = "0")

    val blockChain = new BlockChain[String](genesisBlock)
    blockChain.list.size shouldBe 1
    val blockChainHash = blockChain.hash

    val firstBlock = Block[String](previousHash = blockChain.last.hash, value = "first")
    firstBlock.previousHash shouldBe genesisBlock.hash
    blockChain.add(firstBlock) shouldBe true
    blockChain.list.size shouldBe 2
    (blockChainHash != blockChain.hash) shouldBe true
    blockChain.get(firstBlock.hash) shouldBe Some(firstBlock)
    blockChain.last.hash shouldBe firstBlock.hash
    blockChain.last.block shouldBe firstBlock

    val secondBlock = Block[String](previousHash = blockChain.last.hash, value = "second")
    secondBlock.previousHash shouldBe firstBlock.hash
    blockChain.add(secondBlock) shouldBe true
    blockChain.list.size shouldBe 3
    blockChain.get(secondBlock.hash) shouldBe Some(secondBlock)
    blockChain.last.hash shouldBe secondBlock.hash
    blockChain.last.block shouldBe secondBlock
  }
}