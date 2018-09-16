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

  /** Convert the number to a string them
    * @param number the number to check to see if palindromic
    * @return true if the specified number reads the same forward s backward.
    */
  private def isPalindromic(number: Long): Boolean = {
    val numStr = FORMAT.format(number)
    val len = numStr.length
    val lend2 = len / 2
    for (i <- 0 until lend2)
      if (numStr.charAt(i) != numStr.charAt(len - i - 1)) return false
    true
  }

  /** @param n the number of digits in the two numbers to tke product of
    * @return the largest palindromic product of two n digit numbers.
    *         returns -1 if no palindrome found.
    */
  private def findLargestPalindromicProduct(n: Int) = {
    val maxNum = Math.pow(10, n).toLong - 1
    val minNum = Math.pow(10, n - 1).toLong
    var maxFound: Long = -1
    var i = maxNum
    for (i <- maxNum until minNum by -1) {
      for (j <- maxNum until minNum by -1) {
        val num = i * j
        if (isPalindromic(num)) if (num > maxFound) maxFound = num
      }
    }
    maxFound
  }

  def main(args: Array[String]): Unit = {
    val n = 3
    println("The largest palindromic number for two " + n + " digit numbers is " + findLargestPalindromicProduct(n))
  }
}
