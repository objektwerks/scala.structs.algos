package objektwerks

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

final class PinTest extends AnyFunSuite with Matchers:
  test("pin"):
    for i <- 1 to 1_000_000
    do Pin().length shouldBe 7