package com.barrybecker4.experimentation.factorize.factorizers

import java.math.BigInteger

import com.barrybecker4.experimentation.factorize.factorizers.AbstractPrimeFactorizerSuite._

class RecursivePrimeFactorizerSuite extends AbstractPrimeFactorizerSuite {

  def createInstance() = new RecursivePrimeFactorizer

  test("Recursive Factorizer") {
    doTest(TEST_NUMBER_VERY_SMALL)
    doTest(TEST_NUMBER_SMALL)
    doTest(TEST_NUMBER_MEDIUM)
    //doTest(TEST_NUMBER_LARGE);   // too slow
  }

  test("integer square root for perfect and imperfect squares") {
    val r = new RecursivePrimeFactorizer
    assert(r.findIntegerSquareRoot(new BigInteger("100")) == new BigInteger("10"))
    assert(r.findIntegerSquareRoot(new BigInteger("1000")) == new BigInteger("31"))
    assert(r.findIntegerSquareRoot(new BigInteger("10000")) == new BigInteger("100"))
    assert(r.findIntegerSquareRoot(new BigInteger("34512")) == new BigInteger("185"))
    assert(r.findIntegerSquareRoot(new BigInteger("657563449")) == new BigInteger("25643"))
  }

  test("integer square root for large numbers") {
    val r = new RecursivePrimeFactorizer
    val large = new BigInteger("3498765234231004984332198798970809812876532")
    val root = r.findIntegerSquareRoot(large)
    assert(root.multiply(root).compareTo(large) <= 0)
    assert(root.add(BigInteger.ONE).multiply(root.add(BigInteger.ONE)).compareTo(large) > 0)
  }
}
