import Basics.Calculator

/**
 * Created by eerilio on 5/20/15.
 */
object BasicsContinued {
  //*************Apply Methods***********
  //executes when a class or object only has one main use

  class Bar{
    def apply() = 0
  }
  val bar = new Bar
  bar() //returns Int = 0

  //************Objects*****************
  //Hold single instances of a class - singleton implementation

  object Timer{ //creates and initialized an single instance of class Timer (similar to static)
    var count = 0
    def currentCount():Long ={
      count +=1
      count //returns value of count
    }
  }

  Timer.currentCount() //Method call for Object in Scala

  //Classes and Objects can have the same name in Scala. Called "Companion Objects" Used commonly for factories

  class CompObject(foo:String)

  object CompObject{
    def apply(foo:String) = new CompObject(foo) //on apply, creates a new instance of Class CompObject inside CompObject object
  }

  //**********Functions are Objects*************
  //function = set of traits, a function with one arg is an instance of a Function1 trait

  class AddOne extends Function1[Int, Int]{ //creation of a class which extends a function
    def apply(m:Int): Int = m+1
  }
  val plusOne = new AddOne() //plusOne is now of type AddOne which extends Function1
  plusOne(1)//returns 2

  //Function1[Int,Int] can be rewritten as class AddOne extends (Int => Int)

  //************Packages**********************
  //Same as java - declare everything in file to be part of that package


  //****************Pattern Matching***************
  //Similar to switch-case statement
  val times =1
  times match{
    case 1 => "one"
    case 2 => "two"
    case _ => "some other number"
  }

  times match{
    case i if i==1 =>"one"
    case i if i==2 =>"two"
    case _ => "some other number"
  }

  //Matching on type
  //handles matching for different types of val/var
  def bigger(o: Any): Any = {
    o match {
      case i: Int if i < 0 => i - 1
      case i: Int => i + 1
      case d: Double if d < 0.0 => d - 0.1
      case d: Double => d + 0.1
      case text: String => text + "s"
    }
  }

  //Matching on class members
  //Allows cases to be passed in as classes
  def calcType(calc: Calculator) = calc match {
    case _ if calc.brand == "hp" && calc.model == "20B" => "financial"
    case _ if calc.brand == "hp" && calc.model == "48G" => "scientific"
    case _ if calc.brand == "hp" && calc.model == "30B" => "business"
    case _ => "unknown"
  }

  //********************Case Classes**************
  //Used to store and match the contents of a class

  case class newCalculator(brand: String, model: String) // Automatically have equality and toString methods based on arg
  val hp20b = newCalculator("hp", "20b")
  val hp20B = newCalculator("hp", "20b")
  hp20b == hp20B

  //case classes with pattern matching

  val newhp20b = newCalculator("hp", "20B")
  val newhp30b = newCalculator("hp", "30B")
  //pattern matching using newCalculator type variables
  def calcType(calc: newCalculator) =
    calc match {
    case newCalculator("hp", "20B") => "financial"
    case newCalculator("hp", "48G") => "scientific"
    case newCalculator("hp", "30B") => "business"
    case newCalculator(_, _) => "Calculator of unknown type" //underscore means any value
  }

  //************Exceptions*********************
  //use a try-catch-finally syntax with pattern matching

  try { //try method
    newCalculator.apply("dsd", null)
  } catch {//if exception is thrown, check case using pattern matching
    case e: NullPointerException => println(e, "the remote calculator service is unavailable. should have kept your trusty HP.")
  } finally { //execute finally
    println("oops")
  }



}
