package com.barrybecker4.experimentation.progressdemo

import com.barrybecker4.ui.application.ApplicationFrame


object ProgressMonitorDemo extends App {
  new ProgressMonitorDemo
}

/**
  * Demonstrates proper use of scala ProgressMonitor for long running tasks.
  */
class ProgressMonitorDemo() extends ApplicationFrame("Progress Monitor Demo") {
  override def createUI(): Unit = {
    val contentPane = new ProgressMonitorPanel
    setContentPane(contentPane)
    super.createUI()
  }
}
