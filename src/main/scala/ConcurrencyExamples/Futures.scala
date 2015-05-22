package ConcurrencyExamples

import java.util.concurrent.FutureTask
import java.net.{Socket, ServerSocket}
import java.util.concurrent.{Executors, ExecutorService}
import java.util.Date
import _root_.ConcurrencyInScala.Callable

/**
 * Created by eerilio on 5/22/15.
 */

//A future is an asynchronous computation. The Future acts like a result, and is passed incomplete, until the result is needed
//wrap computation in a future, call blocking get() to get result
class Futures {
  val future = new FutureTask[String](new Callable[String]() {
    def call(): String = {
      searcher.search(target);
    }})
  executor.execute(future)

  val blockingResult = future.get() //blocks until result is returned
}
