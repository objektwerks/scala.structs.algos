package objektwerks

object EvalApp {
  import scala.annotation.tailrec

  def main(args: Array[String]): Unit = {
    println(s"1 + 2 = ${eval("1 + 2")}")
    println(s"4 - 1 = ${eval("4 - 1")}")
    println(s"1 * 3 = ${eval("1 * 3")}")
    println(s"9 / 3 = ${eval("9 / 3")}")
    println(s"1 + 2 * 3 + 4 / 5 + 6 * 7 - 8 = ${eval("1 + 2 * 3 + 4 / 5 + 6 * 7 - 8")}")
  }

  def eval(expr: String): Int = {
    val operators = Set("+", "-", "*", "/")

    def getOperators: List[String] = expr.split(" ").filter(operators.contains).toList
    def getNumbers: List[Int] = expr.split(" ").filter(!operators.contains(_)).map(_.toInt).toList

    def simpleOperation(op1: Int, op2: Int, operator: String) = operator match {
      case "+" => op1 + op2
      case "-" => op1 - op2
      case "*" => op1 * op2
      case "/" => op1 / op2
      case _ => throw new IllegalArgumentException(s"Invalid operator: $operator")
    }

    def priority(operator: String): Int = operator match {
      case "+" | "-" => 1
      case "*" | "/" => 2
      case _ => 0
    }

    @tailrec
    def loop(remainingOperands: List[Int],
             remainingOperators: List[String],
             operandStack: List[Int],
             operatorStack: List[String]): Int = {
      if (remainingOperands.isEmpty) {
        if (operatorStack.isEmpty) operandStack.head // final result
        else {
          // compute a simple operation and proceed
          val op2 = operandStack.head
          val op1 = operandStack.tail.head
          val operator = operatorStack.head
          val simpleResult = simpleOperation(op1, op2, operator)
          loop(remainingOperands, remainingOperators, simpleResult :: operandStack.drop(2), operatorStack.tail)
        }
      } else if (remainingOperands.length > remainingOperators.length) {
        // pop an operand and proceed
        loop(remainingOperands.tail, remainingOperators, remainingOperands.head :: operandStack, operatorStack)
      } else if (operatorStack.isEmpty || priority(operatorStack.head) < priority(remainingOperators.head)) {
        // pop an operator and proceed
        loop(remainingOperands, remainingOperators.tail, operandStack, remainingOperators.head :: operatorStack)
      } else {
        // compute a simple operation and proceed
        val op2 = operandStack.head
        val op1 = operandStack.tail.head
        val operator = operatorStack.head
        val simpleResult = simpleOperation(op1, op2, operator)
        loop(remainingOperands, remainingOperators, simpleResult :: operandStack.drop(2), operatorStack.tail)
      }
    }
    loop(getNumbers, getOperators, List(), List())
  }
}