package com.barrybecker4.experimentation.brian.simpleui

import com.barrybecker4.ui.components.ScrollingTextArea
import javax.swing._
import java.awt._
import java.awt.event.ActionEvent
import java.awt.event.ActionListener


object SimpleFrame:

  /** Entry point for program */
  def main(args: Array[String]): Unit = new SimpleFrame

class SimpleFrame() extends JFrame("Simple Frame") with ActionListener:
  /** Used to show some text after a button is clicked */
  private[simpleui] val taskOutput = new ScrollingTextArea(5, 20)

  setContentPane(createContent)
  pack()
  setVisible(true)

  private def createContent =
    val startButton = new JButton("Start")
    startButton.addActionListener(this)
    val contentPanel = new JPanel
    contentPanel.setLayout(new BorderLayout)
    contentPanel.add(startButton, BorderLayout.NORTH)
    contentPanel.add(new JScrollPane(taskOutput), BorderLayout.CENTER)
    contentPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20))
    contentPanel

  /** Called when the user presses the start button. */
  override def actionPerformed(evt: ActionEvent): Unit =
    taskOutput.setText("The button was clicked, Yada yada yada yada yada ...")
