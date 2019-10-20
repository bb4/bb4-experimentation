/** Copyright by Barry G. Becker, 2000-2018. Licensed under MIT License: http://www.opensource.org/licenses/MIT  */
package com.barrybecker4.experimentation .axes

import com.barrybecker4.ui.components.ResizableAppletPanel
import com.barrybecker4.ui.util.GUIUtil
import javax.swing._
import java.awt._


object AxesSynch extends App {
  val simulator = new AxesSynch
  simulator.setPreferredSize(new Dimension(600, 400));
  simulator.pack()
  simulator.setVisible(true)
}

class AxesSynch() extends JFrame {
  commonInit()

  def commonInit(): Unit = {
    enableEvents(AWTEvent.WINDOW_EVENT_MASK)
    setFont(new Font(GUIUtil.DEFAULT_FONT_FAMILY, Font.PLAIN, 14))
    val mainPanel = createMainPanel
    this.getContentPane.add(mainPanel)
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

  override def getName = "Axes Synchronizer"
}
