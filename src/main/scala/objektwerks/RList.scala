package objektwerks

object RList {
  import scala.annotation.tailrec

  def from[T](iterable: Iterable[T]): RList[T] = {
    @tailrec
    def loop(remainder: Iterable[T], result: RList[T]): RList[T] = {
      if (remainder.isEmpty) result
      else loop(remainder.tail, remainder.head :: result)
    }
    loop(iterable, RNil)
  }

  sealed abstract class RList[+T] {
    def head: T
    def tail: RList[T]
    def isEmpty: Boolean
    def apply(index: Int): T
    def length: Int
    def reverse: RList[T]
    def ::[S >: T](element: S): RList[S] = new ::(element, this)
  }

  case object RNil extends RList[Nothing] {
    override def head: Nothing = throw new NoSuchElementException
    override def tail: RList[Nothing] = throw new NoSuchElementException
    override def isEmpty: Boolean = true
    override def toString: String = "[]"
    override def apply(index: Int): Nothing = throw new NoSuchElementException
    override def length: Int = 0
    override def reverse: RList[Nothing] = RNil
  }

  case class ::[+T](override val head: T,
                    override val tail: RList[T]) extends RList[T] {
    override def isEmpty: Boolean = false
    override def toString: String = {
      @tailrec
      def loop(remainder: RList[T], result: String): String = {
        if (remainder.isEmpty) result
        else if (remainder.tail.isEmpty) s"$result${remainder.head}"
        else loop(remainder.tail, s"$result${remainder.head}, ")
      }
      "[" + loop(this, "") + "]"
    }
    override def apply(index: Int): T = {
      @tailrec
      def loop(remainder: RList[T], currentIndex: Int): T = {
        if ( currentIndex == index) remainder.head
        else loop( remainder.tail, currentIndex + 1)
      }
      if ( index < 0 ) throw new NoSuchElementException
      else loop(this, 0)
    }
    override def length: Int = {
      @tailrec
      def loop(remainder: RList[T], accumulator: Int): Int = {
        if (remainder.isEmpty) accumulator
        else loop(remainder.tail, accumulator + 1)
      }
      loop(this, 0)
    }
    override def reverse: RList[T] = {
      @tailrec
      def loop(remainder: RList[T], result: RList[T]): RList[T] = {
        if (remainder.isEmpty) result
        else loop(remainder.tail, remainder.head :: result)
      }
      loop(this, RNil)
    }
  }
}