
object problem2{
  sealed trait Tree[A]
  case class Branch[A]( left: Tree[A], V: A, right: Tree[A])  extends Tree[A]


  def inOrder[A](tree: Tree[A]): List[A] = tree match {
    case null => Nil
    case Branch(l, v, r)  => inOrder(l) ::: ( v :: inOrder(r))
  }

  def PreOrder[A](tree: Tree[A]): List[A] = tree match {
    case null => Nil
    case Branch(l, v, r)  => (v ::  PreOrder(l)) ::: PreOrder(r)
  }

  def PostOrder[A](tree: Tree[A]): List[A] = tree match {
    case null => Nil
    case Branch(l, v, r)  => (PostOrder(l) ::: PostOrder(r)) ::: List(v)
  }

  def search[A](tree:Tree[A], key:A):Boolean = tree match{
    case  null => false
    case Branch(l, v, r)  if v == key=> v==key

    case Branch(l, v, r)  if v != key => search(l, key) || search(r, key)
  }

  def replace[A](tree: Tree[A], before:A, after: A):Tree[A] = tree match{
    case  null => null
    case Branch(l, v ,r) if v == before => Branch( replace(l, before, after), after, replace(r, before, after))
    case Branch(l, v ,r) if v != before => Branch( replace(l, before, after), v, replace(r, before, after))
  }

  def main(args: Array[String]): Unit = {
    var t = Branch(Branch( Branch(null, 4, null), 2, Branch(null, 5, null)), 1, Branch(null,3,null))
    print("test on an ordinary tree, the tree is ")
    println(t)
    println()

    print("InOrder is ")
    println(inOrder(t))

    print("PreOrder is ")
    println(PreOrder(t))


    print("PostOrder is ")
    println(PostOrder(t))

    print("search for 100 gives ")
    println(search(t,100))

    print("search for 2 gives ")
    println(search(t, 2))

    print("replace existing number to 100  ")
    println(replace(t, 1, 100))

    print("replace not existing number to 100  ")
    println(replace(t, 99, 100))

    println()
    println()

    var t2 = null
    print("test on a nulltree, the tree is ")
    println(t2)
    println()

    print("InOrder is ")
    println(inOrder(t2))

    print("PreOrder is ")
    println(PreOrder(t2))



    print("PostOrder is ")
    println(PostOrder(t2))


    print("search for 100 gives ")
    println(search(t2,100))


    print("search for 2 gives ")
    println(search(t2, 2))

    print("replace existing number to 100  ")
    println(replace(t2, 1, 100))

    print("replace not existing number to 100  ")
    println(replace(t2, 99, 100))


    println()
    println()


    var t3 = Branch(Branch( Branch(Branch(Branch(null, 5, null), 4, null), 3, null), 2, null), 1, null)
    print("test on an left node only tree, the tree is ")
    println(t3)
    println()

    print("InOrder is ")
    println(inOrder(t3))

    print("PreOrder is ")
    println(PreOrder(t3))


    print("PostOrder is ")
    println(PostOrder(t3))

    print("search for 100 gives ")
    println(search(t3,100))


    print("search for 2 gives ")
    println(search(t3, 2))

    print("replace existing number to 100  ")
    println(replace(t3, 1, 100))

    print("replace not existing number to 100  ")
    println(replace(t3, 99, 100))


  }


}