package objektwerks

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

import Trees.*

final class TreesTest extends AnyFunSuite with Matchers:
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

  val xtree = BNode(10,
    BNode(20,
      BNode(30, BEnd, BEnd),
      BNode(40,
        BEnd,
        BNode(50, BEnd, BEnd),
      )
    ),
    BNode(60,
      BNode(70, BEnd, BEnd),
      BNode(80, BEnd, BEnd)
    )
  )

  val symmetricalTree = BNode(1,
    BNode(2,
      BNode(3, BEnd, BEnd),
      BNode(4, BEnd, BEnd),
    ),
    BNode(6,
      BNode(7, BEnd, BEnd),
      BNode(8, BEnd, BEnd)
    )
  )

  test("leafCount"):
    tree.leafCount shouldBe 4

  test("size"):
    tree.size shouldBe 8

  test("collectLeaves"):
    tree.collectLeaves.map(_.value) shouldBe List(8, 7, 5, 3)

  test("collectNodes"):
    tree.collectNodes(-1).map(_.value) shouldBe List()
    tree.collectNodes(0).map(_.value) shouldBe List(1)
    tree.collectNodes(1).map(_.value) shouldBe List(2, 6)
    tree.collectNodes(2).map(_.value) shouldBe List(3, 4, 7, 8)
    tree.collectNodes(3).map(_.value) shouldBe List(5)

  test("mirror"):
    val mirror = tree.mirror
    mirror.collectNodes(1).map(_.value) shouldBe List(6, 2)
    mirror.collectNodes(2).map(_.value) shouldBe List(8, 7, 4, 3)

  test("sameShapeAs"):
    tree.sameShapeAs(tree) shouldBe true
    tree.sameShapeAs(xtree) shouldBe true
    tree.sameShapeAs(tree.mirror) shouldBe false

  test("isSymmetrical"):
    symmetricalTree.isSymmetrical shouldBe true
    tree.isSymmetrical shouldBe false
    tree.mirror.isSymmetrical shouldBe false

  test("toList"):
    tree.toList shouldBe List(1, 2, 6, 3, 4, 7, 8, 5)
    xtree.toList shouldBe List(10, 20, 60, 30, 40, 70, 80, 50)
    symmetricalTree.toList shouldBe List(1, 2, 6, 3, 4, 7, 8)