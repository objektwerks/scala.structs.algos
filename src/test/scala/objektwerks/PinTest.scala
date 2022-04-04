package objektwerks

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

class PinTest extends AnyFunSuite with Matchers:
  test("pin") {
    Pin().length shouldBe 7
  }