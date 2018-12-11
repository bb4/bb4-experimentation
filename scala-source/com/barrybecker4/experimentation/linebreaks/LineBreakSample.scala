package com.barrybecker4.experimentation.linebreaks

import java.awt.{BorderLayout, Container, Dimension}
import java.awt.event.{WindowAdapter, WindowEvent}
import javax.swing.{JApplet, JFrame, UIManager}


object LineBreakSample extends App {
  val f = new JFrame("Line Break Sample")
  f.addWindowListener(new WindowAdapter() {
    override def windowClosing(e: WindowEvent): Unit = {
      System.exit(0)
    }
  })
  val lineBreakSample = new LineBreakSample
  lineBreakSample.buildUI(f.getContentPane)
  f.setSize(new Dimension(400, 250))
  f.setVisible(true)
}

class LineBreakSample extends JApplet {
  override def init(): Unit = {
    buildUI(getContentPane)
  }

  def buildUI(container: Container): Unit = {
    //val cn = UIManager.getSystemLookAndFeelClassName
    //UIManager.setLookAndFeel(cn)
    val lineBreakPanel = new LineBreakPanel
    container.add(lineBreakPanel, BorderLayout.CENTER)
  }
}

