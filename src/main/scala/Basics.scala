
/**
 * Created by eerilio on 5/20/15.
 */
object Basics {
    //**********Expressions********
    def expressions() {
      1+1
    }

    //***********Values************
    def values(){
      val two = 1+1 //gives result a name, cant change binding values
      var name = "steve" //creates a variable, where values can be altered
      name = "marius"
    }

    //***********Functions***********
    def addOne(m: Int): Int = m+1 //def create functions
    val three = addOne(2) //type signature for function parameters required
    def four() = 2+2
    four() //You can leave parens on functions without arguments
    four

    //Anonymous Functions
    (x:Int) => x + 1 //function has no "name"
    (2) //function will return 2+1

    def timesTwo(i: Int): Int = { //parenthesis used to group large functions
      println("hello world")
      i * 2
    }

    (i:Int) =>{ //works for anonymous functions as well
      println("hello world")
      i*2
    }

    //partial application of functions
    def adder(m:Int, n:Int) = m+n
    val add2 = (2, _:Int) //underscore acts as "wildcard", results in parameter being passed to fill in
    add2(3) //returns 5 since 3 is passed in place of n

    //curried functions
    def multiply(m:Int)(n:Int) = m*n
    val mult = multiply(2)_ //value mult has partial parameters passed, to be filled in somewhere else
    mult(3)//mult has value 6, since 3 is passed into the blank

    //variable length arguments
    def capitalizeAll(arg:String*)={ //method can take multiple parameters of the same type
      arg.map {arg => arg.capitalize}
    }
    println(capitalizeAll("rarity", "applejack")) //returns Seq[String] = ArrayBuffer(Rarity, Applejack)

    //**********CLASSES************
    class Calculator{ //Creation of new class Calculator
      val brand: String = "HP" //initialization of value
      def add(m:Int, n:Int): Int = m+n //creation of method
      val model: String = "20B"
    }
    val calc = new Calculator //initialization of new calculator
    calc.add(1,2) //method call
    calc.brand //value call

    //constructors
    class Calculator2 (input:String){ //Create new class with constructor of String
      val color: String = if (input == "TI"){
        "blue"
      }else if (input=="HP"){
        "black"
      }else
        "white"

      def add(m: Int, n: Int): Int = m + n //Instance methods
    }

    val calc2 = new Calculator2("HP") //passes value "HP" into instantiation of class
    println(calc2.color) //will return black

    //example above shows how Scala is expression oriented, the value of color was based off an expression rather than a statement

    //functions vs methods
    class C{
      var acc=0
      def minc = {acc+=1}
      val finc = {() => acc+=1}
    }

    val c = new C
    c.minc //calls c.minc which is a method
    c.finc //returns the function as a value

    //**************Inheritance****************
    class ScientificCalculator(brand: String) extends Calculator2(brand) { //creation of class extends another class with passed parameters
      def log(m: Double, base: Double) = math.log(m) / math.log(base) //Instance method
    }

    class EvenMoreScientificCalculator(brand: String) extends ScientificCalculator(brand) {
      def log(m: Int): Double = log(m, math.exp(1)) //Method overwrites method from inherited parent class
    }

    //Abstract Classes
    abstract class Shape{ //abstract class definition same as Java
      def getArea():Int //inherited methods
    }

    class Circle(radius:Int) extends Shape{
      def getArea():Int ={radius * radius * 3} //overwritten method from inheritance
    }

    val s = new Circle(3)


    //***************Traits*********************
    //Allow classes to extended to mix values and variables into

    trait Car{
      val brand :String
    }
    trait Shiny {
      val shineRefraction: Int
    }

    class BMW extends Car with Shiny { //traits use "extend" to add to classes; "with" allows multiple extensions
      val brand = "BMW"
      val shineRefraction = 12
    }

    //Traits allow classes to extend multiple aspects, only one abstract class can be extended at once
    //if constructors are needed, use abstract class

    //***************Types*************************
    //functions can be generic, and allow any type of input

    trait Cache[K, V] {
      def get(key: K): V
      def put(key: K, value: V)
      def delete(key: K)
    }
}

