package objektwerks

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

import Stats.*

class StatsTest extends AnyFunSuite with Matchers {
  val xs = Vector[Double](1.0, 2.0, 3.0, 3.0, 4.0, 4.0, 5.0)
  val ys = Vector[Double](1.5, 2.5, 3.25, 3.5, 4.25, 4.75, 5.5)
  val outcomesToCount = Vector((1.0, 5.0), (2.0, 10.0), (3.0, 11.0), (4.0, 44.0), (5.0, 38.0))

  test("mean") {
    mean(xs) shouldBe 3.142857142857143
  }

  test("median") {
    median(xs) shouldBe 3.0
  }

  test("mode") {
    mode(xs) shouldBe Vector(3.0, 4.0)
  }

  test("range") {
    range(xs) shouldBe 4.0
  }

  test("histogram") {
    histogram(xs) shouldBe Map(1.0 -> 1, 2.0 -> 1, 3.0 -> 2, 4.0 -> 2, 5.0 -> 1)
  }

  test("variance") {
    assert(variance(xs) == 1.8095238095238093)
  }

  test("standard deviation") {
    assert(standardDeviation(xs) == 1.3451854182690985)
  }

  test("standard error") {
    assert(standardError(xs) == 0.5084322977157767)
  }

  test("covariance") {
    assert(covariance(xs, ys) == 1.8154761904761905)
  }

  test("correlation coefficient") {
    assert(correlationCoefficient(xs, ys) == 0.9919341745639146)
  }

  test("centroid") {
    assert((3.142857142857143,3.607142857142857) == centroid(xs, ys))
  }

  test("factorial") {
    assert(factorial(9) == 362880)
  }

  test("fibonacci") {
    assert(fibonacci(39) == 63245986)
  }

  test("combinations") {
    assert(combinations(10, 3) == 120)
  }

  test("permutations") {
    assert(permutations(10, 3) == 720)
  }

  test("probability") {
    assert(probability(outcomesToCount) == Vector(0.046296296296296294, 0.09259259259259259, 0.10185185185185185, 0.4074074074074074, 0.35185185185185186))
  }

  test("expected value") {
    assert(expectedValue(outcomesToCount) == 3.9259259259259256)
  }
}