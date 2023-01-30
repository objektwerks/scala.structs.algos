package objektwerks

import scala.annotation.tailrec

object Graphs:
  type Graph[T] = Map[T, Set[T]]

  val socialGraph: Graph[String] = Map(
    "Alice" -> Set("Bob", "Charlie", "David"),
    "Bob" -> Set(),
    "Charlie" -> Set("David"),
    "David" -> Set("Bob", "Mary"),
    "Mary" -> Set("Bob", "Charlie")
  )

  // Number of nodes adjacent to node - or node's set count.
  def outDegree[T](graph: Graph[T], node: T): Int =
    if graph.contains(node) then graph(node).size
    else 0

  // Number of nodes connected to node - or node occurences in all sets.
  def inDegree[T](graph: Graph[T], node: T): Int =
    graph.values.count(_.contains(node))

  def isPath[T](graph: Graph[T], start: T, end: T): Boolean =
    @tailrec
    def loop(list: List[T], visited: Set[T]): Boolean =
      if list.isEmpty then false
      else
        val node = list.head
        if (node == end) true
        else if (visited.contains(node)) loop(list.tail, visited)
        else loop(list.tail ++ graph(node), visited + node)

    loop(List(start), Set())

  def findPath[T](graph: Graph[T], start: T, end: T): List[T] =
    @tailrec
    def loop(list: List[(T, List[T])], visited: Set[T]): List[T] =
      if list.isEmpty then List()
      else
        val (node, path) = list.head
        if (node == end) path.reverse
        else if visited.contains(node) then loop(list.tail, visited)
        else
          val neighbors = graph(node)
          val tuples = neighbors.map(n => (n, n :: path))
          loop(list.tail ++ tuples, visited + node)

    loop(
      list = graph(start).map(n => (n, n :: List(start))).toList,
      visited = Set(start)
    )

  def findCycle[T](graph: Graph[T], node: T): List[T] = findPath(graph, node, node)

  // Bidirectional graph.
  def toUndirected[T](graph: Graph[T]): Graph[T] =
    def addEdge(graph: Graph[T], from: T, to: T): Graph[T] =
      if !graph.contains(from) then graph + (from -> Set(to))
      else
        val neighbors = graph(from)
        graph + (from -> (neighbors + to))

    @tailrec
    def addOpposingEdges(list: Set[T], acc: Graph[T]): Graph[T] =
      if list.isEmpty then acc
      else
        val node = list.head
        val neighbors = graph(node)
        val newGraph = neighbors.foldLeft(acc)((graph, neighbor) => addEdge(graph, neighbor, node))
        addOpposingEdges(list.tail, newGraph)

    addOpposingEdges(graph.keySet, graph)

  def color[T](undirectedGraph: Graph[T]): Map[T, Int] =
    @tailrec
    def loop(list: List[T], current: Int, acc: Map[T, Int]): Map[T, Int] =
      if list.isEmpty then acc
      else
        val node = list.head
        if acc.contains(node) then loop(list.tail, current, acc)
        else
          val uncoloredNodes = list.tail.foldLeft[Set[T]](Set(node)) { (nodesToColor, n) =>
            val neighbors = nodesToColor.flatMap(nodeToColor => undirectedGraph(nodeToColor))
            if acc.contains(n) || neighbors.contains(n) then nodesToColor
            else nodesToColor + n
          }
          val newColoredNodes = uncoloredNodes.map((_, current)).toMap
          loop(list.tail, current + 1, acc ++ newColoredNodes)

    val orderedNodes = undirectedGraph.keySet.toList.sortWith((a, b) =>
      outDegree(undirectedGraph, a) > outDegree(undirectedGraph, b)
    )
    
    loop(orderedNodes, 0, Map())