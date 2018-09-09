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

    new MixPanel(colorA, colorB, AlphaComposite.DST_ATOP,
      "Destination Atop Source",
      "The part of the destination lying inside of the source " +
        "is composited over the source and replaces the destination"),

    new MixPanel(colorA,  colorB, AlphaComposite.DST_IN,
      "Destination In Source",
      "The part of the destination lying inside of the source replaces the destination"),

    new MixPanel(colorA, colorB,  AlphaComposite.DST_OUT,
      "Destination Held Out By Source",
      "The part of the destination lying outside of the source replaces the destination"),

    new MixPanel(colorA, colorB,  AlphaComposite.DST_OVER,
      "Destination Over Source",
      "The destination is composited over the source and the result replaces the destination"),

    new MixPanel(colorA,  colorB,  AlphaComposite.SRC_ATOP,
      "Source Atop Destination",
      "The part of the source lying inside of the destination is composited onto the destination"),

    new MixPanel(colorA,  colorB,  AlphaComposite.SRC_IN,
      "Source In Destination",
      "The part of the source lying inside of the destination replaces the destination"),

    new MixPanel(colorA,  colorB,  AlphaComposite.SRC_OUT,
      "Source Held Out By Destination",
      "The part of the source lying outside of the destination replaces the destination"),

    new MixPanel(colorA,  colorB, AlphaComposite.SRC_OVER,
      "Source Over Destination",
      "The source is composited over the destination"),

    new MixPanel(colorA,  colorB,  AlphaComposite.CLEAR,
      "Clear",
      "Both the color and the alpha of the destination are cleared. " +
        "Neither the source nor the destination is used as input."),

    new MixPanel(colorA,  colorB,  AlphaComposite.XOR,
      "Source XOR Destination",
      "The part of the source that lies outside of the destination is combined " +
        "with the part of the destination that lies outside of the source")
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

  def setOpacityA(opacity: Float): Unit = {
    for (p <- mixPanels) p.setOpacityA(opacity)
    mainPanel.invalidate()
  }

  def setOpacityB(opacity: Float): Unit = {
    for (p <- mixPanels) p.setOpacityB(opacity)
    mainPanel.invalidate()
  }
}
