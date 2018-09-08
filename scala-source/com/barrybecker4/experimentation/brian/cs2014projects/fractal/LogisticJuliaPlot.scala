package com.barrybecker4.experimentation.brian.cs2014projects.fractal

import java.awt.Canvas
import java.awt.Color
import java.awt.Graphics


/**
  * @author Period 5
  */
object LogisticJuliaPlot {
  private val cmap = Array(
    Color.black,
    new Color(0, 0, 168), new Color(100, 50, 0),
    new Color(0, 168, 168), new Color(168, 0, 0), new Color(168, 0, 168),
    new Color(168, 84, 0), new Color(168, 168, 168), new Color(84, 84, 84),
    new Color(84, 84, 255), new Color(84, 255, 84), new Color(84, 255, 255),
    new Color(255, 84, 84), new Color(255, 84, 255), new Color(255, 255, 84),
    Color.white
  )
}

class LogisticJuliaPlot extends Canvas {
  private val maxcol = 399
  private val maxrow = 399
  private val max_iterations = 256
  private val max_size = 4

  private def plot(g: Graphics, x: Int, y: Int, color_index: Int): Unit = {
    g.setColor(LogisticJuliaPlot.cmap(color_index))
    g.drawLine(x, y, x, y)
  }

  override def paint(g: Graphics): Unit = {
    g.setColor(Color.BLUE)
    g.drawOval(200, 200, 300, 300)
    val Q = new Array[Double](400)
    val Pmax = 1.5
    val Pmin = -.5
    val Qmax = .7
    val Qmin = -.7
    val A = 1.678
    val B = .95
    var P = .0
    var deltaP = .0
    var deltaQ = .0
    var color = 0
    deltaP = (Pmax - Pmin) / (maxcol - 1).toDouble
    deltaQ = (Qmax - Qmin) / (maxrow - 1).toDouble

    for (row <- 0 to maxrow)
      Q(row) = (Qmin + row * deltaQ).toFloat

    for (col <- 0 to maxcol) {
      P = Pmin + col * deltaP
      for (row <- 0 to maxrow) {
        var X = P
        var Y = Q(row)
        color = 0
        var Xsquare = 0.0
        var Ysquare = 0.0
        while (color <= max_iterations - 1 && (Xsquare + Ysquare) <= max_size) {
          val Xfactor = X - X * X + Y * Y
          val Yfactor = 2 * X * Y - Y
          X = A * Xfactor + B * Yfactor
          Y = B * Xfactor - A * Yfactor
          Xsquare = X * X
          Ysquare = Y * Y
          color += 1
        }
        plot(g, col, row, color % LogisticJuliaPlot.cmap.length)
      }
    }
  }
}