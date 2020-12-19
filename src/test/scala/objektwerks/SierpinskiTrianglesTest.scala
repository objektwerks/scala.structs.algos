package objektwerks

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

import SierpinskiTrianglesApp._

class SierpinskiTrianglesTest extends AnyFunSuite with Matchers {
  test("triangles") {
    sierpinski(1).length shouldBe 7
    sierpinski(2).length shouldBe 31
    sierpinski(3).length shouldBe 127
  }
}