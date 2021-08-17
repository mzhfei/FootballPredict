object A2Q3 {
  def unfold[A, S](z: S)(f: S => Option[(A, S)]): LazyList[A] = f(z).map((p: (A,S)) => p._1 #:: unfold(p._2)(f)).getOrElse(LazyList.empty[A])

  val double = unfold(1){
    case x if x < 100 => Some(x, x+x)
    case _ => None
  }

  val finite = unfold(1){
    case x if x < 100 => Some(x, x+x)
    case _ => None
  }

  val finite = unfold(1){
    case x if x < 100 => Some(x, x+x)
    case _ => None
  }
}
