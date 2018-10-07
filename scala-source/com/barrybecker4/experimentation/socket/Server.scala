/** Copyright by Barry G. Becker, 2000-2011. Licensed under MIT License: http://www.opensource.org/licenses/MIT  */
package com.barrybecker4.experimentation.socket

import com.barrybecker4.ui.components.ScrollingTextArea
import javax.swing._
import java.awt._
import java.awt.event.WindowAdapter
import java.awt.event.WindowEvent
import java.io.IOException
import java.net.ServerSocket
import com.barrybecker4.experimentation.socket.Server.PORT


/**
  * Multi threaded server for client-server application.
  * Adapted from http://java.sun.com/developer/onlineTraining/Programming/BasicJava2/socket.html
  * @author Barry Becker
  */
object Server {
  val PORT = 4444

  def main(args: Array[String]): Unit = {
    new Server
  }

}

class Server() extends JFrame {
  initUI()
  setTitle("Server Program")
  addWindowListener(new WindowAdapter() {
    override def windowClosing(e: WindowEvent): Unit = {System.exit(0)}
  })
  pack()
  setVisible(true)
  listenSocket()

  private var textArea: ScrollingTextArea = _
  private var server: ServerSocket = _

  private def initUI(): Unit = {
    val panel = new JPanel
    val label = new JLabel("Text received over socket:")
    textArea = new ScrollingTextArea(20, 40)
    panel.setLayout(new BorderLayout)
    panel.setBackground(Color.white)
    panel.add("North", label)
    panel.add("Center", textArea)
    getContentPane.add(panel)
  }

  def listenSocket(): Unit = {
    try {
      println("PORT = " + PORT)
      server = new ServerSocket(PORT)
    } catch {
      case e: IOException =>
        println("Could not listen on port " + PORT)
        e.printStackTrace()
        System.exit(-1)
    }
    while (true) {
      var w: ClientWorker = null
      try {
        w = new ClientWorker(server.accept, textArea)
        // should use executor framework here.
        val t = new Thread(w)
        t.start()
      } catch {
        case e: IOException =>
          throw new IllegalStateException("Accept failed: " + PORT)
      }
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
        println("Could not close socket")
        e.printStackTrace()
        System.exit(-1)
      case t: Throwable =>
        t.printStackTrace()
    }
  }
}