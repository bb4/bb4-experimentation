// Copyright by Barry G. Becker, 2000-2020. Licensed under MIT License: http://www.opensource.org/licenses/MIT
package com.barrybecker4.experimentation.socket.client

import java.awt.event._
import javax.swing._


/**
  * Client portion of client-server program using sockets.
  * Adapted from http://java.sun.com/developer/onlineTraining/Programming/BasicJava2/socket.html
  * If this window is closed the entire JVM is terminated.
  * @author Barry Becker
  */
case class ClientFrame(host: String, port: Int) extends JFrame {
  initUI()

  addWindowListener(new WindowAdapter() {
    override def windowClosing(e: WindowEvent): Unit = {
      System.exit(0)
    }
  })

  pack()
  setVisible(true)

  private def initUI(): Unit = {
    setTitle("Client Program")
    val panel = ClientPanel(host, port)
    getContentPane.add(panel)
  }

}
