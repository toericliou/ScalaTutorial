import org.scalatest.{ShouldMatchers, FlatSpec}
import org.scalamock.scalatest.MockFactory

/**
 * Created by eerilio on 5/22/15.
 */

object MockExampleSpec extends FlatSpec with MockFactory with ShouldMatchers{
  "MockExample" should "return a mock value" in{
    val m = mockFunction[Int,String]
    m expects (42) returning "Forty two" once //create a mock function
  }


}