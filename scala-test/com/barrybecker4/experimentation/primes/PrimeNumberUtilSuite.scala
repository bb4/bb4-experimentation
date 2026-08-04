package com.barrybecker4.experimentation.primes

import org.scalatest.funsuite.AnyFunSuite

class PrimeNumberUtilSuite extends AnyFunSuite:

  test("isPrime for small primes and composites") {
    assert(PrimeNumberUtil.isPrime(2))
    assert(PrimeNumberUtil.isPrime(3))
    assert(PrimeNumberUtil.isPrime(97))
    assert(!PrimeNumberUtil.isPrime(4))
    assert(!PrimeNumberUtil.isPrime(91))
    assert(!PrimeNumberUtil.isPrime(100))
  }

  test("isPrimeUnder1B agrees with isPrime on sample values") {
    val samples = Seq(2L, 3L, 5L, 7L, 11L, 97L, 100L, 7919L, 7920L)
    for n <- samples do
      assert(PrimeNumberUtil.isPrime(n) == PrimeNumberUtil.isPrimeUnder1B(n), s"disagree on $n")
  }
