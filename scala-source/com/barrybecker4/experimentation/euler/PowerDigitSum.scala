package com.barrybecker4.experimentation.euler

import java.math.BigInteger


/**
  * @author Barry Becker
  */
object PowerDigitSum extends App {
    val base = 2
    val exp = 1000
    val pds = new PowerDigitSum(base, exp)
    println("Power digit sum of " + base + " ^ " + exp + " is " + pds.getSum)
}

class PowerDigitSum (var base: Int, var exp: Int) {

  def getSum: Int = {
    val result = getNumber
    val strValue = result.toString
    var sum = 0
    for (i <- 0 until strValue.length)
      sum += strValue.substring(i, i + 1).toInt
    sum
  }

  private def getNumber = {
    val value = BigInteger.valueOf(base)
    value.pow(exp)
  }
}
