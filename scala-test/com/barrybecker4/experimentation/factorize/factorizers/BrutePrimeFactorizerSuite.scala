package com.barrybecker4.experimentation.factorize.factorizers

import AbstractPrimeFactorizerSuite._


class BrutePrimeFactorizerSuite extends AbstractPrimeFactorizerSuite {

  def createInstance() = new BrutePrimeFactorizer

  test("Brute Factorizer") {
    doTest(TEST_NUMBER_VERY_SMALL)
    doTest(TEST_NUMBER_SMALL)
  }
}
