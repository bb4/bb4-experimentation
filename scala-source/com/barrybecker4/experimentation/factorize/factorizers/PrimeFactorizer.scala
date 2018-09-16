package com.barrybecker4.experimentation.factorize.factorizers

import java.math.BigInteger


/**
  * Finds the prime factors of a positive integer with arbitrarily large precision.
  * @author Barry Becker
  */
object PrimeFactorizer {
  private[factorizers] val ONE = new BigInteger("1")
  private[factorizers] val TWO = new BigInteger("2")
}

trait PrimeFactorizer {

  /** Finds all the prime factors in order.
    * @param num number to find prime factors of.
    * @return the prime factors
    */
  def findPrimeFactors(num: BigInteger): List[BigInteger]
}
