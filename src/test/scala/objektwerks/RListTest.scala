package objektwerks

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

import RList._

class RListTest extends AnyFunSuite with Matchers {
  test("cons :: > prepend ::") {
    val cons = 1 :: 2 :: 3 :: RNil
    val prepend = 0 :: cons
    prepend shouldBe 0 :: 1 :: 2 :: 3 :: RNil
  }

  test("isEmpty") {
    val rlist = 1 :: 2 :: 3 :: RNil
    rlist.isEmpty shouldBe false
  }

  test("length") {
    val rlist = 1 :: 2 :: 3 :: RNil
    rlist.length shouldBe 3
  }

  test("apply") {
    val rlist = 1 :: 2 :: 3 :: RNil
    rlist(2) shouldBe 3
  }

  test("reverse") {
    val rlist = 1 :: 2 :: 3 :: RNil
    rlist.reverse shouldBe 3 :: 2 :: 1 :: RNil
  }

  test("append ++") {
    val rlist = 1 :: 2 :: 3 :: RNil
    (rlist ++ (4 :: RNil)) shouldBe 1 :: 2 :: 3 :: 4 :: RNil
  }

  test("remove -=") {
    val rlist = 1 :: 2 :: 3 :: RNil
    (rlist -= 2) shouldBe 1 :: 2 :: RNil
  }

  test("iterable") {
    val rlist = RList.iterable(1 to 1000)
    rlist.length shouldBe 1000
    rlist(500) shouldBe 500
  }

  test("map") {
    val rlist = 1 :: 2 :: 3 :: RNil
    rlist.map(x => x * 2) shouldBe 2 :: 4 :: 6 :: RNil
  }

  test("flatMap") {
    val rlist = 1 :: 2 :: 3 :: RNil
    rlist.flatMap(x => x :: (x * 2) :: RNil) shouldBe 1 :: 2 :: 2 :: 4 :: 3 :: 6 :: RNil
  }

  test("filter") {
    val rlist = 1 :: 2 :: 3 :: RNil
    rlist.filter(x => x % 2 == 0) shouldBe 2 :: RNil
  }

  test("count") {
    val rlist = 1 :: 2 :: 2 :: 3 :: 3 :: 3 :: RNil
    rlist.count shouldBe (1, 1) :: (2, 2) :: (3, 3) :: RNil
  }

  test("duplicate") {
    val rlist = 1 :: 2 :: 3 :: RNil
    rlist.duplicate(by = 2) shouldBe 1 :: 1 :: 2 :: 2 :: 3 :: 3 :: RNil
  }

  test("rotate") {
    val rlist = 1 :: 2 :: 3 :: RNil
    rlist.rotate(by = 1) shouldBe 2 :: 3 :: 1 :: RNil
  }
}