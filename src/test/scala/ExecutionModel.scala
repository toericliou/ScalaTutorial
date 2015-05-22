import org.scalatest._
import org.scalatest.FlatSpec

/**
 * Created by eerilio on 5/22/15.
 */
class ExecutionModel extends FlatSpec{
  object ExecSpec  {
    var x = 0
    "Mutations are isolated" should "x equals 1 if we set it." in
       assert(x==1)
     it should "x is the default value if we don't change it" in {
       assert(x==0)
      }
  }
}
