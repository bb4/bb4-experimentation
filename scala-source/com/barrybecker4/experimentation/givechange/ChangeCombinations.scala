package com.barrybecker4.experimentation.givechange

import com.barrybecker4.common.format.FormatUtil
import com.barrybecker4.common.util.Input

/**
  * Program to compute all the possible different ways that change could
  * be given for some value.
  * For example for 0.26, the possibilities are :
  * 1Q, 1P
  * 2D, 1N, 1P
  * 2D, 6P
  * 1D, 3N, 1P
  * 1D, 2N, 6P
  * 1D, 1N, 11P
  * 4N, 1P
  * 4N, 6P
  * 3N, 11P
  * 2N, 16P
  * 1N, 21P
  * 26P
  *
  * @author Barry Becker
  */
object ChangeCombinations extends App {
  /** maximum amount of money (in cents) to give change for. */
  private val MAX_AMOUNT = 999L

  /** Recursive method to find all the change combinations.
    * @param coinMap        coins represented by change so far
    * @param centsRemaining number of cents between 0 and MAX_AMT
    */
  def showChangeCombinationsFor(coinMap: CoinMap, centsRemaining: Int): Unit = { // base case of recursion
    if (centsRemaining == 0) println(coinMap)
    for (i <- Coin.VALUES.length - 1 to 0 by -1) {
      val coin = Coin.VALUES(i)
      val numCoins = centsRemaining / coin.worthInPennies
      if (numCoins > 0) {
        coinMap.add(coin, numCoins)
        showChangeCombinationsFor(coinMap, centsRemaining - numCoins * coin.worthInPennies)
        coinMap.remove(coin, numCoins)
      }
    }
  }

  while (true) {
    val cents = Input.getLong("Enter a number of cents for which to compute change combinations\n" +
      "[0 - " + FormatUtil.formatNumber(MAX_AMOUNT) + "]:", 0, MAX_AMOUNT)
    println("The change possibilities are ...")
    showChangeCombinationsFor(new CoinMap, cents.toInt)
  }
}