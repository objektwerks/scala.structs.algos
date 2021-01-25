package objektwerks

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

class BlockChainTest extends AnyFunSuite with Matchers {
  test("blockchain") {
    val chain = new Chain[String]()
    chain.timestamp should be > 0L
    chain.hash.nonEmpty shouldBe true

    val genesis = Block[String](previousHash = Hash.sha3256("genesis"), value = "genesis")
    genesis.timestamp should be > 0L
    genesis.hash.nonEmpty shouldBe true
    chain.addBlock(genesis) shouldBe true

    val first = Block[String](previousHash = genesis.previousHash, value = "first")
    first.timestamp should be > 0L
    first.hash.nonEmpty shouldBe true
    chain.addBlock(first) shouldBe true

    chain.getBlock(genesis.hash) shouldBe Some(genesis)
    chain.getBlock(first.hash) shouldBe Some(first)

    chain.getBlocks.size shouldBe 2
    chain.hash.nonEmpty shouldBe true
  }
}