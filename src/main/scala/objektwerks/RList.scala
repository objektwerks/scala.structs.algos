package objektwerks

object RList {
  import scala.annotation.tailrec
  import scala.util.Random

  def iterable[T](iterable: Iterable[T]): RList[T] = {
    @tailrec
    def loop(iterable: Iterable[T], acc: RList[T]): RList[T] = {
      if (iterable.isEmpty) acc
      else loop(iterable.tail, iterable.head :: acc)
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
    def ++[S >: T](other: RList[S]): RList[S]
    def -=(index: Int): RList[T]
    def map[S](func: T => S): RList[S]
    def flatMap[S](func: T => RList[S]): RList[S]
    def filter(predicate: T => Boolean): RList[T]
    def count: RList[(T, Int)]
    def duplicate(by: Int): RList[T]
    def rotate(by: Int): RList[T]
    def random(by: Int): RList[T]
    def insertionSort[S >: T](implicit ordering: Ordering[S]): RList[S]
    def mergeSort[S >: T](implicit ordering: Ordering[S]): RList[S]
    def quickSort[S >: T](implicit ordering: Ordering[S]): RList[S]
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
    override def count: RList[(Nothing, Int)] = RNil
    override def duplicate(by: Int): RList[Nothing] = RNil
    override def rotate(by: Int): RList[Nothing] = RNil
    override def random(by: Int): RList[Nothing] = RNil
    override def insertionSort[S >: Nothing](implicit ordering: Ordering[S]): RList[S] = RNil
    override def mergeSort[S >: Nothing](implicit ordering: Ordering[S]): RList[S] = RNil
    override def quickSort[S >: Nothing](implicit ordering: Ordering[S]): RList[S] = RNil
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
      def loop(list: RList[T], current: Int): T = {
        if ( current == index) list.head
        else loop(list.tail, current + 1)
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
    override def ++[S >: T](list: RList[S]): RList[S] = {
      @tailrec
      def loop(other: RList[S], acc: RList[S]): RList[S] = {
        if (other.isEmpty) acc
        else loop(other.tail, other.head :: acc)
      }
      loop(this.reverse, list)
    }
    override def -=(index: Int): RList[T] = {
      @tailrec
      def loop(list: RList[T], current: Int, unmatchedIndexes: RList[T]): RList[T] = {
        if (current == index) unmatchedIndexes.reverse ++ list.tail
        else if (list.isEmpty) unmatchedIndexes.reverse
        else loop(list.tail, current + 1, list.head :: unmatchedIndexes)
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
      def flatten(lists: RList[RList[S]], current: RList[S], acc: RList[S]): RList[S] = {
        if (lists.isEmpty && current.isEmpty) acc
        else if (current.isEmpty) flatten(lists.tail, lists.head, acc)
        else flatten(lists, current.tail, current.head :: acc)
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
    override def count: RList[(T, Int)] = {
      @tailrec
      def loop(list: RList[T], current: (T, Int), acc: RList[(T, Int)]): RList[(T, Int)] = {
        if (list.isEmpty && current._2 == 0) acc
        else if (list.isEmpty) current :: acc
        else if (list.head == current._1) loop(list.tail, current.copy(_2 = current._2 + 1), acc)
        else loop(list.tail, (list.head, 1), current :: acc )
      }
      loop(this.tail, (this.head, 1), RNil).reverse
    }
    override def duplicate(by: Int): RList[T] = {
      @tailrec
      def loop(list: RList[T], current: T, duplicates: Int, acc: RList[T]): RList[T] = {
        if (list.isEmpty && duplicates == by) acc.reverse
        else if (list.isEmpty) loop(list, current, duplicates + 1, current :: acc)
        else if (duplicates == by) loop(list.tail, list.head, 0, acc)
        else loop(list, current, duplicates + 1, current :: acc)
      }
      loop(this.tail, this.head, 0, RNil)
    }
    override def rotate(by: Int): RList[T] = {
      @tailrec
      def loop(list: RList[T], current: Int, acc: RList[T]): RList[T] = {
        if (list.isEmpty && current == 0) this
        else if (list.isEmpty) loop(this, current, RNil)
        else if (current == 0) list ++ acc.reverse
        else loop(list.tail, current - 1, list.head :: acc)
      }
      loop(this, by, RNil)
    }
    override def random(by: Int): RList[T] = {
      val random = new Random(System.currentTimeMillis())
      val length = this.length
      @tailrec
      def loop(current: Int, acc: RList[T]): RList[T] = {
        if (current == 0) acc
        else {
          val index = random.nextInt(length)
          val number = this(index)
          loop(current -1, number :: acc)
        }
      }
      if (by < 0) RNil else loop(by, RNil)
    }
    override def insertionSort[S >: T](implicit ordering: Ordering[S]): RList[S] = {
      @tailrec
      def sort(element: T, before: RList[S], after: RList[S]): RList[S] = {
        if (after.isEmpty || ordering.lteq(element, after.head)) before ++ (element :: after)
        else sort(element, after.head :: before, after.tail)
      }
      @tailrec
      def loop(list: RList[T], acc: RList[S]): RList[S] = {
        if (list.isEmpty) acc
        else loop(list.tail, sort(list.head, acc, RNil))
      }
      loop(this, RNil).reverse
    }
    override def mergeSort[S >: T](implicit ordering: Ordering[S]): RList[S] = {
      @tailrec
      def merge(listA: RList[S], listB: RList[S], acc: RList[S]): RList[S] = {
        if (listA.isEmpty) acc.reverse ++ listB
        else if (listB.isEmpty) acc.reverse ++ listA
        else if (ordering.lteq(listA.head, listB.head)) merge(listA.tail, listB, listA.head :: acc)
        else merge(listA, listB.tail, listB.head :: acc)
      }
      @tailrec
      def loop(smallLists: RList[RList[S]], bigLists: RList[RList[S]]): RList[S] = {
        if (smallLists.isEmpty) {
          if (bigLists.isEmpty) RNil
          else if (bigLists.tail.isEmpty) bigLists.head
          else loop(bigLists, RNil)
        } else if (smallLists.tail.isEmpty) {
          if (bigLists.isEmpty) smallLists.head
          else loop(smallLists.head :: bigLists, RNil)
        } else {
          val first = smallLists.head
          val second = smallLists.tail.head
          val merged = merge(first, second, RNil)
          loop(smallLists.tail.tail, merged:: bigLists)
        }
      }
      loop(this.map(x => x :: RNil), RNil)
    }
    override def quickSort[S >: T](implicit ordering: Ordering[S]): RList[S] = {
      @tailrec
      def partition(list: RList[T], pivot: T, smaller: RList[T], larger: RList[T]): (RList[T], RList[T]) = {
        if (list.isEmpty) (smaller, larger)
        else if (ordering.lteq(list.head, pivot)) partition(list.tail, pivot, list.head :: smaller, larger)
        else partition(list.tail, pivot, smaller, list.head :: larger)
      }
      @tailrec
      def loop(lists: RList[RList[T]], acc: RList[RList[T]]): RList[T] = {
        if (lists.isEmpty) acc.flatMap(xs => xs).reverse
        else if (lists.head.isEmpty) loop(lists.tail, acc)
        else if (lists.head.tail.isEmpty) loop(lists.tail, lists.head :: acc)
        else {
          val list = lists.head
          val pivot = list.head
          val listToSplit = list.tail
          val (smaller, larger) = partition(listToSplit, pivot, RNil, RNil)
          loop(smaller :: (pivot :: RNil) :: larger :: lists.tail, acc)
        }
      }
      loop(this :: RNil, RNil)
    }
  }
}