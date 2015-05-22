/**
 * Created by eerilio on 5/21/15.
 */
object FunctionComposition {
  def f(s: String) = "f(" + s + ")"
  def g(s: String) = "g(" + s + ")"

  //compose - makes a new function that composes other functions
  val fComposeG = f _ compose g _ //creates a composition of f(g(x))
  fComposeG("yay") //returns f(g(yay))

  //andThen - like compose, but calls first function then the second
  val fAndThenG = f _ andThen g _
  fAndThenG("yay") // returns g(f(yay)

  //*****************Currying vs Partial Application*****************
  //case statements are a subclass of a function called PartialFuntion
  //therefore, a collection of multiple case statements are just multiple PartialFunctions composed together

  //*****************Understanding PartialFunction*******************
  //a typical function works for every argument of defined typed
  //PartialFunction is only defined for certain values of a defined type ex: A Partial Function (Int) => String might not accept every Int.

  val one : PartialFunction[Int, String] = {case 1 => "one"} //function takes in Int, returns String if exists
  one.isDefinedAt(1) // returns true since 1 is covered by case
  one.isDefinedAt(2) // returns false

  //can apply partial function
  one(1) //String = one

  //PartialFunctions can be composed with something new, called orElse that reflects whether the PartialFunction
  //is defined over the supplied argument

  val two: PartialFunction[Int, String] = { case 2 => "two" }
  val three: PartialFunction[Int, String] = { case 3 => "three" }
  val wildcard: PartialFunction[Int, String] = {case 4 => "else"}
  val partial = one orElse two orElse three orElse wildcard //orElse checks each partial function, creating pattern matching

  partial(5) //else
  partial(3) //three

  //mystery of case - using a case statement where a function is typically used

  case class PhoneExt(name: String, ext: Int)
  val extensions = List(PhoneExt("steve", 100), PhoneExt("robey", 200)) //PhoneExt is a case class
  extensions.filter { case PhoneExt(name, extension) => extension < 200 } //returns List(PhoneExt("steve",100))

  //Why? because filter takes a function, but a PartialFunction is a subtype of Function


}
