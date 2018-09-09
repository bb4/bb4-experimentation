package com.barrybecker4.experimentation.brian.cs2014projects

/**
  * @author Period 3
  */
object Ackermann {
  def main(args: Array[String]): Unit = {
    val c = new Ackermann
    println(c.Ackermann(3, 3))
  }
}

class Ackermann {
  def Ackermann(m: Int, n: Int): Int = if (m == 0) n + 1
  else if (n == 0) Ackermann(m - 1, 1)
  else Ackermann(m - 1, Ackermann(m, n - 1))
}