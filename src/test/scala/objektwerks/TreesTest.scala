package objektwerks

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

import Trees._

class TreesTest extends AnyFunSuite with Matchers {
  val tree = BNode(1,
    BNode(2,
      BNode(3, BEnd, BEnd),
      BNode(4,
        BEnd,
        BNode(5, BEnd, BEnd),
      )
    ),
    BNode(6,
      BNode(7, BEnd, BEnd),
      BNode(8, BEnd, BEnd)
    )
  )

  test("leafCount") {
    tree.leafCount shouldBe 4
  }

  test("size") {
    tree.size shouldBe 8
  }

  test("collectLeaves") {
    tree.collectLeaves.map(_.value) shouldBe List(8, 7, 5, 3)
  }

  test("collectNodes") {
    tree.collectNodes(-1).map(_.value) shouldBe List()
    tree.collectNodes(0).map(_.value) shouldBe List(1)
    tree.collectNodes(1).map(_.value) shouldBe List(2, 6)
    tree.collectNodes(2).map(_.value) shouldBe List(3, 4, 7, 8)
    tree.collectNodes(3).map(_.value) shouldBe List(5)
  }
}