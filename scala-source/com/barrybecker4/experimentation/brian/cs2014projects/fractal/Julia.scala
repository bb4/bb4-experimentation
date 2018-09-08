package com.barrybecker4.experimentation.brian.cs2014projects.fractal

import java.applet._
import java.awt._


class Julia extends Applet {
  override def init(): Unit = {
    setLayout(new BorderLayout)
    val canvas = new LogisticJuliaPlot
    add("Center", canvas)
  }
}