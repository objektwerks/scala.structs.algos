package objektwerks

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

import Trees._

class TreesTest extends AnyFunSuite with Matchers {
  val tree = BNode(1,
    BNode(2,
      BNode( 3, BEnd, BEnd),
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

  test("leaf count") {
    tree.leafCount shouldBe 4
  }

  test("collect leaves") {
    tree.collectLeaves.map(_.value) shouldBe List(8, 7, 5, 3)
  }

  test("size") {
    tree.size shouldBe 8
  }
}