package com.barrybecker4.experimentation.factorize.factorizers

import com.barrybecker4.experimentation.factorize.factorizers.AbstractPrimeFactorizerSuite._


class QuickPrimeFactorizerSuite extends AbstractPrimeFactorizerSuite {

  def createInstance() = new QuickPrimeFactorizer

  test("Brute Factorizer") {
    doTest(TEST_NUMBER_VERY_SMALL)
    doTest(TEST_NUMBER_SMALL)
    doTest(TEST_NUMBER_MEDIUM)
    doTest(TEST_NUMBER_LARGE)
  }
}
