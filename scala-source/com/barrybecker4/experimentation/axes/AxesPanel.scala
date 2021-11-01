/** Copyright by Barry G. Becker, 2000-2018. Licensed under MIT License: http://www.opensource.org/licenses/MIT  */
package com.barrybecker4.experimentation.axes

import com.barrybecker4.experimentation.axes.AxesPanel._
import com.barrybecker4.ui.util.ColorMap
import com.barrybecker4.ui.legend.ContinuousColorLegend
import com.barrybecker4.ui.legend.LegendSynchronizer

import javax.swing._
import java.awt._
import java.awt.event.ActionEvent
import java.awt.event.ActionListener


/**
  * Renders 2 continuous color legends one above the other.
  */
object AxesPanel {
  private val VALUES1 = Array(-30.1, -0.5, 1101.1)
  private val VALUES2 = Array(-1.1, 0.5, 1.1)
  private val CM_TRANSPARENCY = 150

  /** this colormap is used to show a spectrum of colors representing a groups health status.  */
  private val COLORS = Array(
    new Color(200, 0, 0, CM_TRANSPARENCY + 40),
    new Color(220, 220, 220, 0),
    new Color(150, 0, 250, CM_TRANSPARENCY + 40)
  )
}

class AxesPanel() extends JPanel with ActionListener {
  this.setLayout(new BorderLayout)
  private val legend1 = createColorLegend("test1", VALUES1)
  private val legend2 = createColorLegend("test2", VALUES2)

  val synchButton = new JButton("Synchronize O point")
  synchButton.setMaximumSize(new Dimension(100, 22))
  synchButton.addActionListener(this)

  val buttonContainer = new JPanel(new FlowLayout)
  buttonContainer.add(synchButton)
  this.add(buttonContainer, BorderLayout.NORTH)
  this.add(legend1, BorderLayout.CENTER)
  this.add(legend2, BorderLayout.SOUTH)

  override def actionPerformed(e: ActionEvent): Unit = {
    new LegendSynchronizer().synchronizeLegends(legend1, legend2)
    this.repaint()
  }

  private def createColorLegend(title: String, values: Array[Double]): ContinuousColorLegend = {
    val colormap = new ColorMap(values, AxesPanel.COLORS)
    new ContinuousColorLegend(title, colormap)
  }
}
