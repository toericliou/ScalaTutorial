package ConcurrencyExamples

import java.util.concurrent.atomic.AtomicReference

/**
 * Created by eerilio on 5/22/15.
 */


class ThreadSafety {
  //This class is not safe since if multiple threads call set, no idea what name will be
  class Person(var name: String) {
    def set(changedName: String) {
      name = changedName
    }
  }

  //Tools
  //Synchronization
  //Mutexes provide ownership, if you enter a mutex, you own it.
  class Person2(var name:String){
    def set(changedName:String){
      this.synchronized{
        name = changedName
      }
    }
  }

  //Volatile
  //Volatile is identical to synchronized except nulls are allowed
  //synchronized allows for more fine-grained locking while volatile synchronizes on every access

  class Person3(@volatile var name:String){
    def set(changedName: String) {
      name = changedName
    }
  }

  //AtomicReference
  //low-level concurrency primitive
  //most costly since method dispatch to access values
  class Person4(val name: AtomicReference[String]) {
    def set(changedName: String) {
      name.set(changedName)
    }
  }


}
