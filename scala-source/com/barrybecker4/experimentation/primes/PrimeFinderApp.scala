package com.barrybecker4.experimentation.primes

import com.barrybecker4.ui.application.ApplicationFrame

/**
  * Find N prime numbers and display them.
  * @author Barry Becker
  */
object PrimeFinderApp:
  /** the number of prime numbers to find */
  private val N = 200000

  def main(args: Array[String]): Unit =
    new PrimeFinderApp(N)

class PrimeFinderApp(num: Int) extends ApplicationFrame("Primer Number Finder") {
  override def createUI(): Unit = {
    val pfpanel = new PrimeFinderPanel()
    setContentPane(pfpanel)
    super.createUI()
    pfpanel.startComputing(num)
  }
}
