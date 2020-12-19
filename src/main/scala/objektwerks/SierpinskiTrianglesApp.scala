package objektwerks

object SierpinskiTrianglesApp {
  import scala.annotation.tailrec

  def main(args: Array[String]): Unit = {
    println(sierpinski(3))
  }

  def sierpinski(n: Int): String = {
    @tailrec
    def loop(currentLevel: Int, currentTriangle: List[String]): List[String] = {
      if (currentLevel >= n) currentTriangle
      else {
        val spaces = " " * (1 << currentLevel) // 2 ^ (n - 1) spaces
        val topTriangle = currentTriangle.map(spaces + _ + spaces)
        val bottomTriangles = currentTriangle.map(row => row + " " + row)
        loop(currentLevel + 1, topTriangle ++ bottomTriangles)
      }
    }
    loop(0, List("*")).mkString("\n")
  }
}