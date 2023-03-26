package objektwerks

/**
  * A palindrome is a sequence of items that are identical when reversed.
  * For instance, 'aabb' is not a palindrome, nor 'ababb'.
  * But 'aba' and 'abba' are a palindrome.
  */
object Palindrome:
  def isPalindrome[T](list: List[T]): Boolean =
    list
      .zip(list.reverse)
      .forall { case (a, b) => a == b }