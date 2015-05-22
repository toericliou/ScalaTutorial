import org.scalatest._

/**
 * Created by eerilio on 5/22/15.
 */

//Arithmetic is the SystemUnderTest
//add is a context
//add two numbers and add three numbers are examples
//mustEqual indicates an expectation
//1 mustEqual 1 is common expectation
class ArithmeticSpec extends FlatSpec{

  "Arithmetic" should "add two numbers" in {
    assert(1+1==2)
  }

  it should "add three numbers" in {
    assert(1+1+1==3)
  }
}
