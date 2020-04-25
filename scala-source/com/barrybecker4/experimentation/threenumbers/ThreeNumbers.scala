package com.barrybecker4.experimentation.threenumbers

/**
  * Fun with double precision arithmetic
  * This program shows how you should not necessarily trust the results of
  * arithmetic in the JVM. It's not always what you might expect!
  * The order in which you perform operation can matter a lot.
  * Below we try various cases of
  * a - 3 - 4 to see how it compares to a - 4 - 3
  * for various values of a.
  */
object ThreeNumbers extends App {

  def diff(a: Float, b: Float = 3.0f, c: Float = 0.4f): Unit = {
    val first = a - b - c
    val second = a - c - b
    val s = s"(a - b - c) = $first    (a - c - b) = $second    for $a"
    println(s + (if (first == second) "    matched!!!" else ""))
  }

  for (i <- Range.BigDecimal(3.0,  3.5, 0.001)) {
    diff(i.toFloat)
  }

  println("\n")

  // 1.0 - 2.3 matched. Starts not matching at 2.4. Starts matching again at 5.4
  for (i <- Range.BigDecimal(1.0,  7.0, 0.1)) {
    diff(i.toFloat)
  }

  println("\n")

  for (i <-Range.BigDecimal(0, 100, 10.0)) {
    diff(i.toFloat)
  }
}
