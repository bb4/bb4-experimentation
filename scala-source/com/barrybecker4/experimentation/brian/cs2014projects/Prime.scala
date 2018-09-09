package com.barrybecker4.experimentation.brian.cs2014projects

import java.util.Scanner
import scala.collection.mutable.ArrayBuffer


/**
  * @author Period 3
  */
object Prime {

  def main(args: Array[String]): Unit = {
    val factors = ArrayBuffer[Integer]()
    val kbd = new Scanner(System.in)
    println("Find the prime factorization of what number?")
    var number = kbd.nextInt
    var i = 2
    while (i <= number) {
      while (number % i == 0) {
        factors.append(i)
        number /= i
      }
      i += 1
    }
    println("prime factorization = " + factors)
  }
}