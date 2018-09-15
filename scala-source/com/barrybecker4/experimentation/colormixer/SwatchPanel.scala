// Copyright by Barry G. Becker, 2005-2018. Licensed under MIT License: http://www.opensource.org/licenses/MIT
package com.barrybecker4.experimentation.colormixer

import java.awt._
import javax.swing._
import SwatchPanel._


object SwatchPanel {
  val INITIAL_OPACITY_A = 1.0f
  val INITIAL_OPACITY_B = 0.5f

  private def getColorWithOp(c: Color, op: Float): Color =
    new Color(c.getRed, c.getGreen, c.getBlue, (255 *op).toInt)
}

/**
  * Shows the mixed color swatches inside the MixPanel
  * @author Barry Becker
  */
class SwatchPanel(var colorA: Color, var colorB: Color, var rule: Int) extends JPanel {

  private var opacityA: Float = _
  private var opacityB: Float = _
  setColors(colorA, INITIAL_OPACITY_A, colorB, INITIAL_OPACITY_B)

  def setColors(colorA: Color, opacityA: Float, colorB: Color, opacityB: Float): Unit = {
    this.colorA = colorA
    this.colorB = colorB
    this.opacityA = opacityA
    this.opacityB = opacityB
    this.setDoubleBuffered(false)
    this.repaint()
  }

  def setOpacityA(op: Float): Unit =
    opacityA = op

  def setOpacityB(op: Float): Unit =
    opacityB = op

  override def paint(g: Graphics): Unit = paintComponent(g)

  override protected def paintComponent(g: Graphics): Unit = {
    super.paintComponents(g)
    val g2 = g.asInstanceOf[Graphics2D]

    drawBackground(g2)
    drawColorSwatches(g2, getWidth - 50, getHeight / 2 - 10)
  }

  private def drawBackground(g2: Graphics2D): Unit = {
    g2.setColor(this.getBackground)
    g2.fillRect(1, 1, getWidth, getHeight)
  }

  private def drawColorSwatches(g2: Graphics2D, width: Int, height: Int): Unit = {

    g2.setColor(getColorWithOp(colorA, opacityA))
    g2.fillRect(5, 10, width, height)

    g2.setComposite(AlphaComposite.getInstance(rule, opacityB))

    g2.setColor(getColorWithOp(colorB, opacityB))
    g2.fillRect(35, 20, width, height)
  }
}