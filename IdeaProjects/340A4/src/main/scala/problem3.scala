import akka.actor._
object Question3 {
  case class sentinel(a:Int){
    def getV:Int={
      a
    }}
  case class Num(a:Int){
      def getV:Int={
        a
      }}

  case class send(a:Int){
    def getV:Int={
          a
        }
  }

  case class end(a:Int){
    def getV:Int={
      a
    }
  }
}
class sort(Prime:ActorRef) extends Actor{
  import Question3._
  var Min:Int = Int.MaxValue
  var count = 0
  var nextNode:ActorRef = context.actorOf(Props(classOf[sort], self))

  def receive:PartialFunction[Any,Unit]={

    case x:Int =>

      count += 1
      if(Min > x && count == 1){
        Min = x
      }
      else if( Min > x && count > 1){
        nextNode ! Min
        Min = x
      }
      else if(Min <= x){
        nextNode ! x
      }

    case x:sentinel =>
      if (count == 1){
        Prime ! Num(Min)
      }
      else if( count > 1){
        Prime  ! Num(Min)
        Min = Int.MaxValue
        nextNode ! sentinel(0)
      }

//    case x:End =>
//      preNode ! End(0)

    case x:Num =>
      count -= 1
      if(x.getV < Min){
        Prime ! x
      }
      else {
        Prime ! Num(Min)
        Min = x.getV
      }
      if(count == 0){
//        Prime  ! end(Min)
        self ! PoisonPill
      }
  }
}

class client3 extends Actor {

  import Question3._

  var filter = context.actorOf(Props(classOf[sort], self))
  var before = List[Int]()

  var now = List[Int]()
  var count = 0

  def receive: PartialFunction[Any, Unit] = {
    case x: Num =>
      now = now ::: (x.getV :: List[Int]())
      count -= 1
      if (count == 0){
        val printer = context.actorOf(Props[Printer])
        printer ! Printer.message(s"the result of ordering $before is $now")
        printer ! PoisonPill
      }

    case x: send =>
      before = before ::: (x.getV :: List[Int]())

      filter ! x.getV
      count += 1
    case x: sentinel =>
      filter ! sentinel(0)

  }
}
object Printer3{
  case class message(text:String)
}
class Printer3 extends Actor{
  import  Printer3._
  def receive: PartialFunction[Any, Unit] = {
    case message(text) =>
      println(s"${sender().path}: $text")
    //      sender() ! PoisonPill
  }
}

object test3 extends App{
  import Question3._
  val system = ActorSystem("test3")

  val list1 = List[Int](1,2,3,4,5)
  val list2 = List[Int](5,4,3,2,1)
  val list3 = List[Int](4,5,3,3,1,5)
  val list4 = List[Int](1,3,2,5,4)

  val list5 = List[Int]()
  val list6 = List[Int](5)
  val list7 = List[Int](5,4)

  val allL = List[List[Int]](list1, list2, list3, list4, list5, list6, list7)

  for (e <- allL){
    val l = system.actorOf(Props[client3])
    for (i<-e){
      l ! Question3.send(i)
    }
    l ! sentinel(0)

  }

}