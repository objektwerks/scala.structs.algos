package objektwerks

/**
  * For every number input, you should be able to output:
  * 1. if divisible by 3 return "fizz"
  * 2. else if divisible by 5 return "buzz"
  * 3. else if divisible by 15 ( 3 * 5 ) return "fizzbuzz"
  * 4. else return input number
  */
object FizzBuzz:
  def apply(n: Int): String =
    n match 
      case i if i % 3 == 0 => "fizz"
      case j if j % 5 == 0 => "buzz"
      case k if k % 15 == 0 => "fizzbuzz"
      case _ => s"$n"