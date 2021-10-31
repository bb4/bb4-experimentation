package com.barrybecker4.experimentation.euler

object TriangleNumber extends App {
    var num = 10
    var tnum = new TriangleNumber(num)
    while (tnum.getNumFactors < 500) {
      num += 1
      tnum = new TriangleNumber(num)
    }
    println(s"The $num the triangle number had a value of ${tnum.getValue} with ${tnum.getNumFactors} factors.")
}

/**
  * The first of two adjacent relatively prime numbers that can be used to determine the value using
  * relPrime * (relPrime + 1 ) / 2
  * @param relPrime the number of the triangle number to construct.
  *                     For example, 5 will be 1 + 2 + 3 + 4 + 5 =  15, the 5th triangle number
  * @author Barry Becker
  */
class TriangleNumber(var relPrime: Long) {

  /** cached number of factors */
  private var numFactors: Option[Int] = None

  def getValue: Long = relPrime * (relPrime + 1) / 2

  def getNumFactors: Int = {
    if (numFactors.isEmpty)
      numFactors = Some(getNumFactors(getValue))
    numFactors.get
  }

  private def getNumFactors(num: Long) = {
    var nod = 0
    val sqrt = Math.sqrt(num.toDouble).toInt
    for (i <- 1 to sqrt)
      if (num % i == 0) nod += 2

    // Correction if the number is a perfect square
    if (sqrt * sqrt == num) nod - 1 else nod
  }
}