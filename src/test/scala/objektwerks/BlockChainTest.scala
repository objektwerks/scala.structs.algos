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

    val first = Block[String](previousHash = genesis.previousHash, value = "first")
    val firstHash = first.hash
    first.timestamp should be > 0L
    firstHash.nonEmpty shouldBe true

    chain.add(genesis) shouldBe true
    chain.add(first) shouldBe true

    chain.get(genesisHash) shouldBe Some(genesis)
    chain.get(genesisHash).get.hash shouldBe genesisHash

    chain.get(firstHash) shouldBe Some(first)
    chain.get(firstHash).get.hash shouldBe firstHash

    chain.list.size shouldBe 2
    (chain.hash == chainHash) shouldBe false

    val ( genesisKey, genesisValue ) = chain.genesis
    genesisKey shouldBe genesis.hash
    genesisValue shouldBe genesis

    val ( firstKey, firstValue ) = chain.last
    firstKey shouldBe first.hash
    firstValue shouldBe first
  }
}