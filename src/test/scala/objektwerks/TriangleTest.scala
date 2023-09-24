package objektwerks

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

import Triangle.*

final class TriangleTest extends AnyFunSuite with Matchers:
  test("triangles"):
    Triangle(3, 6, 9).kind shouldEqual Triangle.scalene
    Triangle(3, 6, 3).kind shouldEqual Triangle.isoceles
    Triangle(3, 3, 3).kind shouldEqual Triangle.equilateral