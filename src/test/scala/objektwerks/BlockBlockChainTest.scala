package objektwerks

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

class BlockBlockChainTest extends AnyFunSuite with Matchers {
  test("blockchain") {
    val blockChain = new BlockChain[String]( genesisBlock = Block[String](value = "0") )
    blockChain.count shouldBe 1
    val blockChainHash = blockChain.hash

    val firstBlock = Block[String](blockChain = blockChain, value = "first")
    firstBlock.previousHash shouldBe blockChain.genesisBlock.hash
    blockChain.add(firstBlock) shouldBe true
    blockChain.count shouldBe 2
    (blockChainHash != blockChain.hash) shouldBe true
    blockChain.get(firstBlock.hash) shouldBe Some(firstBlock)
    blockChain.last.hash shouldBe firstBlock.hash
    blockChain.last.block shouldBe firstBlock

    val secondBlock = Block[String](blockChain = blockChain, value = "second")
    secondBlock.previousHash shouldBe firstBlock.hash
    blockChain.add(secondBlock) shouldBe true
    blockChain.count shouldBe 3
    blockChain.get(secondBlock.hash) shouldBe Some(secondBlock)
    blockChain.last.hash shouldBe secondBlock.hash
    blockChain.last.block shouldBe secondBlock
  }
}