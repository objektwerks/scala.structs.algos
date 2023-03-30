package objektwerks

import scala.annotation.tailrec
import scala.collection.immutable.{SortedMap, TreeMap}

object Stats {
  def mean(xs: Vector[Double]): Double = {
    require(xs.nonEmpty, "Nonempty vector required.")
    xs.sum / xs.length
  }

  def median(xs: Vector[Double]): Double = {
    require(xs.nonEmpty, "Nonempty vector required.")
    xs(xs.length / 2)
  }

  def mode(xs: Vector[Double]): Vector[Double] = {
    val hg = histogram(xs)
    val max = hg.values.max
    hg.filter(_._2 == max).keys.toVector
  }

  def range(xs: Vector[Double]): Double = {
    require(xs.nonEmpty, "Nonempty vector required.")
    xs.max - xs.min
  }

  def histogram(xs: Vector[Double]): SortedMap[Double, Int] = {
    require(xs.nonEmpty, "Nonempty vector required.")
    TreeMap[Double, Int]() ++ xs.groupBy(x => x).view.mapValues(_.length).toMap
  }

  def variance(xs: Vector[Double]): Double = {
    val m = mean(xs)
    xs.iterator.map(x => math.pow(x - m, 2.0)).sum / (xs.length - 1)
  }

  def standardDeviation(xs: Vector[Double]): Double = {
    math.sqrt(variance(xs))
  }

  def standardError(xs: Vector[Double]): Double = {
    standardDeviation(xs) / math.sqrt(xs.length.toDouble)
  }

  def covariance(xs: Vector[Double], ys: Vector[Double]): Double = {
    val xsm = mean(xs)
    val ysm = mean(ys)
    xs.zip(ys).map(t => (t._1 - xsm) * (t._2 - ysm)).sum / (xs.length - 1)
  }

  def correlationCoefficient(xs: Vector[Double], ys: Vector[Double]): Double = {
    val xsm = mean(xs)
    val ysm = mean(ys)
    val xssd = standardDeviation(xs)
    val yssd = standardDeviation(ys)
    val xsys = xs.zip(ys).map(t => (t._1 - xsm) * (t._2 - ysm)).sum
    (xsys / (xssd * yssd)) / (xs.length - 1)
  }

  def centroid(xs: Vector[Double], ys: Vector[Double]): (Double, Double) = {
    val xsm = mean(xs)
    val ysm = mean(ys)
    (xsm, ysm)
  }

  @tailrec
  def factorial(n: Long, acc: Long = 1): Long = n match {
    case i if i <= 0 => acc
    case _ => factorial(n - 1, acc * n)
  }

  def fibonacci(n: Long): BigInt =
    @tailrec
    def loop(n: Long, a: Long, b: Long): BigInt = n match
      case 0 => a
      case _ => loop(n - 1, b, a + b)

    loop(n, 0, 1)

  def combinations(n: Long, r: Long): Long = {
    val dividend = factorial(n)
    val divisor = factorial(r) * factorial(n - r)
    dividend / divisor
  }

  def permutations(n: Long, r: Long): Long = {
    val dividend = factorial(n)
    val divisor = factorial(n - r)
    dividend / divisor
  }

  def probability(outcomeToCount: Vector[(Double, Double)]): Vector[Double] = {
    val countTotal = outcomeToCount.map(t => t._2).sum
    outcomeToCount.map(t => t._2 / countTotal)
  }

  def expectedValue(outcomeToCount: Vector[(Double, Double)]): Double = {
    val countTotal = outcomeToCount.map(t => t._2).sum
    outcomeToCount.map(t => t._1 * (t._2 / countTotal)).sum
  }
}