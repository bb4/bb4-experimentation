package com.barrybecker4.experimentation.factorize.factorizers

import java.math.BigInteger
import PrimeFactorizer._

/**
  * Finds the prime factors of a positive integer with arbitrarily large precision.
  * Algorithm from Lars Vogel
  * http://www.vogella.com/tutorials/JavaAlgorithmsPrimeFactorization/article.html
  * To get something really fast we should try a general number field sieve
  * http://en.wikipedia.org/wiki/General_number_field_sieve
  * @author Barry Becker
  */
class QuickPrimeFactorizer extends PrimeFactorizer {

  override def findPrimeFactors(num: BigInteger): List[BigInteger] = {
    var factors = List[BigInteger]()
    var newNum: BigInteger = new BigInteger(num.toString)
    var candidateFactor: BigInteger = TWO
    while (candidateFactor.compareTo(newNum.divide(candidateFactor)) <= 0) {
      while (newNum.mod(candidateFactor) == BigInteger.ZERO) {
        factors :+= candidateFactor
        newNum = newNum.divide(candidateFactor)
      }
      candidateFactor = candidateFactor.add(ONE)
    }
    if (newNum.compareTo(ONE) > 0) factors :+ newNum else factors
  }
}