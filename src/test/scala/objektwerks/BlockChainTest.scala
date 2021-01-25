package objektwerks

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

class BlockChainTest extends AnyFunSuite with Matchers {
  test("blockchain") {
    val chain = new Chain[String]()
    val chainHash = chain.hash
    chain.timestamp should be > 0L
    chainHash.nonEmpty shouldBe true

    val genesis = Block[String](previousHash = Hash.sha3256("genesis"), value = "genesis")
    val genesisHash = genesis.hash
    genesis.timestamp should be > 0L
    genesisHash.nonEmpty shouldBe true
    chain.addBlock(genesis) shouldBe true

    val first = Block[String](previousHash = genesis.previousHash, value = "first")
    val firstHash = first.hash
    first.timestamp should be > 0L
    firstHash.nonEmpty shouldBe true
    chain.addBlock(first) shouldBe true

    chain.getBlock(genesisHash) shouldBe Some(genesis)
    chain.getBlock(genesisHash).get.hash shouldBe genesisHash

    chain.getBlock(firstHash) shouldBe Some(first)
    chain.getBlock(firstHash).get.hash shouldBe firstHash

    chain.getBlocks.size shouldBe 2
    (chain.hash == chainHash) shouldBe false
  }
}