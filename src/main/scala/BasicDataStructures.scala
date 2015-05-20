
/**
 * Created by eerilio on 5/20/15.
 */
object BasicDataStructures {
  //***************Lists*****************
  val numbers = List(1,2,3,4,5)

  //*************Sets********************
  //Have no duplicates
  val set = Set(1,1,1,3,3,2)
  println(set)//returns Set(1,3,2)

  //*************Tuple*******************
  //groups together similar logistic collections without a class

  val hostPort = ("localhost", 80)
  //dont have accessors, position based

  hostPort._1 //String = localhost
  hostPort._2 //Int = 80

  //can be used in pattern matching
  hostPort match {
    case ("localhost", port) => println("found")
    case (host, port) => println("Not found")
  }
   //special character ->
  1 -> 2 //(Int,Int) = (1,2)

  //*****************Maps***************
  //Holds basic datatypes

  Map(1->2)
  Map("Foo" -> "Bar")

  //Maps can also map other maps
  Map(1 -> Map("foo" -> "bar"))
  //Or functions

  def timesTwo(i:Int): Int = i*2
  Map("timesTwo" -> { timesTwo(_) })

  //****************Option*****************
  //container that may or may not hold something

  //Basic Option interface
  trait Option[T] { //Option is generic and has two subclasses Some[T] or None
    def isDefined: Boolean
    def get: T
    def getOrElse(t: T): T
  }

  val nums = Map("one" -> 1, "two" -> 2)
  nums.get("two") //Option[Int] = Some(2)
  nums.get("three") //Option[Int] = None

  //*****************Functional Combinators
  //operations which can be applied to the data types to return new structures


  //map - evaluates a function over each element in the list and returns same number of elements or pass in a function
  numbers.map((i:Int) => i*2) //List[Int] = List(2,4,6,8)
  numbers.map(timesTwo) //method passed in

  //foreach - like map but returns nothing
  numbers.foreach((i:Int) => i*2) //returns nothing

  //filter - removes any elements where the function you pass evaluates to false
  numbers.filter((i:Int) => i%2==0) //returns List(2,4)
  def isEven(i:Int): Boolean = 1%2==0
  numbers.filter(isEven _) //returns List(2,4)

  //zip - combines two lists into a single list of pairs
  List(1, 2, 3).zip(List("a", "b", "c")) //returns List[(Int, String)] = List((1,a), (2,b), (3,c))

  //partition - splits a list based on where it falls based on some function
  val newnums = List(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
  newnums.partition(_ % 2 == 0) //(List[Int], List[Int]) = (List(2, 4, 6, 8, 10),List(1, 3, 5, 7, 9))

  //find - returns the first element of a collection based off some function
  newnums.find((i: Int) => i > 5) //Option[Int] = Some(6)

  //drop & dropWhile - drop: drops first i elements
  //                   dropWhile: removes the first elements based off some function

  newnums.drop(5) //List[Int] = List(6, 7, 8, 9, 10)
  newnums.dropWhile(_ % 2 != 0) //List[Int] = List(2, 3, 4, 5, 6, 7, 8, 9, 10)


}
