
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

  //foldLeft - m acts as an accumulator, n is the value in the list
  //foldRight - m acts as an accumulator, same as foldLeft but goes top down starting from highest index
  newnums.foldLeft(0)((m: Int, n: Int) => m + n) //Int = 55
  newnums.foldRight(0)((m: Int, n: Int) => m + n) //Int = 55

  //flatten - collapses one level of nested structure
  List(List(1, 2), List(3, 4)).flatten //List(1,2,3,4)

  //flatMap - combines flattening and mapping. Takes a function that works on nested lists and concatenates results
  val nestedNumbers = List(List(1, 2), List(3, 4))
  nestedNumbers.flatMap(x => x.map(_ * 2)) //List(2,4,6,8)


  //***********Generalized functional combinators***********
  //you can write own functional combinators

  def ourMap(numbers: List[Int], fn: Int => Int): List[Int] = {
    numbers.foldRight(List[Int]()) { (x: Int, xs: List[Int]) =>
      fn(x) :: xs
    }
  }

  ourMap(newnums, timesTwo(_)) //List(2, 4, 6, 8, 10, 12, 14, 16, 18, 20)

  //*************************Map?***************************
  //functional combinators all work on maps, since they can be viewed as list of pairs

  val extensions = Map("steve" -> 100, "bob" -> 101, "joe" -> 201)
  //filter out all extension less thant 200
  extensions.filter((namePhone: (String, Int)) => namePhone._2 < 200) //Map((steve,100), (bob,101))

  //we can use pattern matching to pull out the same values
  extensions.filter({case (name,extension) => extension <200}) //"labels # as extension and returns sets <200

}
