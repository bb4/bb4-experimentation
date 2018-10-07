/** Copyright by Barry G. Becker, 2000-2018. Licensed under MIT License: http://www.opensource.org/licenses/MIT  */
package com.barrybecker4.experimentation.socket.server

import java.io.IOException
import java.net.ServerSocket
import com.barrybecker4.ui.components.Appendable
import javax.swing._


/**
  * Multi threaded server for client-server application.
  * Adapted from http://java.sun.com/developer/onlineTraining/Programming/BasicJava2/socket.html
  * @author Barry Becker
  */
class Server(textArea: Appendable, port: Int) extends JFrame {

  listenSocket()
  private var server: ServerSocket = _

  def listenSocket(): Unit = {
    try {
      server = new ServerSocket(port)
      val w: ClientWorker = new ClientWorker(server.accept, textArea)
      new Thread(w).start() // consider using executor framework here.
    } catch {
      case e: IOException => throw new IllegalStateException("Could not listen on port " + port, e)
    }
  }

  /** Objects created in run method are finalized when
    * program terminates and thread exits.
    */
  override protected def finalize(): Unit = {
    try {
      super.finalize()
      server.close()
    } catch {
      case e: IOException =>
        throw new IllegalStateException("Could not close socket", e)
      case t: Throwable => t.printStackTrace()
    }
  }
}