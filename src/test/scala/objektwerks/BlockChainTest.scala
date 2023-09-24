package objektwerks

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

final class BlockChainTest extends AnyFunSuite with Matchers:
  test("blockchain"):
    val blockChain = BlockChain[String]( genesisBlock = Block[String](value = "0") )
    blockChain.count shouldBe 1

    for 
      i <- 1 until 10 
    do
      val block = Block[String](blockChain = blockChain, value = i.toString)
      blockChain.add(block) shouldBe true
      blockChain.get(block.hash) shouldBe Some(block)
      blockChain.last.block.previousHash shouldBe block.previousHash
      blockChain.last.hash shouldBe block.hash
      blockChain.last.block shouldBe block
      blockChain.count shouldBe i + 1

    blockChain.isValid shouldBe true