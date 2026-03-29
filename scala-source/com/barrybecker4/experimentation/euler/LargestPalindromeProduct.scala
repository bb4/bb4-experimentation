package com.barrybecker4.experimentation.euler

import java.text.DecimalFormat

/**
  * From project Euler:
  * A palindromic number reads the same both ways.
  * The largest palindrome made from the product of two 2-digit numbers is 9009 = 91 × 99.
  * Find the largest palindrome made from the product of two 3-digit numbers.
  * @author Barry Becker
  */
object LargestPalindromeProduct {
  private val FORMAT = new DecimalFormat("#")

  /** @return true if the specified number reads the same forward as backward */
  private def isPalindromic(number: Long): Boolean =
    val numStr = FORMAT.format(number)
    numStr == numStr.reverse

  /** @param n the number of digits in the two numbers to take product of
    * @return the largest palindromic product of two n digit numbers.
    *         returns -1 if no palindrome found.
    */
  private def findLargestPalindromicProduct(n: Int): Long =
    val maxNum = Math.pow(10, n).toLong - 1
    val minNum = Math.pow(10, n - 1).toLong
    var maxFound: Long = -1
    for i <- maxNum until minNum by -1 do
      for j <- maxNum until minNum by -1 do
        val p = i * j
        if isPalindromic(p) && p > maxFound then maxFound = p
    maxFound

  def main(args: Array[String]): Unit =
    val n = 3
    println("The largest palindromic number for two " + n + " digit numbers is " + findLargestPalindromicProduct(n))
}
