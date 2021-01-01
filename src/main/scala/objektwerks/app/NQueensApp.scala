package objektwerks.app

object NQueensApp {
  import scala.annotation.tailrec

  def main(args: Array[String]): Unit = {
    val solutions = nQueens()
    println(solutions.mkString("\n\n"))
    println(s"Total solutions: ${solutions.length}")
  }

  def nQueens(n: Int = 8): List[String] = {
    def conflict(position: Int, queens: List[Int]): Boolean = {
      def conflictOneQueen(position: Int, queen: Int, index: Int): Boolean =
        queen == position || (index + 1) == (position - queen) || (index + 1) == (queen - position)
      queens.zipWithIndex.exists { pair =>
        val (queen, index) = pair
        conflictOneQueen(position, queen, index)
      }
    }

    @tailrec
    def loop(currentPosition: Int, currentQueens: List[Int], solutions: List[List[Int]]): List[List[Int]] = {
      // I'm out of options
      if (currentPosition >= n && currentQueens.isEmpty) solutions
      else if (currentPosition >= n) {
        // I'm out of options on THIS row; move the previous queen by 1
        loop(currentQueens.head + 1, currentQueens.tail, solutions)
      } else if (conflict(currentPosition, currentQueens)) {
        // conflict with the other queens, try next position
        loop(currentPosition + 1, currentQueens, solutions)
      } else if (currentQueens.length == n-1) {
        // I've just built a solution
        val newSolution = currentPosition :: currentQueens
        loop(currentPosition + 1, currentQueens, newSolution :: solutions)
      } else {
        // try next queen on the next row, as this one is valid
        loop(0, currentPosition :: currentQueens, solutions)
      }
    }

    def prettyPrint(solution: List[Int]): String = {
      val topEdge = (1 to n).map(_ => "_").mkString(".", ".", ".") // ._._._._.
      val rows = solution.map { queen =>
        val cellsBefore = (0 until queen).map(_ => "_")
        val beforeString = if (cellsBefore.isEmpty) "|" else cellsBefore.mkString("|", "|", "|")
        val cellsAfter = ((queen + 1) until n).map(_ => "_")
        val afterString = if (cellsAfter.isEmpty) "|" else cellsAfter.mkString("|", "|", "|")
        beforeString + "x" + afterString
      }
      s"$topEdge\n${rows.mkString("\n")}"
    }
    loop(0, List(), List()).map(prettyPrint)
  }
}