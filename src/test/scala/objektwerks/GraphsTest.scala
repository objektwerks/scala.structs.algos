package objektwerks

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

import Graphs._

class GraphsTest extends AnyFunSuite with Matchers {
  test("out degree") {
    outDegree(socialNetwork, "Alice") shouldBe 3
    outDegree(socialNetwork, "Bob") shouldBe 0
    outDegree(socialNetwork, "Charlie") shouldBe 1
    outDegree(socialNetwork, "David") shouldBe 2
    outDegree(socialNetwork, "Mary") shouldBe 2
  }

  test("in degree") {
    inDegree(socialNetwork, "Alice") shouldBe 0
    inDegree(socialNetwork, "Bob") shouldBe 3
    inDegree(socialNetwork, "Charlie") shouldBe 2
    inDegree(socialNetwork, "David") shouldBe 2
    inDegree(socialNetwork, "Mary") shouldBe 1
  }
}