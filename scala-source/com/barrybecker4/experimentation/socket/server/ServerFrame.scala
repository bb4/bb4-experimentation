// Copyright by Barry G. Becker, 2000-2020. Licensed under MIT License: http://www.opensource.org/licenses/MIT
package com.barrybecker4.experimentation.socket.server

import java.awt._
import java.awt.event.{WindowAdapter, WindowEvent}
import com.barrybecker4.ui.components.ScrollingTextArea
import javax.swing._

/**
  * UI for Server. This must start before the client or you will get an error on the client.
  * @author Barry Becker
  */
case class ServerFrame(port: Int) extends JFrame {
  initUI()
  setTitle("Server Program")

  addWindowListener(new WindowAdapter() {
    override def windowClosing(e: WindowEvent): Unit = {
      System.exit(0)
    }
  })

  setLocation(100, 120)
  pack()
  setVisible(true)

  private var textArea: ScrollingTextArea = _
  val server = new Server(textArea, port)

  private def initUI(): Unit = {
    val panel = new JPanel
    val label = new JLabel("Text received from client over socket:")
    textArea = new ScrollingTextArea(20, 40)
    panel.setLayout(new BorderLayout)
    panel.setBackground(Color.white)
    panel.add("North", label)
    panel.add("Center", textArea)
    getContentPane.add(panel)
  }
}