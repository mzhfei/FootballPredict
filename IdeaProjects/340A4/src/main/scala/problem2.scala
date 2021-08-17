import akka.actor._
object Question2{
  case class value(v: Int) extends AnyVal{
    def getV: Int = {
      v
    }
  }
  case class sum(v: Int) extends AnyVal{
    def getV: Int = {
      v
    }
  }
}

class fibonacci2(max:Int, Prime:ActorRef) extends Actor{
  import Question2._
  var result = 0
//  var send:ActorRef = context.actorOf(Props[fibonacci2])
  var received = 0
//  var at = max

  def receive: PartialFunction [Any, Unit] = {
    case x:value =>
      if(x.getV <= 0){

        val printer = context.actorOf(Props[Printer])
        printer ! Printer.message(s"Received $max, enter Positive number please")
        printer ! PoisonPill
      }
      else if (x.getV == 1){
        result = 0
        Prime ! Question2.sum(0)
      }
      else if (x.getV == 3 || x.getV == 2){
        self ! Question2.sum(1)

      }
      else if (x.getV > 2){
        val m = context.actorOf(Props(classOf[fibonacci2], x.getV-2, self))
        m ! Question2.value(x.getV -2)
        self ! Question2.value(x.getV - 1)
//        at -= 1
      }
    case x:sum =>
//      val printer = context.actorOf(Props[Printer])
////      printer ! Printer.message(s"Received $x, Prime is $Prime, from $sender, max is $max, received is $received")
//      printer ! PoisonPill
      received += 1
      result += x.getV
      if(received >= max -2){
          Prime ! Question2.sum(result)
      }
  }

}
object Printer2{
  case class message(text:String)
}
class Printer2 extends Actor{
  import  Printer2._
  def receive: PartialFunction[Any, Unit] = {
    case message(text) =>
      println(s"${sender().path}: $text")
//      sender() ! PoisonPill
  }
}
class client2 extends Actor {
  var fibs = List[(ActorRef, Question2.value)]()

  def receive: PartialFunction[Any, Unit] = {
    case x: Question2.sum =>
      for (e <- fibs){
        if(sender == e._1){
          val printer = context.actorOf(Props[Printer])
          printer ! Printer.message(s"the ${e._2.getV}th of fibanocci is : ${x.getV})")
          printer ! PoisonPill}}
    case x: Question2.value =>
      val fib = context.actorOf(Props(classOf[fibonacci2],x.getV, self))
      fibs = (fib, x) :: fibs
      fib ! Question2.value(x.getV)
  }


}
object test2 extends App{
  val system = ActorSystem("test2")

  val cl = system.actorOf(Props(classOf[client2]))
  cl ! Question2.value(-1)
  cl ! Question2.value(0)
  cl ! Question2.value(1)
  cl ! Question2.value(2)
  cl ! Question2.value(3)
  cl ! Question2.value(4)
  cl ! Question2.value(8)
  cl ! Question2.value(10)

}