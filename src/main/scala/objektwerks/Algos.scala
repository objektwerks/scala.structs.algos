package objektwerks

object Algos:
  import scala.annotation.tailrec
  import scala.util.Try

  @tailrec
  def sum(xs: List[Int], acc: Int = 0): Int = xs match
    case Nil => acc
    case head :: tail => sum(tail, acc + head)

  @tailrec
  def product(xs: List[Int], acc: Int = 1): Int = xs match
    case Nil => acc
    case head :: tail => product(tail, acc * head)

  @tailrec
  def reverse[A](list: List[A], acc: List[A] = List.empty[A]): List[A] = list match
    case Nil => acc
    case head :: tail => reverse(tail, head :: acc)

  def findNthElementFromRight[A](list: List[A], nthElement: Int): Option[A] =
    @tailrec
    def reverse(list: List[A], acc: List[A] = List.empty[A]): List[A] = list match
      case Nil => acc
      case head :: tail => reverse(tail, head :: acc)

    Try { reverse(list)(nthElement - 1) }.toOption

  @tailrec
  def factorial(n: Int, acc: Int = 1): Int = n match
    case i if i < 1 => acc
    case _ => factorial(n - 1, acc * n)

  def fibonacci(n: Long): BigInt =
    @tailrec
    def loop(n: Long, a: Long, b: Long): BigInt = n match
      case 0 => a
      case _ => loop(n - 1, b, a + b)

    loop(n, 0, 1)

  @tailrec
  def intersectLists[A](listA: List[A],
                        listB: List[A],
                        acc: List[A] = List.empty[A]): List[A] =
    listA match
      case Nil => acc
      case head :: tail =>
        if (listB.contains(head)) intersectLists(tail, listB, acc :+ head)
        else intersectLists(tail, listB, acc)

  def nQueens(n: Int = 8): List[String] =
    def conflict(position: Int, queens: List[Int]): Boolean =
      def conflictOneQueen(position: Int, queen: Int, index: Int): Boolean =
        queen == position || (index + 1) == (position - queen) || (index + 1) == (queen - position)
      queens.zipWithIndex.exists { pair =>
        val (queen, index) = pair
        conflictOneQueen(position, queen, index)
      }

    @tailrec
    def loop(currentPosition: Int,
             currentQueens: List[Int], 
             solutions: List[List[Int]]): List[List[Int]] =
      // I'm out of options
      if currentPosition >= n && currentQueens.isEmpty then solutions
      else if currentPosition >= n then
        // I'm out of options on THIS row; move the previous queen by 1
        loop(currentQueens.head + 1, currentQueens.tail, solutions)
      else if conflict(currentPosition, currentQueens) then
        // conflict with the other queens, try next position
        loop(currentPosition + 1, currentQueens, solutions)
      else if currentQueens.length == n-1 then
        // I've just built a solution
        val newSolution = currentPosition :: currentQueens
        loop(currentPosition + 1, currentQueens, newSolution :: solutions)
      else
        // try next queen on the next row, as this one is valid
        loop(0, currentPosition :: currentQueens, solutions)

    def prettyPrint(solution: List[Int]): String =
      val topEdge = (1 to n).map(_ => "_").mkString(".", ".", ".") // ._._._._.
      val rows = solution.map { queen =>
        val cellsBefore = (0 until queen).map(_ => "_")
        val beforeString = if (cellsBefore.isEmpty) "|" else cellsBefore.mkString("|", "|", "|")
        val cellsAfter = ((queen + 1) until n).map(_ => "_")
        val afterString = if (cellsAfter.isEmpty) "|" else cellsAfter.mkString("|", "|", "|")
        beforeString + "x" + afterString
      }
      s"$topEdge\n${rows.mkString("\n")}"
      
    loop(0, List(), List()).map(prettyPrint)

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

  object Triangle:
    trait Kind
    case object scalene extends Kind     // 0 sides equal
    case object isoceles extends Kind    // 2 sides equal
    case object equilateral extends Kind // 3 sides equal

  case class Triangle(a: Int, b: Int, c: Int):
    import Triangle._
    def kind: Kind = (a, b, c) match
      case (x, y, z) if x == y && y == z => equilateral
      case (x, y, z) if x == y || y == z || z == x => isoceles
      case _ => scalene