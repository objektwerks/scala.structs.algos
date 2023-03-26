package objektwerks

/**
  * For every number, you should be able to output:
  * 1. 'Fizz' if it is divisible by 3
  * 2. 'Buzz', if it is divisible by 5,
  * 3. 'FizzBuzz' if it is divisible by 3 * 5 (so that the words are combined)
  * 4. And in all other cases such as '14', return '14', the original number.
  */
object FizzBuzz:
  def apply(n: Int): String =
    n match 
      case i if i % 3 == 0 => "fizz"
      case j if j % 5 == 0 => "buzz"
      case _ => s"$n"