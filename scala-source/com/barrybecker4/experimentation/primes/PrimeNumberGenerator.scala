package com.barrybecker4.experimentation.primes

/**
  * Finds prime numbers.
  * @author Barry Becker
  */
class PrimeNumberGenerator(var currentCandidate: Long = 1L) {

  /** @return the next computed prime number*/
  def getNextPrimeNumber: Long = {
    currentCandidate += 1
    if (currentCandidate == 2) return 2

    while (!PrimeNumberUtil.isPrime(currentCandidate))
      currentCandidate += 1
    currentCandidate
  }
}


