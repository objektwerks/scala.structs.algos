package objektwerks

object Triangle:
  trait Kind
  case object scalene extends Kind     // 0 sides equal
  case object isoceles extends Kind    // 2 sides equal
  case object equilateral extends Kind // 3 sides equal

case class Triangle(a: Int, b: Int, c: Int):
  import Triangle.*
  
  def kind: Kind =
    (a, b, c) match
      case (x, y, z) if x == y && y == z => equilateral
      case (x, y, z) if x == y || y == z || z == x => isoceles
      case _ => scalene