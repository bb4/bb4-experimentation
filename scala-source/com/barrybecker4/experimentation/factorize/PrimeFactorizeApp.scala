package com.barrybecker4.experimentation.factorize

import java.math.BigInteger

import com.barrybecker4.common.profile.SimpleProfiler
import com.barrybecker4.common.util.Input
import com.barrybecker4.experimentation.factorize.factorizers.{BrutePrimeFactorizer, PrimeFactorizer, QuickPrimeFactorizer, RecursivePrimeFactorizer}

/**
  * Find the prime factors of a positive integer.
  * @author Barry Becker
  */
object PrimeFactorizeApp extends App {

  /** @param num number to show the prime factors for.*/
  def showPrimeFactors(num: BigInteger, factorizer: PrimeFactorizer): Unit = {
    val prof = new SimpleProfiler
    prof.start()
    println(s"The prime factors (found using ${factorizer.getClass.getSimpleName}) are ...")
    println(factorizer.findPrimeFactors(num).mkString(", "))
    prof.stop()
    prof.print()
    println()
  }

  println("\n==================================================================\n")
  // for now, just loops forever
  // Try with a number like 234908237409234691
  while (true) {
    val num = Input.getBigInteger("Enter a positive integer to find the prime factors of:")
    if (!(num.signum > 0)) println("must be positive.")
    else {
      showPrimeFactors(num, new QuickPrimeFactorizer)
      showPrimeFactors(num, new RecursivePrimeFactorizer)
      showPrimeFactors(num, new BrutePrimeFactorizer)
    }
    println()
  }
}