package objektwerks

object Trees {
  import scala.annotation.tailrec

  sealed abstract class BTree[+T] {
    def value: T
    def left: BTree[T]
    def right: BTree[T]
    def isEmpty: Boolean
    def isLeaf: Boolean
    def collectLeaves: List[BTree[T]]
    def leafCount: Int
  }

  case object BEnd extends BTree[Nothing] {
    override def value: Nothing = throw new NoSuchElementException
    override def left: BTree[Nothing] = throw new NoSuchElementException
    override def right: BTree[Nothing] = throw new NoSuchElementException
    override def isEmpty: Boolean = true
    override def isLeaf: Boolean = false
    override def collectLeaves: List[BTree[Nothing]] = List()
    override def leafCount: Int = 0
  }

  case class BNode[+T](override val value: T,
                       override val left: BTree[T],
                       override val right: BTree[T]) extends BTree[T] {
    override def isEmpty: Boolean = false
    override def isLeaf: Boolean = left.isEmpty && right.isEmpty
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
    override def leafCount: Int = collectLeaves.length
  }
}