package objektwerks

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

class BlockBlockChainTest extends AnyFunSuite with Matchers {
  test("blockchain") {
    val blockChain = new BlockChain[String]()
    val blockChainHash = blockChain.hash
    blockChain.timestamp should be > 0L
    blockChainHash.nonEmpty shouldBe true

    val genesisBlock = Block[String](previousHash = Hash.sha3256("genesis"), value = "genesis")
    val genesisHash = genesisBlock.hash
    genesisBlock.timestamp should be > 0L
    genesisHash.nonEmpty shouldBe true
    blockChain.add(genesisBlock) shouldBe true

    val firstBlock = Block[String](previousHash = blockChain.last.hash, value = "first")
    val firstHash = firstBlock.hash
    firstBlock.timestamp should be > 0L
    firstHash.nonEmpty shouldBe true
    blockChain.add(firstBlock) shouldBe true

    blockChain.get(genesisHash) shouldBe Some(genesisBlock)
    blockChain.get(genesisHash).get.hash shouldBe genesisHash

    blockChain.get(firstHash) shouldBe Some(firstBlock)
    blockChain.get(firstHash).get.hash shouldBe firstHash

    blockChain.list.size shouldBe 2
    (blockChain.hash == blockChainHash) shouldBe false

    val genensisHashBlock = blockChain.genesis
    genensisHashBlock.hash shouldBe genesisBlock.hash
    genensisHashBlock.block shouldBe genesisBlock

    val firstHashBlock = blockChain.last
    firstHashBlock.hash shouldBe firstBlock.hash
    firstHashBlock.block shouldBe firstBlock
  }
}