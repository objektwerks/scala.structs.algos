package objektwerks.app

import objektwerks.NQueens.*

object NQueensApp:
  @main def runNQueens(): Unit =
    val solutions = nQueens()
    println(solutions.mkString("\n\n"))
    println(s"Total solutions: ${solutions.length}")