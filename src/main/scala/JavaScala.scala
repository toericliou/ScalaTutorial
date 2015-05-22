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


}
