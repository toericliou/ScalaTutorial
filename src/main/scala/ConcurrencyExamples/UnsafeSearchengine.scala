package ConcurrencyExamples
import scala.collection.mutable

/**
 * Created by eerilio on 5/22/15.
 */

//Create an unsafe engine
class UnsafeSearchengine {

  case class User(name:String, id:Int)


  //userMap is not guaranteed to be safe since multiple clients could modify
  class InvertedIndex(val userMap: mutable.Map[String, User]){
    def this() = this(new mutable.HashMap[String,User]())

    def tokenizeName(name: String): Seq[String] = {
      name.split(" ").map(_.toLowerCase)
    }


    def add(term: String, user: User) {
      userMap += term -> user
    }

    //To make it safe, make map synchronized
    /*
    def add(user: User) {
      tokenizeName(user.name).foreach { term =>
        add(term, user)
    }*/

    //This is very rough, to make it more secure we can do more work outside the mutex

    /*
    def add(user: User) {
      userMap.synchronized {
        tokenizeName(user.name).foreach { term =>
          add(term, user)
        }
      }
    }*/

    def add(user: User) {
      val tokens = tokenizeName(user.name)
      tokens.foreach{ term =>
        userMap.synchronized{
          add(term,user)
        }
      }
    }


  }

}
