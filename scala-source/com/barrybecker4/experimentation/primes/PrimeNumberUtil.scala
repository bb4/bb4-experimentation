// Copyright by Barry G. Becker, 2014 - 2018. Licensed under MIT License: http://www.opensource.org/licenses/MIT
package com.barrybecker4.experimentation.primes

/**
  * Some static methods for dealing with prime numbers.
  * The isPrimeUnder1B method is from an article by Mike Fink in QL Hackers Journal.
  */
object PrimeNumberUtil {
  private val LOW_PRIMES = Array[Long](
    7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 49, 53, 59, 61, 67, 71, 73, 77, 79, 83, 89, 91
  )

  /**
    * @param num the number to check primality of
    * @return true of num is prime, else false.
    */
  def isPrime(num: Long): Boolean = {
    if (num == 2) return true
    if (num % 2 == 0) return false
    var i = 3
    while (i <= Math.sqrt(num)) {
      if (num % i == 0) return false
      i += 2
    }
    true
  }

  /** Prime Numbers - Mike Fink
    * This program ascertains the primality of any
    * number less than a billion.
    * (C) 1988 by Mike Fink with help from Robert Fink
    * @param num must be under 1 billion, or all bets are off.
    * @return true if under 1 billion and prime.
    */
  def isPrimeUnder1B(num: Long): Boolean = {
    if (num == 2 || num == 3 || num == 5) return true
    if (num % 2 == 0 || num % 3 == 0 || num % 5 == 0) return false
    val sqrt = Math.sqrt(num).toLong
    var c = 0
    while (true) {
      var index = 0
      while (index < 24) {
        val v1 = LOW_PRIMES(index) + 90 * c
        if (v1 > sqrt) return true
        if (num % v1 == 0) return false
          index += 1
      }
      c += 1
    }
    false // should not get here
  }
}