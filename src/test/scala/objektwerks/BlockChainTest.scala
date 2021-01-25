package objektwerks

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

class BlockChainTest extends AnyFunSuite with Matchers {
  test("blockchain") {
    val chain = new Chain[String]()

    val genesis = Block[String](previousHash = Hash.sha3256("genesis"), value = "genesis")
    chain.addBlock(genesis)

    val first = Block[String](previousHash = genesis.previousHash, value = "first")
    chain.addBlock(first)

    chain.getBlock(genesis.hash) shouldBe Some(genesis)
    chain.getBlock(first.hash) shouldBe Some(first)
  }
}