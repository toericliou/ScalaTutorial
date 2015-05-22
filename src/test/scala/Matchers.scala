import org.scalatest._
/**
 * Created by eerilio on 5/22/15.
 */
class Matchers extends FlatSpec with Matchers {
  //When we have data, we want to make sure its correct; which we use matchers for
  //refrence equality, value equality
  1 mustEqual 1
  "a" mustEqual "a"

  //elements in a Sequence
  val numbers = List(1, 2, 3)
  numbers must contain(1)

  numbers must not contain(4)

  numbers must containAll(List(1, 2, 3))

  numbers must containInOrder(List(1, 2, 3))

  List(1, List(2, 3, List(4)), 5) must haveTheSameElementsAs(List(5, List(List(4), 2, 3), 1))

  //items in a map
  val map = Map("one" -> 1, "two" -> 2)
  map must haveKey("one")
  map must notHaveKey("one")

  map must haveValue(2)
  map must notHaveValue(2)

  //numbers
  a must beGreaterThan(b)
  a must beGreaterThanOrEqualTo(b)

  a must beLessThan(b)
  a must beLessThanOrEqualTo(b)

  a must beCloseTo(b, delta)

  //options
  a must beNone

  a must beSome[Type]

  a must beSomething

  a must beSome(value)

  //throwA
  a must throwA[WhateverException] //shorter than try catch
  a must throwA(WhateverException("message")) //specific message

  //Writing own Matchers
  //as a val
  "A matcher" should {
    "be created as a val" in
    {
      //creating custome Matcher
      //returns a tuple containing whether the expectation is true
      val beEven = new Matcher[Int]
      {
        def apply(n: => Int) = {(n % 2 == 0, "%d is even".format(n), "%d is odd".format(n))}
      }
      2 must beEven
    }
  }

}
