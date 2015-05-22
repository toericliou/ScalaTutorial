package ConcurrencyExamples

import java.net.{Socket, ServerSocket}
import java.util.concurrent.{Executors, ExecutorService}
import java.util.Date
/**
 * Created by eerilio on 5/22/15.
 */

//ExecutorService generated through static methods in Executors object
//Configure an ExecutorService with policies for thread polling

class Executors {
  class NetworkService(port: Int, poolSize: Int) extends Runnable {
    val serverSocket = new ServerSocket(port)
    val pool: ExecutorService = Executors.newFixedThreadPool(poolSize) //creates a service which polls the threads

    def run() {
      try {
        while (true) {
          // This will block until a connection comes in.
          val socket = serverSocket.accept()
          pool.execute(new Handler(socket))
        }
      } finally {
        pool.shutdown()
      }
    }
  }

  class Handler(socket: Socket) extends Runnable {
    def message = (Thread.currentThread.getName() + "\n").getBytes

    def run() {
      socket.getOutputStream.write(message)
      socket.getOutputStream.close()
    }
  }

  (new NetworkService(2020, 2)).run
}
