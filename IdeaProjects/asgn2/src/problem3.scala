object problem3{
  def unfold[A, B] (p: A => Boolean, h: A => B, t: A => A)(x: A) : List[B] =
    if (p(x)) Nil
    else h(x) :: unfold(p, h, t) (t(x))


  def int2bin(n:Int): List[Int]={
    def check(n:Int):Boolean= n ==0
    def remainder(n:Int): Int= n%2
    def divide(n:Int): Int= n/2

    unfold(check, remainder, divide)(n)

  }

  def repHalve[A](l:List[A]): List[List[A]]={

    def check(l:List[A]):Boolean= l.length == 0

    def h(l:List[A]): List[A]= {
      var n = l.length/2 + l.length%2
      def split[A](l: List[A], n: Int):List[A] =l match{
        case l1 if n == 0 => List()
        case l if n == l.length => l
        case l1 if n >= 1 => l1.head :: split(l1.tail, n-1)
        case Nil => Nil
      }
      split(l, n)
    }

    def t(l:List[A]): List[A]= {
      var n = l.length/2 + l.length%2
      def split[A](l: List[A], n: Int):List[A] =l match{
        case l1 if n == 0 => l
        case l if n == l.length => List()
        case l1 if n >= 1 => split(l1.tail, n-1)
        case Nil => Nil
      }
      split(l, n)
    }

    unfold(check, h,t)(l)
  }

  def main(args: Array[String]): Unit = {

    print("0 to binary is ")
    println(int2bin(0))

    print("15 to binary is ")
    println(int2bin(6))

    print("99 to binary is ")
    println(int2bin(99))


    println("cut list (1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15)")
    println(repHalve (List(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15)))

    println("cut list ()")
    println(repHalve(List()))
  }


}