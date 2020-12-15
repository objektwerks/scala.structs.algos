package objektwerks

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

import Graphs._

class GraphsTest extends AnyFunSuite with Matchers {
  test("outDegree") {
    outDegree(socialGraph, "Alice") shouldBe 3
    outDegree(socialGraph, "Bob") shouldBe 0
    outDegree(socialGraph, "Charlie") shouldBe 1
    outDegree(socialGraph, "David") shouldBe 2
    outDegree(socialGraph, "Mary") shouldBe 2
  }

  test("inDegree") {
    inDegree(socialGraph, "Alice") shouldBe 0
    inDegree(socialGraph, "Bob") shouldBe 3
    inDegree(socialGraph, "Charlie") shouldBe 2
    inDegree(socialGraph, "David") shouldBe 2
    inDegree(socialGraph, "Mary") shouldBe 1
  }

  test("isPath") {
    isPath(socialGraph, "Alice", "Mary") shouldBe true
    isPath(socialGraph, "Bob", "Mary") shouldBe false
  }

  test("findPath") {
    findPath(socialGraph, "Alice", "Mary") shouldBe List("Alice", "David", "Mary")
    findPath(socialGraph, "Bob", "Mary") shouldBe List()
  }

  test("findCycle") {
    findCycle(socialGraph, "Alice") shouldBe List()
  }
}