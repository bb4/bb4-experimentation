/** Copyright by Barry G. Becker, 2000-2011. Licensed under MIT License: http://www.opensource.org/licenses/MIT  */
package com.barrybecker4.experimentation.jfcbook

import com.barrybecker4.ui.components.ResizableAppletPanel
import com.barrybecker4.ui.components.TexturedPanel
import com.barrybecker4.ui.util.GUIUtil
import javax.swing._
import java.awt._


object OpaqueTest {
  def main(args: Array[String]): Unit = {
    val frame = new OpaqueTest
    val contentPane = frame.getContentPane
    val resizablePanel = createResizableAppletPanel
    contentPane.add(resizablePanel, BorderLayout.CENTER)
    frame.setSize(400, 400)
    frame.setVisible(true)
  }

  private def createResizableAppletPanel = {
    val rainPanel = new TexturedPanel(GUIUtil.getIcon("com/barrybecker4/experimentation/jfcbook/rain.gif"))
    val resizablePanel = new ResizableAppletPanel(rainPanel)
    val opaque = new ColoredPanel
    // JComponents are opaque by default
    val transparent = new ColoredPanel
    transparent.setOpaque(false)
    val alpha = new ColoredPanel
    alpha.setOpaque(false)
    alpha.setSquareColor(new Color(255, 34, 34, 134))
    rainPanel.add(opaque)
    rainPanel.add(transparent)
    rainPanel.add(alpha)
    rainPanel.add(createLabel)
    resizablePanel
  }

  private def createLabel = {
    val textLabel = new JLabel("test")
    val plain = new JPanel
    plain.setPreferredSize(new Dimension(100, 100))
    plain.add(textLabel)
    textLabel
  }
}

class OpaqueTest extends JFrame {}