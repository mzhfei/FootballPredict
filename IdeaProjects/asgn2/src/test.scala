object a{

  def splita[A](l: List[A], n: Int):List[A] =l match{
    case l1 if n == 0 => List()
    case l if n > l.length => sys.error("n larger than length")
    case l if n == l.length => l
    case l1 if n >= 1 => l1.head :: splita(l1.tail, n-1)
    case Nil => Nil
  }

  def splitb[A](l: List[A], n: Int):List[A] =l match{
    case l1 if n == 0 => l
    case l if n == l.length => List()
    case l1 if n >= 1 => splitb(l1.tail, n-1)
    case Nil => Nil
  }

  def main(args: Array[String]): Unit = {
    println(splita ( List(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15), 8))
    println(splita ( List(15), 1))

    println(splitb ( List(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15), 8))
    println(splitb ( List(15), 1))
  }
}