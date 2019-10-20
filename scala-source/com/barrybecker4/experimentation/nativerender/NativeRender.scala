/** Copyright by Barry G. Becker, 2000-2018. Licensed under MIT License: http://www.opensource.org/licenses/MIT  */
package com.barrybecker4.experimentation.nativerender

import com.barrybecker4.common.concurrency.ThreadUtil
import javax.swing.JFrame
import java.awt.Color
import java.awt.DisplayMode
import java.awt.Graphics2D
import java.awt.GraphicsDevice
import java.awt.GraphicsEnvironment
import java.util.Random


/**
  * This is pretty dangerous to run. It takes over the display.
  */
object NativeRender extends App {
  private val RANDOM = new Random(0)

  val graphicsEnvironment = GraphicsEnvironment.getLocalGraphicsEnvironment
  val graphicsDevice = graphicsEnvironment.getDefaultScreenDevice
  val displayModes = graphicsDevice.getDisplayModes
  val originalDisplayMode = graphicsDevice.getDisplayMode
  val frame = new JFrame
  frame.setUndecorated(true)
  frame.setIgnoreRepaint(true)
  if (graphicsDevice.isFullScreenSupported) {
    println("Full screen is supported.")
    graphicsDevice.setFullScreenWindow(frame)
  }
  showDisplayModes(displayModes)
  drawShapesInEachDisplayMode(graphicsDevice, displayModes, frame)
  graphicsDevice.setDisplayMode(originalDisplayMode)
  graphicsDevice.setFullScreenWindow(null)
  System.exit(0)


  private def showDisplayModes(displayModes: Array[DisplayMode]): Unit = { //int mode = RANDOM.nextInt(displayModes.magnitude);
    println("There are " + displayModes.length + " different display modes on this device.")
    var i = 0
    while (i < displayModes.length) {
      val displayMode = displayModes(i)
      println(s"$i ${displayMode.getWidth} x ${displayMode.getHeight} \t${displayMode.getRefreshRate} / ${displayMode.getBitDepth}")
      i += 1
    }
  }

  private def drawShapesInEachDisplayMode(graphicsDevice: GraphicsDevice,
                                          displayModes: Array[DisplayMode], frame: JFrame): Unit = {
    var lastWidth = -1
    for (displayMode <- displayModes) {
      if (displayMode.getWidth != lastWidth && displayMode.getWidth > 1200) {
        lastWidth = displayMode.getWidth
        if (graphicsDevice.isDisplayChangeSupported) {
          graphicsDevice.setDisplayMode(displayMode)
          drawShapes(frame)
        }
        else println("changing the display mode to " + displayMode.toString + " is not supported. :(")
      }
    }
  }

  private val RECT_WIDTH_VARIANCE = 200
  private val RECT_HEIGHT_VARIANCE = 200

  private def drawShapes(frame: JFrame): Unit = {
    frame.createBufferStrategy(2)
    val bufferStrategy = frame.getBufferStrategy
    val width = frame.getWidth
    val height = frame.getHeight
    val g = bufferStrategy.getDrawGraphics.asInstanceOf[Graphics2D]
    g.drawString(s"width= $width height= $height", 20, 20)
    var i = 0
    while (i < 2000) {
      g.setColor(new Color(RANDOM.nextInt))
      g.fillRoundRect(RANDOM.nextInt(width), RANDOM.nextInt(height),
        10 + RANDOM.nextInt(RECT_WIDTH_VARIANCE), 10 + RANDOM.nextInt(RECT_HEIGHT_VARIANCE), 20, 20)
      i += 1
    }
    bufferStrategy.show()
    ThreadUtil.sleep(2000)
    g.dispose()
  }
}
