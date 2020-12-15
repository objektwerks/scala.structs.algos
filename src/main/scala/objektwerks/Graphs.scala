package objektwerks

object Graphs {
  import scala.annotation.tailrec

  type Graph[T] = Map[T, Set[T]]

  val socialGraph: Graph[String] = Map(
    "Alice" -> Set("Bob", "Charlie", "David"),
    "Bob" -> Set(),
    "Charlie" -> Set("David"),
    "David" -> Set("Bob", "Mary"),
    "Mary" -> Set("Bob", "Charlie")
  )

  // Number of nodes adjacent to node - or node's set count.
  def outDegree[T](graph: Graph[T], node: T): Int = {
    if (graph.contains(node)) graph(node).size
    else 0
  }

  // Number of nodes connected to node - or node occurences in all sets.
  def inDegree[T](graph: Graph[T], node: T): Int = {
    graph.values.count(_.contains(node))
  }

  def isPath[T](graph: Graph[T], start: T, end: T): Boolean = {
    @tailrec
    def loop(list: List[T], visited: Set[T]): Boolean = {
      if (list.isEmpty) false
      else {
        val node = list.head
        if (node == end) true
        else if (visited.contains(node)) loop(list.tail, visited)
        else loop(list.tail ++ graph(node), visited + node)
      }
    }
    loop(List(start), Set())
  }

  def findpath[T](graph: Graph[T], start: T, end: T): List[T] = {
    @tailrec
    def loop(list: List[(T, List[T])], visited: Set[T]): List[T] = {
      if (list.isEmpty) List()
      else {
        val (node, path) = list.head
        if (node == end) path.reverse
        else if (visited.contains(node)) loop(list.tail, visited)
        else {
          val neighbors = graph(node)
          val tuples = neighbors.map(n => (n, n :: path))
          loop(list.tail ++ tuples, visited + node)
        }
      }
    }
    loop(List((start, List(start))), Set())
  }
}