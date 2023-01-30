package objektwerks

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

import Sierpinski.*

class SierpinskiTest extends AnyFunSuite with Matchers:
  test("sierpinski") {
    sierpinski(1).length shouldBe 7
    sierpinski(2).length shouldBe 31
    sierpinski(3).length shouldBe 127
  }