package com.barrybecker4.experimentation.puzzlers

import java.math.BigInteger
import com.barrybecker4.common.util.Input


/**
  * Find the remainders of dividing a specified number into a sequence
  * of numbers consisting of repeated ones.
  *
  * To help with this puzzler:
  * PUZZLE: Zeroes and Ones
  *
  * Show that every natural number has a non-zero multiple which, when written out (in base ten),
  * contains only zeroes and ones.
  * Even better, prove that your telephone number, if it ends with a 1, 3, 7, or 9,
  * has a  multiple whose decimal representation is all ones!
  *
  * HINT: Use the fact that if you have two numbers with the same remainder when divided by n,
  * their difference is a multiple of n.
  *
  * @author Barry Becker
  */
object OnesAndZerosPuzzler extends App {

  val MILLION = new BigInteger("1000000")

  def getOnesNumber(numOfOnes: Int): BigInteger = {
    new BigInteger("1" * numOfOnes)
  }

  /** @param num number to show the remainders for.*/
  def showRemainders(num: BigInteger): Unit = {
    val limit: Int = Math.min(1000L, num.longValue()).toInt
    for (i <- 1 to limit) {
      val onesNumber = getOnesNumber(i)
      val onesNumStr = if (onesNumber.compareTo(MILLION) < 0) onesNumber.toString() else s"$i ones"
      val remainder  = onesNumber.divideAndRemainder(num)

      println(s"$i) rem: ${remainder(1)} for $onesNumStr")
    }
  }

  println("\n==================================================================\n")
  // Given a number N, find the remainders of dividing it into the following sequence of numbers
  // 1) 1
  // 2) 11
  // 3) 111
  // ...
  // N) <N ones>
  while (true) {
    val num = Input.getBigInteger("Enter a positive integer to find remainders for:")
    if (!(num.signum > 0)) println("must be positive.")
    else {
      showRemainders(num)
    }
    println()
  }
}