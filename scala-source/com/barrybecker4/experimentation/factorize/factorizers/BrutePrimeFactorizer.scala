package com.barrybecker4.experimentation.factorize.factorizers

import java.math.BigInteger
import PrimeFactorizer._

/**
  * Finds the prime factors of a positive integer with arbitrarily large precision.
  * @author Barry Becker
  */
class BrutePrimeFactorizer extends PrimeFactorizer {

  override def findPrimeFactors(num: BigInteger): List[BigInteger] = {
    var factors = List[BigInteger]()
    var newNum = new BigInteger(num.toString)
    var candidateFactor = TWO
    while (candidateFactor.compareTo(newNum) <= 0 && newNum.compareTo(ONE) > 0) {
      if (newNum.mod(candidateFactor) == BigInteger.ZERO) {
        factors :+= candidateFactor
        newNum = newNum.divide(candidateFactor)
      }
      else candidateFactor = candidateFactor.add(ONE)
    }
    factors
  }
}
