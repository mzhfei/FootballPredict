//import akka.actor._
//object Question1{
//  case class value(v: Int) extends AnyVal{
//    def getV: Int = {
//      v
//    }
//  }
//  case class sum(v: Int) extends AnyVal{
//    def getV: Int = {
//      v
//    }
//  }
//}
//
//class fibonacci(max:Int) extends Actor{
//  import Question1._
//  var result = 0
//  var received = 0
//  var send:ActorRef = context.actorOf(Props[Printer])
//  var at = 0
//
//
//  def receive: PartialFunction [Any, Unit] = {
//    case x: value =>
//      at = x.getV
//      send = sender()
//      if (x.getV <= 0){
//        val printer = context.actorOf(Props[Printer])
//        printer ! Printer.message(s"Received $max, enter Positive number please")
//        printer ! PoisonPill
//      }
//      if (x.getV ==2 || x.getV == 3) {
//        result = 1
//        if(at!= max){
//          sender() ! sum(result)}
//        else if(at == max){
//          val printer = context.actorOf(Props[Printer])
//          printer ! Printer.message(s"the $at th of fibanocci is : $result)")
//          printer ! PoisonPill
//        }
//      }
//      else if (max == 1){
//        val printer = context.actorOf(Props[Printer])
//        printer ! Printer.message(s"the $at th of fibanocci is : $result)")
//        printer ! PoisonPill
//        //        self ! PoisonPill
//      }
//      else if (x.getV > 2){
//        val m1 = context.actorOf(Props(classOf[fibonacci], max))
//        val m2 = context.actorOf(Props(classOf[fibonacci], max))
//        m1 ! value(x.getV -1)
//        m2 ! value(x.getV -2)
//      }
//
//    case x:sum =>
//      result += x.getV
//      received += 1
//      sender() ! PoisonPill
//      if (received == 2){
//        //        if(at!= max){
//        send ! sum(result)
//        //      }
//        //        if(at == max){
//        //          val printer = context.actorOf(Props[Printer])
//        //          printer ! Printer.message(s"this is result of fib$max: $result)")
//        //          printer ! PoisonPill
//        //        }
//      }
//  }
//
//}
//object Printer{
//  case class message(text:String)
//}
//class Printer extends Actor{
//  import  Printer._
//  def receive: PartialFunction[Any, Unit] = {
//    case message(text) =>
//      println(s"${sender().path}: $text")
//    //      sender() ! PoisonPill
//  }
//}
//
//class client extends Actor{
//  var fibs = List[(ActorRef,Question1.value)]()
//  def receive:PartialFunction[Any, Unit] = {
//    case x:Question1.value =>
//      val fib = context.actorOf(Props(classOf[fibonacci],x.getV))
//      fibs = (fib, x) ::fibs
//      fib ! Question1.value(x.getV)
//    case x:Question1.sum =>
//      for (e <- fibs){
//        if(sender == e._1){
//          val printer = context.actorOf(Props[Printer])
//          printer ! Printer.message(s"the ${e._2.getV}th of fibanocci is : ${x.getV})")
//          printer ! PoisonPill}}
//
//  }
//}
//
//object test extends App{
//  val system = ActorSystem("test")
//  var cl =   system.actorOf(Props(classOf[client]))
//  cl ! Question1.value(-1)
//  cl ! Question1.value(0)
//  cl ! Question1.value(1)
//  cl ! Question1.value(2)
//  cl ! Question1.value(3)
//  cl ! Question1.value(4)
//  cl ! Question1.value(8)
//  cl ! Question1.value(10)
//
//}