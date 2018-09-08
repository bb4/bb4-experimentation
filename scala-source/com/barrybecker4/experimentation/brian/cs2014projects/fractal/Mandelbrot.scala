package com.barrybecker4.experimentation.brian.cs2014projects.fractal

import javax.swing.JFrame
import java.awt.Graphics
import java.awt.image.BufferedImage
import Mandelbrot._

/**
  * @author Period 5
  */
object Mandelbrot {

  final private val MAX_ITER = 570
  final private val ZOOM = 150

  def main(args: Array[String]): Unit = {
    new Mandelbrot().setVisible(true)
  }
}

class Mandelbrot extends JFrame("Mandelbrot Set") {
  setBounds(100, 100, 800, 600)
  setResizable(false)
  setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE)
  private val I = new BufferedImage(getWidth, getHeight, BufferedImage.TYPE_INT_RGB)

  private var zx = .0
  private var zy = .0
  private var cX = .0
  private var cY = .0
  private var tmp = .0

  for (y <- 0 until getHeight) {
    var x = 0
    for (x <- 0 until getWidth) {
      zx = 0; zy = 0
      cX = (x - 400.0) / ZOOM
      cY = (y - 300.0) / ZOOM
      var iter = MAX_ITER
      while (zx * zx + zy * zy < 4.0 && iter > 0) {
        tmp = zx * zx - zy * zy + cX
        zy = 2.0 * zx * zy + cY
        zx = tmp
        iter -= 1
      }
      I.setRGB(x, y, iter | (iter << 8))
    }
  }

  override def paint(g: Graphics): Unit = {
    g.drawImage(I, 0, 0, this)
  }
}