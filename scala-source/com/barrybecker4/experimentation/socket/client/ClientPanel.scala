// Copyright by Barry G. Becker, 2000-2020. Licensed under MIT License: http://www.opensource.org/licenses/MIT
package com.barrybecker4.experimentation.socket.client

import java.awt.{BorderLayout, Color, FlowLayout}
import java.awt.event.{ActionEvent, ActionListener, KeyEvent, KeyListener}
import javax.swing.{JButton, JLabel, JPanel, JTextField}


case class ClientPanel(host: String, port: Int) extends JPanel with ActionListener with KeyListener  {

  private val client = new Client(host, port)

  private val text = new JLabel("Text to send over socket:")
  private val textField = new JTextField(40)
  textField.addKeyListener(this)
  private val button = new JButton("Send message")
  button.setToolTipText("Send data to server")
  button.addActionListener(this)
  val buttonPanel = new JPanel(new FlowLayout)
  buttonPanel.add(new JPanel)
  buttonPanel.add(button)
  buttonPanel.add(new JPanel)

  setLayout(new BorderLayout)
  setBackground(Color.white)
  add("North", text)
  add("Center", textField)
  add("South", buttonPanel)


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
