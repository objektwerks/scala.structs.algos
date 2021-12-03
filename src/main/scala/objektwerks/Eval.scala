package objektwerks

object Eval:
  import scala.annotation.tailrec

  def eval(expr: String): Int =
    val operators = Set("+", "-", "*", "/")

    def getOperators: List[String] = expr.split(" ").filter(operators.contains).toList

    def getNumbers: List[Int] = expr.split(" ").filter(!operators.contains(_)).map(_.toInt).toList

    def evalSimpleOperation(op1: Int, op2: Int, operator: String) = operator match {
      case "+" => op1 + op2
      case "-" => op1 - op2
      case "*" => op1 * op2
      case "/" => op1 / op2
      case _ => throw new IllegalArgumentException(s"Invalid operator: $operator")
    }

    def prioritizeOperator(operator: String): Int = operator match {
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
          val simpleResult = evalSimpleOperation(op1, op2, operator)
          loop(remainingOperands, remainingOperators, simpleResult :: operandStack.drop(2), operatorStack.tail)
        }
      } else if (remainingOperands.length > remainingOperators.length) {
        // pop an operand and proceed
        loop(remainingOperands.tail, remainingOperators, remainingOperands.head :: operandStack, operatorStack)
      } else if (operatorStack.isEmpty || prioritizeOperator(operatorStack.head) < prioritizeOperator(remainingOperators.head)) {
        // pop an operator and proceed
        loop(remainingOperands, remainingOperators.tail, operandStack, remainingOperators.head :: operatorStack)
      } else {
        // compute a simple operation and proceed
        val op2 = operandStack.head
        val op1 = operandStack.tail.head
        val operator = operatorStack.head
        val simpleResult = evalSimpleOperation(op1, op2, operator)
        loop(remainingOperands, remainingOperators, simpleResult :: operandStack.drop(2), operatorStack.tail)
      }
    }
    loop(getNumbers, getOperators, List(), List())