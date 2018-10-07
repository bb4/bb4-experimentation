/** Copyright by Barry G. Becker, 2000-2018. Licensed under MIT License: http://www.opensource.org/licenses/MIT  */
package com.barrybecker4.experimentation.socket

import java.awt._
import java.awt.event._
import java.io.{BufferedReader, IOException, InputStreamReader, PrintWriter}
import java.net.{Socket, UnknownHostException}
import javax.swing._


/**
  * Socket portion of client-server program using sockets.
  * Adapted from http://java.sun.com/developer/onlineTraining/Programming/BasicJava2/socket.html
  * @author Barry Becker
  */
object ClientFrame extends App {
  private val DEFAULT_HOST = "127.0.0.1" /// "192.168.1.100";
  private val PORT = 4444
  new ClientFrame("Client Program", DEFAULT_HOST, PORT)
}

class ClientFrame(title: String, host: String, port: Int) extends JFrame with ActionListener {
  initUI()

  setTitle(title)
  addWindowListener(new WindowAdapter() {
    override def windowClosing(e: WindowEvent): Unit = {System.exit(0)}
  })
  pack()
  setVisible(true)
  val client = new Client(host, port)

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
      client.send(textField.getText)
      textField.setText("")

      println("Text received from server: [ " + client.receive() + " ]")
    }
  }
}