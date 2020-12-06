package objektwerks

object RList {
  import scala.annotation.tailrec

  sealed abstract class RList[+T] {
    def head: T
    def tail: RList[T]
    def isEmpty: Boolean
    def ::[S >: T](element: S): RList[S] = new ::(element, this)
  }

  case object RNil extends RList[Nothing] {
    override def head: Nothing = throw new NoSuchElementException
    override def tail: RList[Nothing] = throw new NoSuchElementException
    override def isEmpty: Boolean = true
    override def toString: String = "[]"
  }

  case class ::[+T](override val head: T,
                    override val tail: RList[T]) extends RList[T] {
    override def isEmpty: Boolean = false
    override def toString: String = {
      @tailrec
      def loop(remaining: RList[T], result: String): String = {
        if (remaining.isEmpty) result
        else if (remaining.tail.isEmpty) s"$result${remaining.head}"
        else loop(remaining.tail, s"$result${remaining.head}, ")
      }
      "[" + loop(this, "") + "]"
    }
  }
}