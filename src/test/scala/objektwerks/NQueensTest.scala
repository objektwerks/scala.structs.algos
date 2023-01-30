package objektwerks

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

import NQueens.*

class NQueensTest extends AnyFunSuite with Matchers:
  test("nqueens") {
    nQueens().length shouldBe 92
  }