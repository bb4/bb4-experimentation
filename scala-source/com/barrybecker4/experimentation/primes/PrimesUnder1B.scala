package com.barrybecker4.experimentation.primes

/**
  * Check that the algorithm proposed by Mike Fink and Tim Swenson actually
  * works as advertised. In other words, does it accurately determine the
  * primality of integers under one billion.
  * @author Barry Becker
  */
object PrimesUnder1B:

  def main(args: Array[String]): Unit =
    var numErrors = 0
    // checked everything < 200,000,000 and between 900,000,000 and 1,000,000,000 but then I got tired of waiting.
    for i <- 1 to 100000000 do
      val isPrime = PrimeNumberUtil.isPrime(i)
      val isPrimeUnder1b = PrimeNumberUtil.isPrimeUnder1B(i)
      if isPrime != isPrimeUnder1b then
        numErrors += 1
        print("The Fink algorithm said " + i)
        if isPrime then println(" is not prime when it actually is.")
        else println(" is prime when it actually is not.")
      if i % 1000000 == 0 then println(s"checked $i numbers so far")
    println("There were " + numErrors + " errors.")
