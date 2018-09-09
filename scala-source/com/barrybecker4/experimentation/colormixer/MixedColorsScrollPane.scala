// Copyright by Barry G. Becker, 2005-2018. Licensed under MIT License: http://www.opensource.org/licenses/MIT
package com.barrybecker4.experimentation.colormixer

import javax.swing._
import java.awt._
import ScrollPaneConstants._


/**
  * @author Barry Becker
  */
class MixedColorsScrollPane(val colorA: Color, val colorB: Color) extends JPanel {
  private[colormixer] var mainPanel = new JPanel
  mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS))
  private[colormixer] var scrollPane =
    new JScrollPane(mainPanel, VERTICAL_SCROLLBAR_AS_NEEDED, HORIZONTAL_SCROLLBAR_AS_NEEDED)

  private[colormixer] var mixPanels  = Array(
    new MixPanel(colorA, 1.0f, colorB, 1.0f, AlphaComposite.DST_ATOP, "Dest Atop"),
    new MixPanel(colorA, 1.0f, colorB, 1.0f, AlphaComposite.DST_IN, "Dest in"),
    new MixPanel(colorA, 1.0f, colorB, 1.0f, AlphaComposite.DST_OUT, "Dest out"),
    new MixPanel(colorA, 1.0f, colorB, 1.0f, AlphaComposite.DST_OVER, "Dest Over"),
    new MixPanel(colorA, 1.0f, colorB, 1.0f, AlphaComposite.SRC_ATOP, "Source Atop"),
    new MixPanel(colorA, 1.0f, colorB, 1.0f, AlphaComposite.SRC_IN, "Source In"),
    new MixPanel(colorA, 1.0f, colorB, 1.0f, AlphaComposite.SRC_OUT, "Source in"),
    new MixPanel(colorA, 1.0f, colorB, 1.0f, AlphaComposite.SRC_OVER, "Source over"),
    new MixPanel(colorA, 1.0f, colorB, 1.0f, AlphaComposite.CLEAR, "Clear"),
    new MixPanel(colorA, 1.0f, colorB, 1.0f, AlphaComposite.XOR, "XOR")
  )

  for (p <- mixPanels) {
    p.setPreferredSize(new Dimension(200, 60))
    mainPanel.add(p)
  }
  this.setLayout(new BorderLayout)
  this.add(scrollPane, BorderLayout.CENTER)
  mainPanel.invalidate()

  def setColorsToMix(colorA: Color, opacityA: Float, colorB: Color, opacityB: Float): Unit = {
    for (p <- mixPanels) p.setColors(colorA, opacityA, colorB, opacityB)
    mainPanel.invalidate()
  }

  def setOpacity(opacity: Float): Unit = {
    for (p <- mixPanels) p.setOpacity(opacity)
    mainPanel.invalidate()
  }
}
