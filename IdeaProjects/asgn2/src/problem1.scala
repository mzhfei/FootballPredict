

    object problem1 {


      def shuffle[A](x: List[A], y: List[A]): List[A] = (x, y) match {
        case (Nil, y) => y
        case (x, Nil) => x

        case (x, y) => x.head :: (y.head :: shuffle(x.tail, y.tail))
      }
//
      def split[A](l: List[A], n: Int):List[List[A]] =l match{

        case l1 if n == 0 => List( List(), l1)
        case l if n > l.length => sys.error("n larger than length")
        case l if n == l.length => List(l, Nil)
        case l1 if n >= 1 => List(l1.head :: split(l1.tail, n-1).head , split(l1.tail, n-1)(1))
        case Nil => List(Nil, Nil)
      }

      def outshuffle[A](l:List[A]):List[A] = l match{
        case l if l.length%2 != 0 => sys.error("amount of elements is not even")
        case Nil => Nil
        case l => shuffle(  split(l, l.length /2).head, split(l, l.length/2)(1))
      }

      def inshuffle[A](l:List[A]):List[A] = l match{
        case l if l.length%2 != 0 => sys.error("amount of elements is not even")
        case Nil => Nil
        case l => shuffle(  split(l, l.length /2)(1), split(l, l.length/2).head)
      }

      def nshuffle[A](shuffle:List[A]=>List[A], n:Int, l:List[A]):List[A] = l match{
        case l if l.length%2 != 0 => sys.error("amount of elements is not even")
        case Nil => Nil
        case l if n == 0 => l
        case l if n > 0 =>  nshuffle(shuffle,  n-1, shuffle(l))
      }

      def compare[A](l1:List[A], l2:List[A]): Boolean=  (l1,l2) match{
        case(Nil, Nil) => true
        case(a::arest, b:: brest) if a == b => compare(arest, brest)
        case(a::arest, b:: brest) if a != b => false
      }

      def howManyShuffles[A](shuffle:List[A]=>List[A], l1:List[A], l2:List[A]):Int = (l1,l2) match{
        case (l1, l2) if l1.length != l2.length => sys.error("amount of elements is not even")
        case (l, l2) if l.length%2 != 0 => sys.error("amount of elements is not even")
        case (Nil, Nil) => 0

        case (l, l2) if !compare(l, l2) => 1+ howManyShuffles(shuffle, shuffle(l1), l2)
        case (l, l2) if compare(l, l2)=> 0
//        case (l, l2) if l != l2 => 1+ howManyShuffles(shuffle, shuffle(l1), l2)
//        case (l, l2) if l == l2 => 0
      }


      def main(args:Array[String]):Unit={
        //test shuffle
        val l1 = List("a","b","c","d")
        val l2 = List("1","2","3","4","5")
        var lempty = List()
        val l4 =List(1,2,3,4,5,6)


        print("The first STRING List is ")
        println(l1)

        print("The second STRING List is ")
        println(l2)

        print("The third List is ")
        println(lempty)

        println("---------test shuffle---------")

        println("TEST SHUFFLE LIST WITH SAME LENGTH: ")
        println("THE RESULT SHOULD BE (a, a, b, b, c, c, d, d)")
        print("and the result is: ")
        println(shuffle(l1, l1))
        println()

        println("TEST SHUFFLE LIST WITH DIFFERENT LENGTH: ")
        println("THE RESULT SHOULD BE (a, 1, b, 2, c, 3, d, 4, 5)")
        print("and the result is: ")
        println(shuffle(l1, l2))
        println()

        println("TEST SHUFFLE LIST WITH ONE EMPTY: ")
        println("THE RESULT SHOULD BE (a, b, c, d)")
        print("and the result is: ")
        println(shuffle(lempty, l1))
        println()

        println("TEST SHUFFLE LIST WITH TWO EMPTY: ")
        println("THE RESULT SHOULD BE ()")
        print("and the result is: ")
        println(shuffle(lempty, lempty))
        println()




        //test split
        println("---------test split----------")

        println("TEST SPLIT AT MIDDLE: ")
        println("THE RESULT SHOULD BE ( (a, b), (c, d))")
        print("and the result is: ")
        println(split(l1,2 ))
        println()

        println("TEST SPLIT AT start: ")
        println("THE RESULT SHOULD BE ( (a, b, c, d), ())")
        print("and the result is: ")
        println(split(l1,0 ))
        println()

        println("TEST SPLIT AT end: ")
        println("THE RESULT SHOULD BE ( (),(a, b, c, d) )")
        print("and the result is: ")
        println(split(l1,4 ))
        println()


        println("TEST SPLIT empty: ")
        println("THE RESULT SHOULD BE ( (),() )")
        print("and the result is: ")
        println(split(lempty,0 ))
        println()

        //test outshuffle

        println("------- TEST OUTSHUFFLE----------")

        println("TEST outshuffle empty: ")
        println("THE RESULT SHOULD BE ( )")
        print("and the result is: ")
        println(outshuffle(lempty))
        println()


        println("TEST outshuffle : ")
        println("THE RESULT SHOULD BE ( a, c, b, d)")
        print("and the result is: ")
        println(outshuffle(l1))
        println()

        println("------- TEST INSHUFFLE----------")

        println("TEST INshuffle empty: ")
        println("THE RESULT SHOULD BE ( )")
        print("and the result is: ")
        println(inshuffle(lempty))
        println()


        println("TEST inshuffle : ")
        println("THE RESULT SHOULD BE ( c, a, d, b)")
        print("and the result is: ")
        println(inshuffle(l1))
        println()


        println("------- TEST NSHUFFLE----------")

        println("TEST NSHUFFLE WITH INSHUFFLE empty: ")
        println("THE RESULT SHOULD BE ( )")
        print("and the result is: ")
        println(nshuffle(inshuffle: List[Any] => List[Any], 6 ,lempty))
        println()

        println("TEST NSHUFFLE WITH OUTSHUFFLE empty: ")
        println("THE RESULT SHOULD BE ( )")
        print("and the result is: ")
        println(nshuffle(inshuffle: List[Any] => List[Any], 3 ,lempty))
        println()

        println("TEST NSHUFFLE WITH INSHUFFLE 0 TIMES: ")
        println("THE RESULT SHOULD BE (  (a, b, c, d))")
        print("and the result is:")
        println(nshuffle(inshuffle: List[Any] => List[Any], 0, l1))
        println()

        println("TEST NSHUFFLE WITH OUTSHUFFLE O TIMES: ")
        println("THE RESULT SHOULD BE ( (a, b, c, d))")
        print("and the result is: ")
        println(nshuffle(inshuffle: List[Any] => List[Any], 0, l1))
        println()

        println("TEST NSHUFFLE WITH INSHUFFLE 3 TIMES: ")
        println("THE RESULT SHOULD BE (((b, d, a, c) )")
        print("and the result is: ")
        println(nshuffle(inshuffle: List[Any] => List[Any], 3, l1))
        println()

        println("TEST NSHUFFLE WITH INSHUFFLE 2 TIMES: ")
        println("THE RESULT SHOULD BE ( (d, c, b, a))")
        print("and the result is: ")
        println(nshuffle(inshuffle: List[Any] => List[Any], 2, l1))
        println()



        println("TEST NSHUFFLE WITH OUTSHUFFLE 3 TIMES: ")
        println("THE RESULT SHOULD BE ((a, c, b, d) )")
        print("and the result is: ")
        println(nshuffle(outshuffle: List[Any] => List[Any], 3, l1))
        println()

        println("TEST NSHUFFLE WITH OUTSHUFFLE 2 TIMES: ")
        println("THE RESULT SHOULD BE ( (a, b, c, d))")
        print("and the result is: ")
        println(nshuffle(outshuffle: List[Any] => List[Any], 2, l1))
        println()


        println("------- TEST howManyShuffle----------")

        //test how many shuffle
        val poker=List(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52)
        val pokerFirstO = outshuffle(poker)
        val pokerR=List(52, 51, 50, 49, 48, 47, 46, 45, 44, 43, 42, 41, 40, 39, 38, 37, 36, 35, 34, 33, 32, 31, 30, 29, 28, 27, 26, 25, 24, 23, 22, 21, 20, 19, 18, 17, 16, 15, 14, 13, 12, 11, 10, 9, 8, 7, 6, 5, 4, 3, 2, 1)

        println("How many out-shuffles are required to return a stack of 52 cards to its original ordering?")
        var count = 1 + howManyShuffles(outshuffle: List[Int] => List[Int],pokerFirstO,poker)
        println(count)
        println("use answer to outshuffle poker, and it gives")
        var newl = nshuffle(outshuffle: List[Any] => List[Any], count, poker)
        println(newl)
        print("compare with original poker, are they identical:---------")
        println(newl == poker)

        println("How many in-shuffles are required to completely reverse a stack of 52 cards?")
        var count2 = howManyShuffles(inshuffle: List[Int] => List[Int],poker, pokerR)
        println(count2)
        println("use answer to inshuffle poker, and it gives")
        var newl2 = nshuffle(inshuffle: List[Any] => List[Any], count2, poker)
        println(newl2)
        print("compare with reversed poker, are they identical:---------")
        println(newl2 == pokerR)

      }



}
