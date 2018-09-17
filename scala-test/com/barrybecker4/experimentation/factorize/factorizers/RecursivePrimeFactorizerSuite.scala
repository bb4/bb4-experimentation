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
    testSqrt(new BigInteger("34987120355512903652323849423984231987498378978976787906720342154332342239872398742923847678998982103312338723823094090912312020292365735249876853972983742293874982733100498433219232382932378423984239887298374298798723984729388798970809812876532"))

  }
}
