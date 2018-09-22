package com.barrybecker4.experimentation.factorize.factorizers

import java.math.BigInteger

import com.barrybecker4.experimentation.factorize.factorizers.AbstractPrimeFactorizerSuite._

class RecursivePrimeFactorizerSuite extends AbstractPrimeFactorizerSuite {

  def createInstance() = new RecursivePrimeFactorizer

  private def testSqrt(num: BigInteger): Unit = {
    println(s"The integer sqrt of $num is " +
      factorizer.asInstanceOf[RecursivePrimeFactorizer].findIntegerSquareRoot(num))
  }

  test("Recursive Factorizer") {
    doTest(TEST_NUMBER_VERY_SMALL)
    doTest(TEST_NUMBER_SMALL)
    doTest(TEST_NUMBER_MEDIUM)
    //doTest(TEST_NUMBER_LARGE);   // too slow

    println()

    testSqrt(new BigInteger("100"))
    testSqrt(new BigInteger("1000"))
    testSqrt(new BigInteger("10000"))
    testSqrt(new BigInteger("34512"))
    testSqrt(new BigInteger("34789512"))
    testSqrt(new BigInteger("657563449")) //perfect square

    testSqrt(new BigInteger("3498765234231004984332198798970809812876532"))
    testSqrt(new BigInteger(
      "349871203555129036523238494239842319874983789789767879067203421543323422398723987429238476789989821033123" +
        "387238230940909123120202923657352498768539729837422938749827331004984332192323829323784239842398872983742987" +
        "98723984729388798970809812876532"))
  }
}
