package objektwerks

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

import MiscAlgos.*

class MiscAlgosTest extends AnyFunSuite with Matchers:
  test("find unpaired item") {
    findUnpairedItem(List(1, 2, 1)).get shouldBe 2
    findUnpairedItem(List(1, 2, 1, 1, 1)).get shouldBe 2
    findUnpairedItem(List(1, 1, 1, 1, 1)).get shouldBe 1
    findUnpairedItem(List(1, 1, 1, 1)).isEmpty shouldBe true
    findUnpairedItem(List(1, 2, 3, 1, 2)).get shouldBe 3
    findUnpairedItem(List(1, 2, 3, 3, 1, 2)).isEmpty shouldBe true
  }

  test("find max profit") {
    findMaxProfit(Array(163, 112, 105, 100, 151)).get shouldBe 51
    findMaxProfit(Array(1)).isEmpty shouldBe true
    findMaxProfit(Array(1, 2)).get shouldBe 1
    findMaxProfit(Array(2, 1)).isEmpty shouldBe true
  }