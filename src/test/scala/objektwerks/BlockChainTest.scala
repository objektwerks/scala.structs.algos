package objektwerks

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

class BlockChainTest extends AnyFunSuite with Matchers {
  test("blockchain") {
    val chain = new Chain[String]()
    chain.timestamp should be > 0L

    val genesis = Block[String](previousHash = Hash.sha3256("gensis"), value = "gensis")
    chain.addBlock(genesis)

    val first = Block[String](previousHash = genesis.previousHash, value = "first")
    chain.addBlock(first)

    chain.getBlock(genesis.hash) shouldBe Some(genesis)
    chain.getBlock(first.hash) shouldBe Some(first)
  }
}