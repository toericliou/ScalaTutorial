/**
 * Created by eerilio on 5/21/15.
 */
object AdvancedTypes {
  //********************View Bounds***********************
  //Sometimes you don't need to specify that one type is equal/sub/super another just so that you could
  //fake it with conversations
  //Implicit functions allow automatic conversion -> allow on-demand function application

  implicit def strToInt(x: String) = x.toInt
  val y: Int = "123" //y: Int = 123 -> the implicit function is automatically applied to convert 123 to int
  math.max("123", 111) //returns Int = 123

  class Container1[A <% Int]{ //<% specifies a view bound (means A has to viewable as Int)
    def addIt(x:A) = 123+x
  }

  (new Container1[String]).addIt("123") //Int=246 since implicit method auto-converts string to int
  (new Container1[Int]).addIt(123) //Int=246

  //****************Other Type Bounds*********************
  //Methods can enforce more complex type bounds via implicit parameters
  //Methods may ask for some kinds of specific “evidence” for a type instead of setting up objects, use one of these type-relation operators
  //A =:= B,	A must be equal to B
  //A <:< B, 	A must be a subtype of B
  //A <%< B, 	A must be viewable as B

  class Container2[A](value: A){
    def addIt(implicit evidence: A =:= Int) = 123 + value
  }

  (new Container2(123)).addIt //Int =246
  //(new Container2("123")).addIt //error: could not find implicit value for parameter evidence: =:=[java.lang.String,Int]

  //Generic programming with views
  //views are primarily used to implement generic functions over collections
  //advantages: items in collection arent required to implement Ordered, you can define your own orderings without any additional library support


  //ex:

  List(1,2,3,4).min //Returns Int=1
  List(1,2,3,4).min(new Ordering[Int]{ //returns Int = 4
    def compare(a:Int, b:Int) = b compare a
  })

  //******Higher-kinded types & Ad-Hoc polymorphism*******
  //Scala can abstract over "higher kinded" types
  //Assume you define a Container interface that is implemented over other types
  //you want to define an interface for using values in these containers without nailing down the values type

  trait Container[M[_]]{
    def put[A](x:A):M[A];
    def get[A](m: M[A]):A
  }

  val container = new Container[List] {
    def put[A](x: A) = List(x);
    def get[A](m: List[A]) = m.head
  }
  container.put("hey") //List(hey) of type string
  container.put(123) //List(123) of type int

  //combining containers with implicits, we get ad-hoc polymorphism: ability to write generic function over containers
  implicit val listContainer = new Container[List] {
    def put[A](x: A) = List(x);
    def get[A](m: List[A]) = m.head
  }

  implicit val optionContainer = new Container[Some] {
    def put[A](x: A) = Some(x);
    def get[A](m: Some[A]) = m.get
  }

  def tupleize[M[_]:Container,A,B](fst:M[A], snd:M[B])={
    val c = implicitly[Container[M]]
    c.put(c.get(fst), c.get(snd))
  }

  tupleize(Some(1), Some(2)) //Some[(Int, Int)] = Some((1,2))
  tupleize(List(1), List(2)) //List[(Int, Int)] = List((1,2))

  //*************F-bounded polymorphism****************
  //Often it is necessary to access a concrete subclass in a (generic) trait.


  //****************Structural types********************
  //scala supports structural types - type requirements are expressed by interface structure instead of a concrete type

  def foo(x: { def get: Int }) = 123 + x.get

}
