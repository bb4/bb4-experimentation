/** Copyright by Barry G. Becker, 2000-2018. Licensed under MIT License: http://www.opensource.org/licenses/MIT  */
package com.barrybecker4.experimentation .axes

import com.barrybecker4.ui.components.ResizableAppletPanel
import com.barrybecker4.ui.util.GUIUtil
import javax.swing._
import java.awt._


object AxesSynch {

  def main(args: Array[String]): Unit = {
    val simulator = new AxesSynch
    GUIUtil.showApplet(simulator)
  }
}

class AxesSynch() extends JApplet {
  commonInit()
  private var resizablePanel: ResizableAppletPanel = _

  def commonInit(): Unit = {
    GUIUtil.setCustomLookAndFeel()
    enableEvents(AWTEvent.WINDOW_EVENT_MASK)
    setFont(new Font(GUIUtil.DEFAULT_FONT_FAMILY, Font.PLAIN, 14))
    val mainPanel = createMainPanel
    resizablePanel = new ResizableAppletPanel(mainPanel)
    this.getContentPane.add(resizablePanel)
  }

  private def createMainPanel = {
    val axesPanel = new AxesPanel
    axesPanel.setBorder(BorderFactory.createEtchedBorder)
    val mainPanel = new JPanel
    mainPanel.setLayout(new BorderLayout)
    val controlsPanel = new JPanel
    controlsPanel.setLayout(new BoxLayout(controlsPanel, BoxLayout.X_AXIS))
    val axesPanelContainer = new JPanel(new BorderLayout)
    axesPanelContainer.add(controlsPanel, BorderLayout.NORTH)
    axesPanelContainer.add(axesPanel, BorderLayout.CENTER)
    mainPanel.add(axesPanelContainer, BorderLayout.NORTH)
    mainPanel
  }

  /** This method allow javascript to resize the applet from the browser. */
  override def setSize(width: Int, height: Int): Unit =
    resizablePanel.setSize(width, height)

  override def getName = "Axes Synchronizer"
}