package com.barrybecker4.experimentation.factorize.factorizers

import java.math.{BigDecimal, BigInteger, MathContext}
import RecursivePrimeFactorizer.TWO_DEC
import PrimeFactorizer._

/**
  * Finds the prime factors of a positive integer with arbitrarily large precision.
  * Algorithm from Lars Vogel
  * http://www.vogella.de/articles/JavaAlgorithmsPrimeFactorization/article.html
  * @author Barry Becker
  */
object RecursivePrimeFactorizer {
  private val TWO_DEC: BigDecimal = new BigDecimal(2)
}

class RecursivePrimeFactorizer extends PrimeFactorizer {

    /** Recursive method to find prime factors. */
  override def findPrimeFactors(num: BigInteger): List[BigInteger] = {
    var factors = List[BigInteger]()
    val newNum: BigInteger = new BigInteger(num.toString)
    var candidateFactor: BigInteger = findIntegerSquareRoot(newNum)
    while (!(newNum.mod(candidateFactor) == BigInteger.ZERO) && candidateFactor.compareTo(ONE) >= 0)
      candidateFactor = candidateFactor.subtract(ONE)
    if (candidateFactor == ONE)
      factors :+= newNum
    else if (newNum.mod(candidateFactor).signum == 0) {
      factors ++= findPrimeFactors(candidateFactor)
      factors ++= findPrimeFactors(newNum.divide(candidateFactor))
    }
    factors
  }

  /** Newtons method to find approximate square root.
    * See http://en.wikipedia.org/wiki/Integer_square_root
    * @param num number to square root
    * @return the largest x such that x squared is <= num
    */
  def findIntegerSquareRoot(num: BigInteger): BigInteger = {
    // throw out the last len/2 - 1 digits and use the result as our initial guess.
    val origNum: BigDecimal = new BigDecimal(num)
    val numStr: String = num.toString
    val numDigits: Int = numStr.length
    val mc: MathContext = new MathContext(5 + numDigits)
    val initialGuessStr: String = numStr.substring(0, (numDigits + 2) / 2)
    var xk: BigDecimal = new BigDecimal(initialGuessStr)
    var xkp1: BigDecimal = null
    var diff: BigDecimal = TWO_DEC
    while (diff.compareTo(BigDecimal.ONE) >= 0) {
      xkp1 = xk.add(origNum.divide(xk, mc)).divide(TWO_DEC, mc)
      diff = xkp1.subtract(xk).abs
      xk = xkp1
    }
    xkp1.toBigInteger
  }
}
