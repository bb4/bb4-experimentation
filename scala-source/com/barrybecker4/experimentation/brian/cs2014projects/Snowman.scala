package com.barrybecker4.experimentation.brian.cs2014projects

import java.awt._
import javax.swing.JFrame


class Snowman extends JFrame {

  override def paint(page: Graphics): Unit = {
    val MID = 150
    val TOP = 50
    setBackground(Color.white)
    page.setColor(Color.yellow)
    page.fillOval(120, 80, 80, 80)
    page.setColor(Color.yellow)
    page.fillOval(80, 80, 80, 80)
    page.setColor(Color.yellow)
    page.fillOval(100, 110, 80, 80)
  }
}
