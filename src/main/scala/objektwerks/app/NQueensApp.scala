package objektwerks.app

object NQueensApp:
  import objektwerks.Algos._

  @main def runNQueens(): Unit =
    val solutions = nQueens()
    println(solutions.mkString("\n\n"))
    println(s"Total solutions: ${solutions.length}")