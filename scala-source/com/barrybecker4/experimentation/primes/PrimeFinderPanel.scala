package com.barrybecker4.experimentation.primes

import com.barrybecker4.common.format.FormatUtil
import com.barrybecker4.ui.components.ScrollingTextArea
import javax.swing.JPanel
import javax.swing.JProgressBar
import java.awt.BorderLayout
import java.beans.PropertyChangeEvent
import java.beans.PropertyChangeListener


/**
  * Find the specified number of prime numbers and display the results in a
  * ScrollingTextArea.  While this is computing, update progress in a {JProgressBar.
  * @author Barry Becker
  */
class PrimeFinderPanel() extends JPanel {
  private var textArea = new ScrollingTextArea(80, 160)
  private var progressBar = new JProgressBar(0, 100)

  progressBar.setStringPainted(true)
  setLayout(new BorderLayout)
  add(progressBar, BorderLayout.NORTH)
  add(textArea, BorderLayout.CENTER)

  /** Computes numPrimesToFind in a separate worker thread
    * @param numPrimesToFind number of primes to compute.
    */
  def startComputing(numPrimesToFind: Int): Unit = {
    val task = new PrimeNumbersTask(textArea, numPrimesToFind)
    task.addPropertyChangeListener((evt: PropertyChangeEvent) => {
      if ("progress" == evt.getPropertyName) {
        val progress = evt.getNewValue.asInstanceOf[Integer]
        progressBar.setValue(progress)
        progressBar.setString(progress + "% done")
      }
    })
    task.execute()
    try { // prints the  number of prime numbers that were computed
      println("Number of primes found = " + FormatUtil.formatNumber(task.get))
    } catch {
      case e: Exception => e.printStackTrace()
    }
  }
}
