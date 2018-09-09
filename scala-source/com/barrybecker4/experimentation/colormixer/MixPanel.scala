// Copyright by Barry G. Becker, 2005-2018. Licensed under MIT License: http://www.opensource.org/licenses/MIT
package com.barrybecker4.experimentation.colormixer

import com.barrybecker4.ui.util.GUIUtil
import javax.swing._
import java.awt._
import MixPanel._


/**
  * @author Barry Becker
  */
object MixPanel {
  val INITIAL_OPACITY = 0.5f
  private val FONT = new Font(GUIUtil.DEFAULT_FONT_FAMILY, Font.BOLD, 16)
}

class MixPanel(var colorA: Color, var opacityA: Float, var colorB: Color, var opacityB: Float,
               var rule: Int, var label: String, val tip: String) extends JPanel {

  setColors(colorA, opacityA, colorB, opacityB)
  private var opacity = INITIAL_OPACITY
  setToolTipText(tip)

  def setColors(colorA: Color, opacityA: Float, colorB: Color, opacityB: Float): Unit = {
    this.colorA = colorA
    this.colorB = colorB
    this.opacityA = opacityA
    this.opacityB = opacityB
    this.setDoubleBuffered(false)
    this.invalidate()
    this.repaint()
  }

  def setOpacity(op: Float): Unit = 
    opacity = op

  override def paint(g: Graphics): Unit = paintComponent(g)

  override protected def paintComponent(g: Graphics): Unit = {
    super.paintComponents(g)
    val g2 = g.asInstanceOf[Graphics2D]

    drawBackground(g2)
    drawColorSwatches(g2, 100, 30)
    drawLabel(g2)
  }

  private def drawBackground(g2: Graphics2D): Unit = {
    g2.setColor(this.getBackground)
    g2.fillRect(1, 1, this.getWidth, this.getHeight)
  }

  private def drawColorSwatches(g2: Graphics2D, width: Int, height: Int): Unit = {
    g2.setColor(colorA)
    g2.fillRect(10, 1, width, height)
    g2.setComposite(AlphaComposite.getInstance(rule, opacity))
    g2.setColor(colorB)
    g2.fillRect(40, 12, width, height)
  }

  private def drawLabel(g2: Graphics2D): Unit = {
    g2.setComposite(AlphaComposite.Src)
    g2.setFont(FONT)
    g2.setColor(Color.BLACK)
    g2.drawString(label, 160, 20)
  }
}