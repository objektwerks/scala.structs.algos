package objektwerks

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

class BlockBlockChainTest extends AnyFunSuite with Matchers {
  test("blockchain") {
    val blockChain = new BlockChain[String]( genesisBlock = Block[String](value = "0") )
    blockChain.count shouldBe 1
    val blockChainHash = blockChain.hash

    testBlock(blockChain, "first")
    (blockChainHash != blockChain.hash) shouldBe true
    blockChain.count shouldBe 2

    testBlock(blockChain, "second")
    blockChain.count shouldBe 3
  }

  def testBlock(blockChain: BlockChain[String], value: String): Unit = {
    val block = Block[String](blockChain = blockChain, value = value)
    blockChain.add(block) shouldBe true
    blockChain.get(block.hash) shouldBe Some(block)
    blockChain.last.block.previousHash shouldBe block.previousHash
    blockChain.last.hash shouldBe block.hash
    blockChain.last.block shouldBe block
    ()
  }
}