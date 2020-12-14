package objektwerks

object Graphs {
  type Graph[T] = Map[T, Set[T]]

  val socialNetwork: Graph[String] = Map(
    "Alice" -> Set("Bob", "Charlie", "David"),
    "Bob" -> Set(),
    "Charlie" -> Set("David"),
    "David" -> Set("Bob", "Mary"),
    "Mary" -> Set("Bob", "Charlie")
  )

  // Number of nodes adjacent to node.
  def outDegree[T](graph: Graph[T], node: T): Int = {
    if (graph.contains(node)) graph(node).size
    else 0
  }

  // Number of nodes connected to node.
  def inDegree[T](graph: Graph[T], node: T): Int = {
    graph.values.count(_.contains(node))
  }
}