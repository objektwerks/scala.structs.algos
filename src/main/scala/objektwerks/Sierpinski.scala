package objektwerks

import scala.annotation.tailrec

object Sierpinski:
  def sierpinski(n: Int): String =
    @tailrec
    def loop(currentLevel: Int, currentTriangle: List[String]): List[String] =
      if currentLevel >= n then currentTriangle
      else
        val spaces = " " * (1 << currentLevel) // 2 ^ (n - 1) spaces
        val topTriangle = currentTriangle.map(spaces + _ + spaces)
        val bottomTriangles = currentTriangle.map(row => row + " " + row)
        loop(currentLevel + 1, topTriangle ++ bottomTriangles)

    loop(0, List("*")).mkString("\n")