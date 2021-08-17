// CMPT340 A3.2Q2
// Jiawei Zhang
// 11241544
// jiz038

object problem2 {

  def unfold[A, S](z: S)(f: S => Option[(A, S)]): LazyList[A] =
    f(z).map((p: (A,S)) => p._1 #:: unfold(p._2)(f)).getOrElse(LazyList.empty[A])




  def pefertNum(t:Int):LazyList[Int] = {
    if (t<=0) LazyList.empty
    else{
      val l = unfold(1)(r => Some(r, r + 1))
      //println(l.take(t).toList)
      val l1 = l.take(t).toList
      var finaL= LazyList[Int]()
      for {x<-l1}{
        if(helper(x)) finaL ++= LazyList(x)
      }
      finaL
    }

  }

  def helper(a:Int):Boolean={
    val l = unfold(1)(r => Some(r, r + 1))
    //println(l.take(a).toList)
    val list = l.take(a).toList.filter(a %_ ==0)
    //println(list)
    var f = 0
    for{z<-list}{
      if(z<a) f+=z
    }
    if(f==a) true
    else false
  }


  def main(args: Array[String]): Unit = {

    // Test case 1
    if (pefertNum(-3).toList != List())println("Error: pefertNum() does not work.")
    else println("PASS!")

    // Test case 2
    if (pefertNum(0).toList != List())println("Error: pefertNum() does not work.")
    else println("PASS!")

    // Test case 3
    if (pefertNum(3).toList != List())println("Error: pefertNum() does not work.")
    else println("PASS!")

    // Test case 4
    if (pefertNum(6).toList != List(6))println("Error: pefertNum() does not work.")
    else println("PASS!")

    // Test case 5
    if (pefertNum(10).toList != List(6))println("Error: pefertNum() does not work.")
    else println("PASS!")

    // Test case 6
    if (pefertNum(30).toList != List(6,28))println("Error: pefertNum() does not work.")
    else println("PASS!")

    // Test case 7
    if (pefertNum(500).toList != List(6, 28, 496))println("Error: pefertNum() does not work.")
    else println("PASS!")


  }


}
