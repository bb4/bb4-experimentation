// Copyright by Barry G. Becker, 2015 - 2018. Licensed under MIT License: http://www.opensource.org/licenses/MIT
package com.barrybecker4.experimentation.euler

import java.math.BigInteger
import com.barrybecker4.experimentation.readnumber.NumberTranslator

/**
  * @author Barry Becker
  */
object NumberLetterCounter extends App {
  var sum = 0
  val translator = new NumberTranslator
  for (i <- 1 to  1000) {
    val numberStr = translator.translateToBritish(BigInteger.valueOf(i))
    val numNoSpaces = numberStr.replace(" ", "")
    println(numberStr + " === " + numNoSpaces + "   num=" + numNoSpaces.length)
    sum += numNoSpaces.length
  }
  println("Total num letters = " + sum)
}