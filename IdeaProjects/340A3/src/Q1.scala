object Q1{
  def unfold[A, S](z: S)(f: S => Option[(A, S)]): LazyList[A] = f(z).map((p: (A,S)) => p._1 #:: unfold(p._2)(f)).getOrElse(LazyList.empty[A])


  def perfect(i:Int): LazyList[Int]= {
    val ADD_ONE = unfold(1) {
      case x if x < i => Some(x, x + 1)
      case _ => None
    }.filter(s => i%s == 0)
    //    ADD_ONE.foreach(s=> println(s))
    ADD_ONE
  }

  def sum(i:Int):Boolean={
    var max = 0
    for ( x <- perfect(i)){
      max += x
    }
    if(max == i){
      true
    }
    else{
      false
    }
  }

  def perfectList(i:Int):LazyList[Int] = {
    var list = unfold(1){
      case x if (x <= i && sum(x)) =>
        Some(x, x+1)
      case x if (x <= i && !sum(x)) => Some(-1, x+1)
      case _ => None
    }

    //    list.foreach(s=> println(s))

    list = list.filter(s => s!= -1)
    //    list.foreach(s=> println(s))
    list
  }




  def main(args: Array[String]): Unit = {

    println( "Perfect Number list 496")
    perfectList(496).foreach(s=>println(s))

    println( "Perfect Number list 0")
    perfectList(0).foreach(s=>println(s))
    println( "Perfect Number list 50")
    perfectList(50).foreach(s=>println(s))
    println( "Perfect Number list 7")
    perfectList(7).foreach(s=>println(s))

  }
}
