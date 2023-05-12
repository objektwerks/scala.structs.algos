package objektwerks.cli

import objektwerks.NQueens.*

@main def runNQueens(): Unit =
  val solutions = nQueens()
  println(solutions.mkString("\n\n"))
  println(s"Total solutions: ${solutions.length}")