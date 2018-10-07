package com.barrybecker4.experimentation.primes

import com.barrybecker4.ui.components.Appendable
import javax.swing.SwingWorker
import java.util


/**
  * Finds a big set of prime numbers in a separate worker thread so the UI does not freeze.
  * The UI is updated periodically using the publish method.
  * @author Barry Becker
  */
class PrimeNumbersTask private[primes](var textArea: Appendable, var numbersToFind: Int) extends SwingWorker[Int, Int] {
  private var generator = new PrimeNumberGenerator
  private var numFoundSoFar = 0

  override def doInBackground(): Int = {
    while (numFoundSoFar < numbersToFind && !isCancelled) {
      val number = generator.getNextPrimeNumber
      numFoundSoFar += 1
      publish(number.toInt)
      setProgress(100 * numFoundSoFar / numbersToFind)
    }
    numFoundSoFar
  }

  override protected def process(chunks: util.List[Int]): Unit = {
    import scala.collection.JavaConversions._
    textArea.append(chunks.toSeq.mkString("\n"))
  }
}


