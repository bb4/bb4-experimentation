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
 * Here are a few hints.
 * Maybe just look at them one at a time and see how few you need before getting the "aha" moment.
 * If you look at them all, you will likely get it. You can also look up the solution (to the first part) online as I did.
 *
 * 1. This statement is also true and may be easier to prove:
 *    "Every positive integer has a multiple who's digits are some number of 1's followed by some number of 0's".
 *
 * 2. Make a list of all the numbers consisting of N 1's where N is the positive integer.
 *
 * 3. Read about the pigeon hole principle.
 *
 * 4. Remember the hint from the puzzle:
 *    if you have two numbers with the same remainder when divided by n, their difference is a multiple of n.
 *
 * 5. For every number in your list of 1's consider what the remainders can be.
 *
 * 6. If you have N numbers consisting of all 1s, can they all have a unique remainder when divided by N?
 *
 * 7. The answer to the previous question is no.
 *
 * @author Barry Becker
 */
object OnesAndZerosPuzzler extends App {

  val LIMIT = 10000L
  val MILLION = new BigInteger("1000000")


  def getOnesNumber(numOfOnes: Int): BigInteger = {
    new BigInteger("1" * numOfOnes)
  }

  /** @param num number to show the remainders for.*/
  def showRemainders(num: BigInteger): Unit = {
    val limit: Int = Math.min(LIMIT, num.longValue()).toInt
    var ctMap: Map[BigInteger, Int] = Map()

    for (i <- 1 to limit) {
      val onesNumber = getOnesNumber(i)
      val onesNumStr = if (onesNumber.compareTo(MILLION) < 0) onesNumber.toString() else s"$i ones"
      val remainder = onesNumber.divideAndRemainder(num)(1)

      if (ctMap.contains(remainder))
        ctMap += remainder -> (ctMap(remainder) + 1)
      else
        ctMap += remainder -> 1

      println(s"$i) rem: $remainder for $onesNumStr")
    }
    println()
    val sortedKeys = ctMap.keySet.toList.sorted
    println(sortedKeys.map(key => s"$key : ${ctMap(key)}").mkString("\n"))

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