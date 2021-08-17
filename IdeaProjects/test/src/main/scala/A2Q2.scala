
trait Partial[+A,+B]{
  def map[C](f:B=>C): Partial[A,C] = this match{
    case Success(a) => f(a)
  }
  def flatMap[AA >: A,C](f:B=> Partial[AA,C]): Partial[AA,C]
  def orElse[AA >: A,C >: B](c: B=> Partial[AA,C]): Partial[AA,C]
  def map2[AA >: A, C, D](c: Partial[AA, C])(f: (B,C) => D): Partial[AA, D]

}
case class Errors[+A](get: Seq[A]) extends Partial[A,Nothing]

case class Success[+B](get: B) extends Partial[Nothing,B]






