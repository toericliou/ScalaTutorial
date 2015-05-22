package ConcurrencyExamples

import java.net.{Socket, ServerSocket}
import java.util.concurrent.{Executors, ExecutorService}
import java.util.Date
/*
  Each request will return the name of the current thread: main
  only one request at a time can be handled since only one thread
 */
class NetworkService(port: Int, poolSize: Int) extends Runnable {
  val serverSocket = new ServerSocket(port)

  def run() {
    while (true) {
      // This will block until a connection comes in.
      val socket = serverSocket.accept()
      (new Thread(new Handler(socket))).start() //This creates a new thread for each request
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
