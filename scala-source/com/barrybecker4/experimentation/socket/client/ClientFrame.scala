/** Copyright by Barry G. Becker, 2000-2018. Licensed under MIT License: http://www.opensource.org/licenses/MIT  */
package com.barrybecker4.experimentation.socket.client

import java.awt._
import java.awt.event._
import javax.swing._


/**
  * Client portion of client-server program using sockets.
  * Adapted from http://java.sun.com/developer/onlineTraining/Programming/BasicJava2/socket.html
  * @author Barry Becker
  */
class ClientFrame(title: String, host: String, port: Int) extends JFrame with ActionListener with KeyListener {
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
    textField.addKeyListener(this)
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

  /** Send text data from UI field over socket */
  private def transmit(): Unit = {
    client.send(textField.getText)
    textField.setText("")
    println("Text received from server: [ " + client.receive() + " ]")
  }

  /** @param event button click to transmit text.*/
  override def actionPerformed(event: ActionEvent): Unit = {
    val source = event.getSource
    if (source eq button) transmit()
  }

  /** pressing the enter key will be the same as clickin the send button */
  override def keyPressed (e: KeyEvent): Unit =
    if (e.getKeyCode == 10 || e.getKeyCode == 13) transmit()


  override def keyTyped(e: KeyEvent): Unit = {}
  override def keyReleased(e: KeyEvent): Unit = {}
}