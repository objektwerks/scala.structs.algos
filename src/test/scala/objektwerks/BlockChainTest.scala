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
    chain.add(genesis) shouldBe true

    val first = Block[String](previousHash = chain.last.hash, value = "first")
    val firstHash = first.hash
    first.timestamp should be > 0L
    firstHash.nonEmpty shouldBe true
    chain.add(first) shouldBe true

    chain.get(genesisHash) shouldBe Some(genesis)
    chain.get(genesisHash).get.hash shouldBe genesisHash

    chain.get(firstHash) shouldBe Some(first)
    chain.get(firstHash).get.hash shouldBe firstHash

    chain.list.size shouldBe 2
    (chain.hash == chainHash) shouldBe false

    val genensisHashBlock = chain.genesis
    genensisHashBlock.hash shouldBe genesis.hash
    genensisHashBlock.block shouldBe genesis

    val firstHashBlock = chain.last
    firstHashBlock.hash shouldBe first.hash
    firstHashBlock.block shouldBe first
  }
}