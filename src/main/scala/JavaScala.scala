import java.io.IOException
import scala.beans.{BooleanBeanProperty, BeanProperty}
import scala.throws



/**
 * Created by eerilio on 5/22/15.
 */
object JavaScala {
  //***********************JAVAP********************
  //tool that comes with the JDK, decompiles class definitions

  //**********************Classes*******************
  //4 things to consider when using a Scala class from Java are:
  //1. Class Parameters
  //2. Class vals
  //3. Class vars
  //4. Exceptions

  //@BeanProperty annotates vals and vars similar to @Getter/@Setter
  class SimpleClass(name: String, val acc: String, @BeanProperty var mutable: String) {
    val foo = "foo"
    var bar = "bar"
    @BeanProperty
    val fooBean = "foobean"
    @BeanProperty
    var barBean = "barbean"
    @BooleanBeanProperty
    var awesome = true

    def dangerFoo() = {
      throw new IOException("SURPRISE!")
    }

    @throws(classOf[IOException])
    def dangerBar() = {
      throw new IOException("NO SURPRISE!")
    }
  }

  //Class parameters - effectively constructor args in Java
  //ex: declaring class parameter makes it accessible from java code
  class SimpleClass2(acc_: String) {
    val acc = acc_
  }

  //Vals - get a method defined for access from Java. You can access the value of the val “foo” via the method “foo()”

  //Vars - vars get a method _$eq defined. Can call using:
  foo$_eq("newfoo");
  //Or with annotation
  setFoo("newFoo");
  getFoo();

  //Exceptions
  //Scala has no checked exceptions, when calling in Java, need to explcitly define throw
  //Use the annotation above

  //**************************Traits*************************

  trait MyTrait{
    def traitName:String
    def upperTraitName = traitName.toUpperCase()
  }

  //MyTrait has on abstract method(traitName) and one implemented method(upperTraitName)
  //To use a trait in Java:
  //Compile error: MyTrait is not abstract and does not override abstract method upperTraitName()
  public class JTraitImpl implements MyTrait {
    private String name = null;

    public JTraitImpl(String name) {
      this.name = name;
    }

    public String traitName() {
      return name;
    }

    //Instead of rewriting method
    public String upperTraitName() {
      return MyTrait$class.upperTraitName(this); //works because `this` implements MyTrait
    }
  }

  //*****************************OBJECTS*********************************
  //Objects are how Scala implements static methods and singletons

  //ex:
  class TraitImpl(name: String) extends MyTrait {
    def traitName = name
  }

  object TraitImpl {
    def apply = new TraitImpl("foo")
    def apply(name: String) = new TraitImpl(name)
  }

  //In Java, we access:
  MyTrait foo = TraitImpl$.MODULE$.apply("foo")
  //This is legal because there arent actually any static methods, instead there is a static member named MODULE$
  //The method implementations delegate to this member

  //Forwarding Methods - can access methods in the TraitImpl Object below:
  MyTrait foo = TraitImpl.apply("foo");

  //**********************Closure Functions****************************
  //Treatment of functions as first class citizens
  //ex:
  class ClosureClass {
    def printResult[T](f: => T) = {
      println(f)
    }

    def printResult[T](f: String => T) = {
      println(f("HI THERE"))
    }
  }

  //To call in Scala:
  val a = new ClosureClass
  a.printResult {"Hi MoM"}

  //f:=>T translates to Function0 and “f: String => T” translates to “Function1

  //To call in Java use AbstractFunction0 and AbstractFunction1 to define Scala assignment
  ClosureClass c = new ClosureClass();
  c.printResult(new AbstractFunction0() {
    public String apply() {
      return "foo";
    }
  });

  c.printResult(new AbstractFunction1<String,String>(){
    public String apply(String arg)
    return arg + "foo";
  });


}
