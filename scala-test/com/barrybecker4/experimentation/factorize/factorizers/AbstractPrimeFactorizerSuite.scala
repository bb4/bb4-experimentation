package com.barrybecker4.experimentation.factorize.factorizers

import java.math.BigInteger

import org.scalatest.{BeforeAndAfter, FunSuite}


object AbstractPrimeFactorizerSuite  {
  /** Try running on a number like this to check performance. */
  val TEST_NUMBER_VERY_SMALL = new BigInteger("2098798")
  val TEST_NUMBER_SMALL = new BigInteger("2920798798768")
  val TEST_NUMBER_MEDIUM = new BigInteger("2989879798798798")
  val TEST_NUMBER_LARGE = new BigInteger("12329087979123879797")
  val TEST_NUMBER_GIANT = new BigInteger("9832148972431897213489723290879791238798797")
}

/**
  * @author Barry Becker
  */
abstract class AbstractPrimeFactorizerSuite extends FunSuite with BeforeAndAfter {

  protected var factorizer: PrimeFactorizer = _

  before {
    factorizer = createInstance()
  }

  protected def createInstance(): PrimeFactorizer

  protected def doTest(num: BigInteger): Unit = {
    println("finding factors for " + num)
    val factorizer = createInstance()
    println("The factors are : " + factorizer.findPrimeFactors(num))
  }
}
