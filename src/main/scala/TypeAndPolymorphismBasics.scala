/**
 * Created by eerilio on 5/21/15.
 */
object TypeAndPolymorphismBasics {
  //*****What are static types? Why are they useful?*****
  //types allow you to denote function domain and codomains

  //******************Types in Scala*********************
  /*
  parametric polymorphism - generic programming
  local type interference - roughly, why you dont need say val i: Int = 12: Int
  existential quantification - defining something for some unnamed type
  views - castability of values of one type to another
  */

  //****************Parametric Polymorphism**************
  //used in order to write generic code (for values of different types)

  //without parametric polymorphism, code would look like:
  2 :: 1 :: "bar" :: "foo" :: Nil //List (2,1,bar,foo)

  //polymorphism achieved through specfiying type variables

  //Scala has rank-1 polymorphism
  //some type concepts are "too generic" for the compiler to understand

  def toList[A](a: A) = List(a)

  //def foo[A, B](f: A => List[A], b: B) = f(b)  //doesn't compile since type variables have to be fixed at the invocation site

  //******************Type inference*********************
  //objection to static typing is syntactic overhead
  //Type inference refers to the automatic deduction of the data type of an expression in a programming language
  //In Scala, all type inference is local - one expression at a time

  def id[T](x: T) = x //returns id of x

  val x = id(322) // Int = 322

  val x2 = id("hey") // java.lang.String = hey

  val x3 = id(Array(1,2,3,4)) //Array[Int] = Array(1,2,3,4)

  //types are now preserved, the compiler infers the type parameter, did not specify the return type explicitly


  //*********************Variance************************
  //Scala's type system has to account for class hierarchies together with polymorphism
  //Central question of OO: if T’ is a subclass of T, is Container[T’] considered a subclass of Container[T]?
  //Variance allows you to express the following relationships between class hierarchies

  /*covariant	        C[T’] is a subclass of C[T]	          [+T]
    contravariant	    C[T] is a subclass of C[T’]	          [-T]
    invariant	        C[T] and C[T’] are not related	      [T]
   */

  class Covariant[+A] //defined class Covariant
  val cv: Covariant[AnyRef] = new Covariant[String]
  //val cv: Covariant[String] = new Covariant[AnyRef]    //Error since AnyRef > String

  class Contravariant[-A] //defined class Contravariant
  val cv2: Contravariant[String] = new Contravariant[AnyRef]
  //val fail: Contravariant[AnyRef] = new Contravariant[String] //Error since AnyRef >String

  //example:

  class Animal {val sound = "rustle"}

  class Bird extends Animal {override val sound ="call"}

  class Chicken extends Bird {override val sound = "cluck"}

  val getTweet: (Bird => String) = ((a: Animal) => a.sound )
  //function parameters are contravariant -> need a function that takes B,and you have one that takes C, error on D
  //But A is ok.

  //*********************Bounds*************************
  //Scala allows you to restrict polymorphic variables using bounds which express subtype relationships

  //def cacaphony[T](things: Seq[T]) = things map (_.sound) //error since sound is not a parameter of T

  def biophony[T <: Animal](things: Seq[T]) = things map (_.sound)

  biophony(Seq(new Chicken, new Bird)) // List(cluck, call)

  //lower type bounds are supported
  val flock = List(new Bird, new Bird) //List[Bird]
  new Chicken :: flock //List[Bird]
  new Animal :: flock //List[Animal]

  //********************Quantification******************
  //Sometimes, you dont care about naming a type variable

  def count[A](l:List[A]) = l.size //count: [A](List[A])Int

  //Instead use wildcard
  def count2(l: List[_]) = l.size // count: (List[_])Int
  def count3(l: List[T forSome { type T }]) = l.size //returns count: (List[T forSome { type T }])Int

  def hashcodes(l: Seq[_ <: AnyRef]) = l map (_.hashCode)
  //hashcodes(Seq(1,2,3)) //throws exception because Int cannot be converted to AnyRef
  hashcodes(Seq("one", "two", "three")) // returns Seq[Int] = List(110182, 115276, 110339486)

}
