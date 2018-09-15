// Copyright by Barry G. Becker, 2005-2018. Licensed under MIT License: http://www.opensource.org/licenses/MIT
package com.barrybecker4.experimentation.colormixer

import com.barrybecker4.ui.application.ApplicationApplet
import com.barrybecker4.ui.util.GUIUtil
import javax.swing._
import javax.swing.event.ChangeEvent
import javax.swing.event.ChangeListener
import java.awt._
import java.awt.event.ActionEvent
import java.awt.event.ActionListener
import SwatchPanel._


object ColorMixer {

  def main(args: Array[String]): Unit = {
    val simulator = new ColorMixer
    GUIUtil.showApplet(simulator)
  }
}

/**
  * Demo to show all the different Porter/Duff rules for  colors mixing using Java2D API.
  * @author Barry Becker
  */
class ColorMixer extends ApplicationApplet with ActionListener with ChangeListener {
  private var colorSelectorA: ColorSelectorPanel = _
  private var colorSelectorB: ColorSelectorPanel = _
  private val colorA = new Color(0, 140, 255)
  private val colorB = new Color(255, 40, 60)
  private var mixedColorsPanel: MixedColorsScrollPane = _

  override def createMainPanel: JPanel = {
    mixedColorsPanel = new MixedColorsScrollPane(colorA, colorB)
    mixedColorsPanel.setBorder(BorderFactory.createEtchedBorder)
    colorSelectorA = new ColorSelectorPanel("Select first color :       ",
      "Select the first color to mix (under/source)",
      colorA, INITIAL_OPACITY_A, this, this)
    colorSelectorB = new ColorSelectorPanel("Select second color : ",
      "Select the second color to mix (over/dest)",
      colorB,INITIAL_OPACITY_B, this, this)

    val mainPanel = new JPanel
    mainPanel.setLayout(new BorderLayout)

    val controlsPanel = createControlsPanel()

    mainPanel.add(controlsPanel, BorderLayout.NORTH)
    mainPanel.add(mixedColorsPanel, BorderLayout.CENTER)
    mainPanel
  }

  private def createControlsPanel(): JPanel = {
    val controlsPanel = new JPanel
    controlsPanel.setLayout(new BoxLayout(controlsPanel, BoxLayout.Y_AXIS))
    controlsPanel.add(colorSelectorA)
    controlsPanel.add(Box.createVerticalStrut(5))
    controlsPanel.add(colorSelectorB)
    controlsPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5))
    controlsPanel
  }

  /** Called when a button is pressed */
  override def actionPerformed(e: ActionEvent): Unit = {
    val opA = colorSelectorA.getOpacity
    val opB = colorSelectorB.getOpacity
    mixedColorsPanel.setColorsToMix(colorSelectorA.getColor, opA, colorSelectorB.getColor, opB)
    resizablePanel.repaint()
  }

  override def getName = "Color Mixer"

  override def stateChanged(ce: ChangeEvent): Unit = {
      val opA = colorSelectorA.getOpacity
      val opB = colorSelectorB.getOpacity
      mixedColorsPanel.setOpacityA(opA)
      mixedColorsPanel.setOpacityB(opB)
      resizablePanel.repaint()
  }
}