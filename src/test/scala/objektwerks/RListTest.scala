package objektwerks

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

import RList.*

final class RListTest extends AnyFunSuite with Matchers:
  test("cons :: > prepend ::"):
    val cons = 1 :: 2 :: 3 :: RNil
    val prepend = 0 :: cons
    prepend shouldBe 0 :: 1 :: 2 :: 3 :: RNil

  test("apply"):
    val rlist = 1 :: 2 :: 3 :: RNil
    rlist(2) shouldBe 3

  test("isEmpty"):
    val rlist = 1 :: 2 :: 3 :: RNil
    rlist.isEmpty shouldBe false

  test("nonEmpty"):
    val rlist = 1 :: 2 :: 3 :: RNil
    rlist.nonEmpty shouldBe true

  test("length"):
    val rlist = 1 :: 2 :: 3 :: RNil
    rlist.length shouldBe 3

  test("contains"):
    val rlist = 1 :: 2 :: 3 :: RNil
    rlist.contains(3) shouldBe true

  test("reverse"):
    val rlist = 1 :: 2 :: 3 :: RNil
    rlist.reverse shouldBe 3 :: 2 :: 1 :: RNil

  test("append ++"):
    val rlist = 1 :: 2 :: 3 :: RNil
    (rlist ++ (4 :: RNil)) shouldBe 1 :: 2 :: 3 :: 4 :: RNil

  test("remove -="):
    val rlist = 1 :: 2 :: 3 :: RNil
    (rlist -= 2) shouldBe 1 :: 2 :: RNil

  test("iterable"):
    val rlist = RList.iterable(1 to 1000)
    rlist.length shouldBe 1000
    rlist(500) shouldBe 500

  test("map"):
    val rlist = 1 :: 2 :: 3 :: RNil
    rlist.map(x => x * 2) shouldBe 2 :: 4 :: 6 :: RNil

  test("flatMap"):
    val rlist = 1 :: 2 :: 3 :: RNil
    rlist.flatMap(x => x :: (x * 2) :: RNil) shouldBe 1 :: 2 :: 2 :: 4 :: 3 :: 6 :: RNil

  test("filter"):
    val rlist = 1 :: 2 :: 3 :: RNil
    rlist.filter(x => x % 2 == 0) shouldBe 2 :: RNil

  test("rle"):
    val rlist = "a" :: "a" :: "b" :: "b" :: "c" :: "c" :: RNil
    rlist.rle shouldBe ("a", 2) :: ("b", 2) :: ("c", 2) :: RNil
    RList.rld( rlist.rle ) shouldBe "aabbcc"

  test("count"):
    val rlist = 1 :: 2 :: 2 :: 3 :: 3 :: 3 :: RNil
    rlist.count shouldBe (1, 1) :: (2, 2) :: (3, 3) :: RNil

  test("duplicate"):
    val rlist = 1 :: 2 :: 3 :: RNil
    rlist.duplicate(by = 2) shouldBe 1 :: 1 :: 2 :: 2 :: 3 :: 3 :: RNil

  test("rotate"):
    val rlist = 1 :: 2 :: 3 :: RNil
    rlist.rotate(by = 1) shouldBe 2 :: 3 :: 1 :: RNil

  test("random"):
    val rlist = RList.iterable(1 to 100)
    rlist.random(50).length shouldBe 50

  test("insertion sort"):
    val rlist = 3 :: 2 :: 1 :: RNil
    rlist.insertionSort shouldBe 1 :: 2 :: 3 :: RNil
    val singleList = 3 :: RNil
    singleList.insertionSort shouldBe (3 :: RNil)

  test("merge sort"):
    val rlist = 3 :: 2 :: 1 :: RNil
    rlist.mergeSort shouldBe 1 :: 2 :: 3 :: RNil
    val singleList = 3 :: RNil
    singleList.mergeSort shouldBe (3 :: RNil)

  test("quick sort"):
    val rlist = 3 :: 2 :: 1 :: RNil
    rlist.quickSort shouldBe 1 :: 2 :: 3 :: RNil
    val singleList = 3 :: RNil
    singleList.quickSort shouldBe (3 :: RNil)

  test("intersect"):
    val rlistA = RList.iterable(1 to 10)
    val rlistB = RList.iterable(5 to 15)
    val rlistIntersection = RList.iterable(5 to 10)
    rlistA.intersect(rlistB) shouldBe rlistIntersection