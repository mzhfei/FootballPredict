object Problem1 {
  val royalParent = Map("George" -> ("m", "William", "Catherine"), "Charlotte" -> ("f", "William", "Catherine"), "Louis" -> ("m", "William", "Catherine"), "Archie" -> ("m", "Harry", "Meghan"), "Savannah" -> ("f", "Autumn", "Peter"), "Isla" -> ("f", "Autumn", "Peter"), "Mia" -> ("f", "Zara", "Mike"), "Lena" -> ("f", "Zara", "Mike"), "Beatrice" -> ("f", "Andrew", "Sarah"), "Eugenie" -> ("f", "Andrew", "Sarah"), "Louise" -> ("f", "Edward", "Sophie"), "James" -> ("m", "Edward", "Sophie"), "Peter" -> ("m", "Mark", "Anne"), "Zara" -> ("f", "Mark", "Anne"), "William" -> ("m", "Diana", "Charles"), "Harry" -> ("m", "Diana", "Charles"), "Charles" -> ("m", "Elizabeth", "Philip"), "Anne" -> ("f", "Elizabeth", "Philip"), "Andrew" -> ("m", "Elizabeth", "Philip"), "Edward" -> ("m", "Elizabeth", "Philip"), "Elizabeth" -> ("f", "", ""), "Philip" -> ("m", "", ""), "Diana" -> ("f", "", ""), "Mark" -> ("m", "", ""), "Sophie" -> ("f", "", ""), "Sarah" -> ("f", "", ""), "Mike" -> ("m", "", ""), "Autumn" -> ("f", "", ""), "Meghan" -> ("f", "", ""), "Catherine" -> ("f", "", ""), "Timothy" -> ("m", "", ""), "Jack" -> ("m", "", ""), "Camilla" -> ("f", "", ""))


  def parents(p: String): Option[(String, String)] = {

    val names = royalParent.keys

    if(!royalParent.contains(p)){
      return None
    }

    val ppl = royalParent(p)
    val p1 = ppl._2
    val p2 = ppl._3

    Some(p1, p2)
  }

  def grandparents(p: String): Option[List[String]]={
    val k = parents(p)
    if(k.isEmpty){
      return None
    }
    val p1 = k.get._1
    val p2 = k.get._2

    val gp1 = parents(p1)
    val gp2 = parents(p2)
    val l = List()
    if(gp1.isEmpty && gp2.isEmpty){
      Some(Nil)
    }
    else if (gp1.isEmpty){
      Some(gp2.get._1 :: (gp2.get._2 :: l))
    }
    else if (gp1.get._1 == "" & gp1.get._2 ==""){
      Some(gp2.get._1 :: (gp2.get._2 :: l))
    }

    else if (gp2.isEmpty){
      Some(gp1.get._1 :: (gp1.get._2 :: l))
    }
    else if (gp2.get._1 == "" & gp2.get._2 ==""){
      Some(gp1.get._1 :: (gp1.get._2 :: l))
    }

    else{
      Some(gp1.get._1 :: (gp1.get._2 :: (gp2.get._1 :: (gp2.get._2 :: l))))
    }
  }

  def siblings(p: String): Option[List[String]]={
    val Parents = parents(p)

    var l:List[String] = List()

    royalParent.keys.foreach{
      i =>
        if ( i != p){
          val ps = parents(i)
          if(ps.get._1 != "" && ps.get._2 != ""){
            if (ps == Parents){
              l = i::l
            }
          }
        }
    }
    Some(l)
  }

  def firstCousins(p: String): Option[List[String]]={
    val GrandParents = grandparents(p)
    val Parents = parents(p)
    var l:List[String] = List()
    royalParent.keys.foreach{
      i =>
        if(i!=p){
          val gps = grandparents(i)
          val ps = parents(i)
          if (ps.get._1 != "" && ps.get._2 != ""){
            if (gps == GrandParents && ps != Parents){
              l = i::l
            }
          }
        }
    }
    Some(l)
  }

  def uncles(p: String): Option[List[String]]={
    val Parents = parents(p)
    val p1 = Parents.get._1
    val p2 = Parents.get._2

    val sb = siblings(p1).get ::: siblings(p2).get
    var l = List[String]()

    sb.foreach(i=>
      if (royalParent(i)._1 == "m"){
        l = i ::l
      })
    Some(l)
  }


  def main(args: Array[String]): Unit = {
    royalParent.keys.foreach{
      i=>
        print("Name is ")
        println(i)
        print("Parents are ")
        println(parents(i))
        print("Grandparents are ")
        println(grandparents(i))
        print("Siblings are ")
        println(siblings(i))
        print("FirstCousins are ")
        println(firstCousins(i))
        print("Uncles are ")
        println(uncles(i))
        println()
    }
    var i = "at"
    print("Name is ")
    println(i)
    print("Parents are ")
    println(parents(i))
    print("Grandparents are ")
    println(grandparents(i))
    print("Siblings are ")
    println(siblings(i))
    print("FirstCousins are ")
    println(firstCousins(i))
    print("Uncles are ")
    println(uncles(i))
    println()

  }
}
