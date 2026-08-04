// Copyright by Barry G. Becker, 2000-2020. Licensed under MIT License: http://www.opensource.org/licenses/MIT
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
class Server(textArea: Appendable, port: Int) extends JFrame:

  // Field must be declared before listenSocket() so the assignment is not overwritten by the initializer.
  private var server: ServerSocket = scala.compiletime.uninitialized
  listenSocket()

  def listenSocket(): Unit =
    try
      server = new ServerSocket(port)
    catch
      case e: IOException => throw new IllegalStateException("Could not listen on port " + port, e)
    val w = new ClientWorker(server.accept, textArea)
    new Thread(w).start() // consider using executor framework here.
