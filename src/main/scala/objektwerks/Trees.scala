package objektwerks

object Trees {
  import scala.annotation.tailrec

  sealed abstract class BTree[+T] {
    def value: T
    def left: BTree[T]
    def right: BTree[T]
    def isEmpty: Boolean
    def isLeaf: Boolean
    def leafCount: Int
    val size: Int
    def collectLeaves: List[BTree[T]]
    def collectNodes(level: Int): List[BTree[T]]
  }

  case object BEnd extends BTree[Nothing] {
    override def value: Nothing = throw new NoSuchElementException
    override def left: BTree[Nothing] = throw new NoSuchElementException
    override def right: BTree[Nothing] = throw new NoSuchElementException
    override def isEmpty: Boolean = true
    override def isLeaf: Boolean = false
    override def leafCount: Int = 0
    override val size: Int = 0
    override def collectLeaves: List[BTree[Nothing]] = List()
    override def collectNodes(level: Int): List[BTree[Nothing]] = List()
  }

  case class BNode[+T](override val value: T,
                       override val left: BTree[T],
                       override val right: BTree[T]) extends BTree[T] {
    override def isEmpty: Boolean = false
    override def isLeaf: Boolean = left.isEmpty && right.isEmpty
    override def leafCount: Int = collectLeaves.length
    override val size: Int = 1 + left.size + right.size
    override def collectLeaves: List[BTree[T]] = {
      @tailrec
      def loop(todos: List[BTree[T]], leaves: List[BTree[T]]): List[BTree[T]] = {
        if (todos.isEmpty) leaves
        else if (todos.head.isEmpty) loop(todos.tail, leaves)
        else if (todos.head.isLeaf) loop(todos.tail, todos.head :: leaves)
        else {
          val node = todos.head
          loop(node.left :: node.right :: todos.tail, leaves)
        }
      }
      loop(List(this), List())
    }
    override def collectNodes(level: Int): List[BTree[T]] = {
      @tailrec
      def loop(currentLevel: Int, currentNodes: List[BTree[T]]): List[BTree[T]] = {
        if (currentNodes.isEmpty) List()
        else if (currentLevel == level) currentNodes
        else {
          val expandedNodes = for {
            node <- currentNodes
            child <- List(node.left, node.right) if !child.isEmpty
          } yield child
          loop(currentLevel + 1, expandedNodes)
        }
      }
      if (level < 0) List() else loop(0, List(this))
    }
  }
}