package objektwerks.app

object EvalApp {
  import objektwerks.Eval._

  def main(args: Array[String]): Unit = {
    println(s"1 + 2 = ${eval("1 + 2")}")
    println(s"4 - 1 = ${eval("4 - 1")}")
    println(s"1 * 3 = ${eval("1 * 3")}")
    println(s"9 / 3 = ${eval("9 / 3")}")
    println(s"1 + 2 * 3 + 4 / 5 + 6 * 7 - 8 = ${eval("1 + 2 * 3 + 4 / 5 + 6 * 7 - 8")}")
  }
}