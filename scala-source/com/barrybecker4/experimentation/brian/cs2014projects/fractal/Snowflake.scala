package com.barrybecker4.experimentation.brian.cs2014projects.fractal

import javax.swing.{JApplet, JFrame, JOptionPane}
import java.awt.Graphics


/**
  * @author Period 5
  */
class Snowflake extends JFrame {

  private val levelStr = JOptionPane.showInputDialog("Enter the depth of recursion")
  private[fractal] var level = levelStr.toInt


  override def paint(g: Graphics): Unit = {
    drawSnow(g, level, 20, 280, 280, 280)
    drawSnow(g, level, 280, 280, 150, 20)
    drawSnow(g, level, 150, 20, 20, 280)
  }

  private def drawSnow(g: Graphics, lev: Int, x1: Int, y1: Int, x5: Int, y5: Int): Unit = {
    var deltaX = 0
    var deltaY = 0
    var x2 = 0
    var y2 = 0
    var x3 = 0
    var y3 = 0
    var x4 = 0
    var y4 = 0
    if (lev == 0) g.drawLine(x1, y1, x5, y5)
    else {
      deltaX = x5 - x1
      deltaY = y5 - y1
      x2 = x1 + deltaX / 3
      y2 = y1 + deltaY / 3
      x3 = (0.5 * (x1 + x5) + Math.sqrt(3) * (y1 - y5) / 6).toInt
      y3 = (0.5 * (y1 + y5) + Math.sqrt(3) * (x5 - x1) / 6).toInt
      x4 = x1 + 2 * deltaX / 3
      y4 = y1 + 2 * deltaY / 3
      drawSnow(g, lev - 1, x1, y1, x2, y2)
      drawSnow(g, lev - 1, x2, y2, x3, y3)
      drawSnow(g, lev - 1, x3, y3, x4, y4)
      drawSnow(g, lev - 1, x4, y4, x5, y5)
    }
  }
}
