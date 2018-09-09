// Copyright by Barry G. Becker, 2005-2018. Licensed under MIT License: http://www.opensource.org/licenses/MIT
package com.barrybecker4.experimentation.colormixer

import com.barrybecker4.ui.application.ApplicationApplet
import com.barrybecker4.ui.components.ColorInputPanel
import com.barrybecker4.ui.util.GUIUtil
import javax.swing._
import javax.swing.event.ChangeEvent
import javax.swing.event.ChangeListener
import java.awt._
import java.awt.event.ActionEvent
import java.awt.event.ActionListener
import java.util
import ColorMixer.SLIDER_TICKS


object ColorMixer {
  private val SLIDER_TICKS = 1000

  private def createColorButton(initialColor: Color) = {
    val colorButton = new JButton("   ")
    colorButton.setBackground(initialColor)
    //colorButton.addActionListener(this);
    colorButton
  }

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
  private var colorButtonA: JButton = _
  private var colorButtonB: JButton = _
  private val colorA = Color.WHITE
  private val colorB = Color.BLACK
  private var opacitySlider: JSlider = _
  private var mixedColorsPanel: MixedColorsScrollPane = _

  override def createMainPanel: JPanel = {
    mixedColorsPanel = new MixedColorsScrollPane(colorA, colorB)
    mixedColorsPanel.setBorder(BorderFactory.createEtchedBorder)
    colorButtonA = ColorMixer.createColorButton(colorA)
    colorButtonB = ColorMixer.createColorButton(colorB)
    opacitySlider = createOpacitySlider
    val colorPanelA = new ColorInputPanel("Select first color : ",
      "Select the first color to mix", colorButtonA, this)
    val colorPanelB = new ColorInputPanel("Select second color : ",
      "Select the second color to mix", colorButtonB, this)
    val mainPanel = new JPanel
    mainPanel.setLayout(new BorderLayout)
    val controlsPanel = new JPanel
    controlsPanel.setLayout(new BoxLayout(controlsPanel, BoxLayout.X_AXIS))
    controlsPanel.add(colorPanelA)
    controlsPanel.add(Box.createHorizontalStrut(15))
    controlsPanel.add(colorPanelB)
    controlsPanel.add(Box.createHorizontalStrut(15))
    controlsPanel.add(new JLabel("Opacity"))
    controlsPanel.add(opacitySlider)
    controlsPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10))
    mainPanel.add(controlsPanel, BorderLayout.NORTH)
    mainPanel.add(mixedColorsPanel, BorderLayout.CENTER)
    mainPanel
  }

  private def createOpacitySlider = {
    val initialSliderValue = (MixPanel.INITIAL_OPACITY * SLIDER_TICKS).toInt
    val opacitySlider = new JSlider(SwingConstants.HORIZONTAL, 0, SLIDER_TICKS, initialSliderValue)
    opacitySlider.setMajorTickSpacing(SLIDER_TICKS)
    opacitySlider.setMinorTickSpacing(SLIDER_TICKS)
    opacitySlider.setToolTipText("Control the opacity of the overlaid color")
    val dict = new util.Hashtable[Integer, JLabel]
    dict.put(0, new JLabel("0"))
    dict.put(ColorMixer.SLIDER_TICKS, new JLabel("1.0"))
    opacitySlider.setLabelTable(dict)
    opacitySlider.setPaintLabels(true)
    opacitySlider.addChangeListener(this)
    opacitySlider
  }

  /** Called when a button is pressed */
  override def actionPerformed(e: ActionEvent): Unit = {
    val source = e.getSource
    if ((source eq colorButtonA) || (source eq colorButtonB)) {
      System.out.println("a or b pressed")
      mixedColorsPanel.setColorsToMix(colorButtonA.getBackground, 1.0f, colorButtonB.getBackground, 1.0f)
      mixedColorsPanel.invalidate()
      resizablePanel.repaint()
    }
  }

  override def getName = "Color Mixer"

  override def stateChanged(ce: ChangeEvent): Unit = {
    val source = ce.getSource
    if (source eq opacitySlider) {
      mixedColorsPanel.setOpacity(opacitySlider.getValue.toFloat / ColorMixer.SLIDER_TICKS)
      resizablePanel.repaint()
    }
  }
}