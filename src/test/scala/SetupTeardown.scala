import org.scalatest.{BeforeAndAfter, FlatSpec}

import scala.collection.mutable.ListBuffer

/**
 * Created by eerilio on 5/22/15.
 */
class SetupTeardown extends FlatSpec with BeforeAndAfter {

  //doBefore and doAfter are run only on leaf examples
  //Launch and stop certain conditions
  val testBuilder = new StringBuilder
  var testString = new String

  before {
    testBuilder.append("Test: ")
    testString = "Before Test"
  }

  after {
    testBuilder.clear()
    testString = ""
  }

  "MySystem" should "execute before" in{
    testBuilder.append("1")
    assert(testBuilder == "Test: 1")
    assert(testString == "Before Test")
  }
}
