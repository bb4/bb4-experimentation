// Copyright by Barry G. Becker, 2005-2018. Licensed under MIT License: http://www.opensource.org/licenses/MIT
package com.barrybecker4.experimentation.colormixer

import javax.swing._
import java.awt._
import ScrollPaneConstants._


/**
  * @author Barry Becker
  */
class MixedColorsScrollPane(val colorA: Color, val colorB: Color) extends JPanel {
  private[colormixer] var mainPanel = new JPanel()
  mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS))

  private[colormixer] var scrollPane =
    new JScrollPane(mainPanel, VERTICAL_SCROLLBAR_AS_NEEDED, HORIZONTAL_SCROLLBAR_AS_NEEDED)

  private[colormixer] var mixPanels  = Array(
    new MixPanel(colorA, 1.0f, colorB, 1.0f, AlphaComposite.DST_ATOP, "Dest atop",
      "The part of the destination lying inside of the source " +
        "is composited over the source and replaces the destination " +
        "(Porter-Duff Destination Atop Source rule)."),
    new MixPanel(colorA, 1.0f, colorB, 1.0f, AlphaComposite.DST_IN, "Dest in",
      "The part of the destination lying inside of the source replaces the destination" +
        " (Porter-Duff Destination In Source rule)."),
    new MixPanel(colorA, 1.0f, colorB, 1.0f, AlphaComposite.DST_OUT, "Dest out",
      "The part of the destination lying outside of the source replaces the destination " +
        "(Porter-Duff Destination Held Out By Source rule)."),
    new MixPanel(colorA, 1.0f, colorB, 1.0f, AlphaComposite.DST_OVER, "Dest over", "fff"),
    new MixPanel(colorA, 1.0f, colorB, 1.0f, AlphaComposite.SRC_ATOP, "Source atop", "fff"),
    new MixPanel(colorA, 1.0f, colorB, 1.0f, AlphaComposite.SRC_IN, "Source in", "fff"),
    new MixPanel(colorA, 1.0f, colorB, 1.0f, AlphaComposite.SRC_OUT, "Source out", "fff"),
    new MixPanel(colorA, 1.0f, colorB, 1.0f, AlphaComposite.SRC_OVER, "Source over", "fff"),
    new MixPanel(colorA, 1.0f, colorB, 1.0f, AlphaComposite.CLEAR, "Clear", "fff"),
    new MixPanel(colorA, 1.0f, colorB, 1.0f, AlphaComposite.XOR, "XOR", "fff")
  )

  mixPanels.foreach(p => {
    p.setPreferredSize(new Dimension(200, 60))
    mainPanel.add(p)
  })
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
