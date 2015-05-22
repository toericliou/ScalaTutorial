import org.specs2._

/**
 * Created by eerilio on 5/22/15.
 */
class SetupTeardown extends mutable.Specification {

  //doBefore and doAfter are run only on leaf examples
  //Launch and stop certain conditions

  "MySystem" should {
    doBefore {..}
    "mess up the system" in {..}
    "and again" in {..}
    doAfter {..}
    }
  }
  //doFirst and doLast are used for single-time setup
  "Foo" should {
    doFirst { openTheCurtains() }
    "test stateless methods" in {...}
    "test other stateless methods" in {...}
    doLast { closeTheCurtains() }
  }
}
