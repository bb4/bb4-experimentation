/** Copyright by Barry G. Becker, 2000-2018. Licensed under MIT License: http://www.opensource.org/licenses/MIT  */
package com.barrybecker4.experimentation.socket

import javax.swing._
import java.awt._
import java.awt.event._
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.io.PrintWriter
import java.net.Socket
import java.net.UnknownHostException


/**
  * Socket portion of client-server program using sockets.
  * Adapted from http://java.sun.com/developer/onlineTraining/Programming/BasicJava2/socket.html
  * @author Barry Becker
  */
object Client extends App {
  private val DEFAULT_HOST = "127.0.0.1" /// "192.168.1.100";

  private def exceptionOccurred(msg: String, t: Throwable): Unit = throw new IllegalStateException(msg, t)

  val client = new Client
  client.setTitle("Client Program")
  client.addWindowListener(new WindowAdapter() {
    override def windowClosing(e: WindowEvent): Unit = {System.exit(0)}
  })
  client.pack()
  client.setVisible(true)
  client.createListenSocket()
}

class Client() extends JFrame with ActionListener {
  initUI()
  private var out: PrintWriter = _
  private var in: BufferedReader = _
  private var button: JButton = _
  private var textField: JTextField = _

  private def initUI(): Unit = {
    val text = new JLabel("Text to send over socket:")
    textField = new JTextField(40)
    button = new JButton("Send message")
    button.setToolTipText("Send data to server")
    button.addActionListener(this)
    val buttonPanel = new JPanel(new FlowLayout)
    buttonPanel.add(new JPanel)
    buttonPanel.add(button)
    buttonPanel.add(new JPanel)
    val panel: JPanel = new JPanel
    panel.setLayout(new BorderLayout)
    panel.setBackground(Color.white)
    getContentPane.add(panel)
    panel.add("North", text)
    panel.add("Center", textField)
    panel.add("South", buttonPanel)
  }

  /** @param event button click to transmit text.*/
  override def actionPerformed(event: ActionEvent): Unit = {
    val source = event.getSource
    if (source eq button) { //Send data over socket
      val text = textField.getText
      out.println(text)
      textField.setText("")
      //Receive text from server
      try {
        val line = in.readLine
        System.out.println("Text received:" + line)
      } catch {
        case e: IOException =>
          Client.exceptionOccurred("Read failed", e)
      }
    }
  }

  def createListenSocket(): Unit = {
    try {
      println("client host = " + Client.DEFAULT_HOST + ", port = "+ Server.PORT)
      val socket: Socket = new Socket(Client.DEFAULT_HOST, Server.PORT)
      out = new PrintWriter(socket.getOutputStream, true)
      in = new BufferedReader(new InputStreamReader(socket.getInputStream))
      println("create listen out_1 = " + out)
    } catch {
      case e: UnknownHostException =>
        Client.exceptionOccurred("Unknown host: " + Client.DEFAULT_HOST, e)
      case e: IOException =>
        Client.exceptionOccurred("No I/O", e)
    }
    assert(out != null, "Failed to create socket")
  }
}
