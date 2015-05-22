/**
 * Created by eerilio on 5/22/15.
 */
object ConcurrencyInScala {
  //***************Runnable/Callable***************
  //runnable - single method that returns no value
  trait Runnable{
    def run() : Unit
  }
  //callable - single method that returns a value
  trait Callable[V]{
    def call() : V
  }


  //*******************Threads*********************
  //Scala concurrency is build ontop of Java model
  //A Thread makes a Runnable - need to call start to run it
  val run = new Runnable {
    override def run(): Unit ={
      println("Hello World")
    }
  }
  val hello = new Thread(run)

}
