// Copyright by Barry G. Becker, 2005-2018. Licensed under MIT License: http://www.opensource.org/licenses/MIT
package com.barrybecker4.experimentation.colormixer

import com.barrybecker4.ui.util.GUIUtil
import javax.swing._
import java.awt._
import MixPanel._


object MixPanel {
  private val HEIGHT = 60
  private val TITLE_FONT = new Font(GUIUtil.DEFAULT_FONT_FAMILY, Font.BOLD, 16)
}

/**
  * Shows the color mixer for a given rule along with title and description
  * @author Barry Becker
  */
class MixPanel(var colorA: Color, var colorB: Color,
               var rule: Int, var label: String, val tip: String) extends JPanel {

  private val titlePanel = new JLabel("<html>" + label + "</html>")
  titlePanel.setFont(TITLE_FONT)
  titlePanel.setVerticalAlignment(SwingConstants.TOP)

  val minDim = new Dimension(140, HEIGHT)
  val dim = new Dimension(160, HEIGHT)
  titlePanel.setMinimumSize(minDim)
  titlePanel.setPreferredSize(dim)
  titlePanel.setMaximumSize(dim)

  private val swatchPanel: SwatchPanel = new SwatchPanel(colorA, colorB, rule)
  swatchPanel.setMinimumSize(minDim)
  swatchPanel.setPreferredSize(dim)

  private val descPanel = new JLabel("<html>" + tip + "</html>")
  val descDim = new Dimension(600, HEIGHT)
  descPanel.setMinimumSize(minDim)
  descPanel.setPreferredSize(descDim)
  descPanel.setMaximumSize(descDim)
  descPanel.setVerticalAlignment(SwingConstants.TOP)

  setLayout(new BoxLayout(this, BoxLayout.X_AXIS)) //new BorderLayout())
  setToolTipText(tip)

  add(titlePanel)//, BorderLayout.WEST)
  add(swatchPanel)//, BorderLayout.CENTER)
  add(descPanel)//, BorderLayout.EAST)

  def setColors(colorA: Color, opacityA: Float, colorB: Color, opacityB: Float): Unit =
    swatchPanel.setColors(colorA, opacityA, colorB, opacityB)

  def setOpacityA(op: Float): Unit = swatchPanel.setOpacityA(op)
  def setOpacityB(op: Float): Unit = swatchPanel.setOpacityA(op)
}