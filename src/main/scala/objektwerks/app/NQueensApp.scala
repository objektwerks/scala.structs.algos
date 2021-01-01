package objektwerks.app

object NQueensApp {
  import objektwerks.Algos._

  def main(args: Array[String]): Unit = {
    val solutions = nQueens()
    println(solutions.mkString("\n\n"))
    println(s"Total solutions: ${solutions.length}")
  }
}