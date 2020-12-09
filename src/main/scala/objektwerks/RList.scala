package objektwerks

object RList extends RListExtensions {
  import scala.annotation.tailrec

  sealed abstract class RList[+T] {
    def head: T
    def tail: RList[T]
    def isEmpty: Boolean
    def apply(index: Int): T
    def length: Int
    def reverse: RList[T]
    def ::[S >: T](element: S): RList[S] = new ::(element, this)
    def ++[S >: T](other: RList[S]): RList[S]
    def -=(index: Int): RList[T]
    def map[S](func: T => S): RList[S]
    def flatMap[S](func: T => RList[S]): RList[S]
    def filter(predicate: T => Boolean): RList[T]
  }

  case object RNil extends RList[Nothing] {
    override def head: Nothing = throw new NoSuchElementException
    override def tail: RList[Nothing] = throw new NoSuchElementException
    override def isEmpty: Boolean = true
    override def toString: String = "[]"
    override def apply(index: Int): Nothing = throw new NoSuchElementException
    override def length: Int = 0
    override def reverse: RList[Nothing] = RNil
    override def ++[S >: Nothing](other: RList[S]): RList[S] = other
    override def -=(index: Int): RList[Nothing] = RNil
    override def map[S](func: Nothing => S): RList[S] = RNil
    override def flatMap[S](func: Nothing => RList[S]): RList[S] = RNil
    override def filter(predicate: Nothing => Boolean): RList[Nothing] = RNil
  }

  case class ::[+T](override val head: T,
                    override val tail: RList[T]) extends RList[T] {
    override def isEmpty: Boolean = false
    override def toString: String = {
      @tailrec
      def loop(list: RList[T], acc: String): String = {
        if (list.isEmpty) acc
        else if (list.tail.isEmpty) s"$acc${list.head}"
        else loop(list.tail, s"$acc${list.head}, ")
      }
      "[" + loop(this, "") + "]"
    }
    override def apply(index: Int): T = {
      @tailrec
      def loop(list: RList[T], currentIndex: Int): T = {
        if ( currentIndex == index) list.head
        else loop( list.tail, currentIndex + 1)
      }
      if ( index < 0 ) throw new NoSuchElementException
      else loop(this, 0)
    }
    override def length: Int = {
      @tailrec
      def loop(list: RList[T], acc: Int): Int = {
        if (list.isEmpty) acc
        else loop(list.tail, acc + 1)
      }
      loop(this, 0)
    }
    override def reverse: RList[T] = {
      @tailrec
      def loop(list: RList[T], acc: RList[T]): RList[T] = {
        if (list.isEmpty) acc
        else loop(list.tail, list.head :: acc)
      }
      loop(this, RNil)
    }
    override def ++[S >: T](other: RList[S]): RList[S] = {
      @tailrec
      def loop(other: RList[S], acc: RList[S]): RList[S] = {
        if (other.isEmpty) acc
        else loop(other.tail, other.head :: acc)
      }
      loop(this.reverse, other)
    }
    override def -=(index: Int): RList[T] = {
      @tailrec
      def loop(list: RList[T], currentIndex: Int, unmatchedIndexes: RList[T]): RList[T] = {
        if (currentIndex == index) unmatchedIndexes.reverse ++ list.tail
        else if (list.isEmpty) unmatchedIndexes.reverse
        else loop(list.tail, currentIndex + 1, list.head :: unmatchedIndexes)
      }
      loop(this, 0, RNil)
    }
    override def map[S](func: T => S): RList[S] = {
      @tailrec
      def loop(list: RList[T], acc: RList[S]): RList[S] = {
        if (list.isEmpty) acc.reverse
        else loop(list.tail, func( list.head ) :: acc)
      }
      loop(this, RNil)
    }
    override def flatMap[S](func: T => RList[S]): RList[S] = {
      @tailrec
      def loop(list: RList[T], acc: RList[RList[S]]): RList[S] = {
        if (list.isEmpty) flatten(acc, RNil, RNil)
        else loop(list.tail, func( list.head ).reverse :: acc)
      }
      @tailrec
      def flatten(lists: RList[RList[S]], currentList: RList[S], acc: RList[S]): RList[S] = {
        if (lists.isEmpty && currentList.isEmpty) acc
        else if (currentList.isEmpty) flatten(lists.tail, lists.head, acc)
        else flatten(lists, currentList.tail, currentList.head :: acc)
      }
      loop(this, RNil)
    }
    override def filter(predicate: T => Boolean): RList[T] = {
      @tailrec
      def loop(list: RList[T], acc: RList[T]): RList[T] = {
        if(list.isEmpty) acc.reverse
        else if( predicate( list.head )) loop(list.tail, list.head :: acc)
        else loop(list.tail, acc)
      }
      loop(this, RNil)
    }
  }
}