package com.barrybecker4.experimentation.euler

import com.barrybecker4.common.format.FormatUtil.formatNumber


/**
  * From project Euler:
  * The following iterative sequence is defined for the set of positive integers:
  * <p>
  * n → n/2 (n is even)
  * n → 3n + 1 (n is odd)
  * </p>
  * Using the rule above and starting with 13, we generate the following sequence:
  *
  * 13 → 40 → 20 → 10 → 5 → 16 → 8 → 4 → 2 → 1
  *
  * It can be seen that this sequence (starting at 13 and finishing at 1) contains 10 terms.
  * Although it has not been proved yet (Collatz Problem), it is thought that all starting numbers finish at 1.
  * Which starting number, under one million, produces the longest chain?
  * @author Barry Becker
  */
object LargestCollatzSequenceFinder {

  def main(args: Array[String]): Unit = {
    val finder = new LargestCollatzSequenceFinder
    val limit = 1000000
    val startTime = System.currentTimeMillis
    val startingNum = finder.getNumWithLongestSequence(limit)
    println("elapsed time = " + (System.currentTimeMillis - startTime))
    val numTerms = finder.getNumTerms(startingNum)
    println("The number with the longest Collatz sequence that is under " + formatNumber(limit) +
      " is " + formatNumber(startingNum) + ". It has " + numTerms + " terms.")
  }
}

class LargestCollatzSequenceFinder {
  private var cache = Map[Long, Long]()

  /** This could be slow if limit is large.
    * @param limit find largest Collatz sequence starting from a number smaller than this
    */
  def getNumWithLongestSequence(limit: Long): Long = {
    var longestStart: Long = 0
    var longestNumTerms: Long = 0
    for (i <- 1L until limit) {
      val numTerms = getNumTerms(i)
      if (numTerms > longestNumTerms) {
        longestStart = i
        longestNumTerms = numTerms
      }
    }
    longestStart
  }

  /** Find the number of terms in the Collatz sequence starting with num.
    * This should be fairly fast - especially if it is found in the cache
    * @param num number to get number of terms for
    * @return number of terms in sequence starting with num.
    */
  def getNumTerms(num: Long): Long =
    if (cache.contains(num)) cache(num)
    else {
      val numTerms = findNumTerms(num)
      cache += num -> numTerms
      numTerms
    }

  /** @param num the first term of the sequence
    * @return the number of terms in a sequence stating with num
    */
  private def findNumTerms(num: Long) =
    if (num <= 1) 1L
    else if (num % 2 == 0) 1 + getNumTerms(num >> 1)
    else 1 + getNumTerms(3 * num + 1)
}