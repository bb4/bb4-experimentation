// Copyright by Barry G. Becker, 2011 - 2018. Licensed under MIT License: http://www.opensource.org/licenses/MIT
package com.barrybecker4.experimentation.jfcbook

import javax.swing._
import java.awt._


object ColoredPanel {
  private val SIZE = 50
  private val HALF_SIZE = SIZE / 2
  private val DEFAULT_COLOR = Color.RED
}

/**
  * A panel with a solid colored square in the middle of the background.
  * @author Barry Becker
  */
class ColoredPanel extends JPanel {
  private var squareColor = ColoredPanel.DEFAULT_COLOR

  override def paintComponent(g: Graphics): Unit = {
    super.paintComponent(g)
    val size: Dimension = getSize()
    g.setColor(Color.black)
    g.drawRect(0, 0, size.width - 1, size.height - 1)
    g.setColor(squareColor)
    val x = size.width / 2 - ColoredPanel.HALF_SIZE
    val y = size.height / 2 - ColoredPanel.HALF_SIZE
    g.fillRect(x, y, ColoredPanel.SIZE, ColoredPanel.SIZE)
  }

  def setSquareColor(color: Color): Unit = {
    this.squareColor = color
  }

  override def getPreferredSize = new Dimension(130, 130)
}
