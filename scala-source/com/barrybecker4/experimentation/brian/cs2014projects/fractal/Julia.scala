package com.barrybecker4.experimentation.brian.cs2014projects.fractal

import java.applet._
import java.awt._

import javax.swing.JFrame


class Julia extends JFrame {
    setLayout(new BorderLayout)
    val canvas = new LogisticJuliaPlot
    add("Center", canvas)
}
