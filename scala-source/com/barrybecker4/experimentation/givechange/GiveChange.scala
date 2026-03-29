package com.barrybecker4.experimentation.givechange

import com.barrybecker4.common.format.FormatUtil
import com.barrybecker4.common.util.Input

/**
  * Program to compute change given a number of cents between 0 and 99.
  * @author Barry Becker
  */
object GiveChange:
  /** maximum amount of money (in cents) to give change for. */
  private val MAX_AMOUNT = 999L

  /** Displays the amount of change to the user.
    * @param cents number of cents between 0 and MAX_AMT
    */
  def showChangeFor(cents: Long): Unit =
    println("Your change is ...")
    var remainingCents = cents
    // start with the largest denomination coin and work down.
    for i <- Coin.VALUES.length - 1 to 0 by -1 do
      val coin = Coin.VALUES(i)
      val num = remainingCents / coin.worthInPennies
      if num != 0 then
        val coinName = if num == 1 then coin.name else coin.pluralName
        println("  " + FormatUtil.formatNumber(num) + ' ' + coinName)
        remainingCents -= num * coin.worthInPennies

  def main(args: Array[String]): Unit =
    while true do
      val cents = Input.getLong(
        "Enter a number of cents for which to compute ideal change\n" +
          "[0 - " + FormatUtil.formatNumber(MAX_AMOUNT) + "]:",
        0,
        MAX_AMOUNT
      )
      showChangeFor(cents)
      println("Have a nice day!\n")
