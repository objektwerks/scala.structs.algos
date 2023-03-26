package objektwerks

object Palindrome:
  def isPalindrome[T](list: List[T]): Boolean =
    list
      .zip(list.reverse)
      .forall { case (a, b) => a == b }