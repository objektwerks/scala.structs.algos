package objektwerks

object Trees {
  import scala.annotation.tailrec
  import scala.collection.immutable.Queue

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
    def mirror: BTree[T]
    def sameShapeAs[S >: T](other: BTree[S]): Boolean
    def isSymmetrical: Boolean
    def toList: List[T]
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
    override def mirror: BTree[Nothing] = BEnd
    override def sameShapeAs[S >: Nothing](other: BTree[S]): Boolean = false
    override def isSymmetrical: Boolean = true
    override def toList: List[Nothing] = List()
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
    override def mirror: BTree[T] = {
      @tailrec
      def loop(todos: List[BTree[T]], expanded: Set[BTree[T]], acc: List[BTree[T]]): BTree[T] = {
        if (todos.isEmpty) acc.head
        else {
          val node = todos.head
          if (node.isEmpty || node.isLeaf) {
            loop(todos.tail, expanded, node :: acc)
          } else if (!expanded.contains(node)) {
            loop(node.left :: node.right :: todos, expanded + node, acc)
          } else {
            val newLeft = acc.head
            val newRight = acc.tail.head
            val newNode = BNode(node.value, newLeft, newRight)
            loop(todos.tail, expanded, newNode :: acc.drop(2))
          }
        }
      }
      loop(List(this), Set(), List())
    }
    override def sameShapeAs[S >: T](other: BTree[S]): Boolean = {
      @tailrec
      def loop(thisTree: List[BTree[S]], otherTree: List[BTree[S]]): Boolean = {
        if (thisTree.isEmpty) otherTree.isEmpty
        else if (otherTree.isEmpty) thisTree.isEmpty
        else {
          val thisNode = thisTree.head
          val thatNode = otherTree.head
          if (thisNode.isEmpty) thatNode.isEmpty && loop(thisTree.tail, otherTree.tail)
          else if (thisNode.isLeaf) thatNode.isLeaf && loop(thisTree.tail, otherTree.tail)
          else loop(
            thisNode.left :: thisNode.right :: thisTree.tail,
            thatNode.left :: thatNode.right :: otherTree.tail
          )
        }
      }
      loop(List(this), List(other))
    }
    override def isSymmetrical: Boolean = sameShapeAs(this.mirror)
    override def toList: List[T] = {
      @tailrec
      def perLevelTailrec(level: List[BTree[T]], finalQueue: Queue[BTree[T]] = Queue()): List[T] =
        if (level.isEmpty) finalQueue.map(_.value).toList
        else perLevelTailrec(
          level.flatMap(node => List(node.left, node.right).filter(!_.isEmpty)),
          finalQueue ++ level
        )
      perLevelTailrec(List(this))
    }
  }
}