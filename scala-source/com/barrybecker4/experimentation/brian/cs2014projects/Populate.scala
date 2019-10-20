package com.barrybecker4.experimentation.brian.cs2014projects

import java.util.Scanner
import scala.collection.mutable.ArrayBuffer


/**
  * @author Period 3
  */
object Populate {

  def main(args: Array[String]): Unit = {
    val myStrings = ArrayBuffer[Integer]()
    val kbd = new Scanner(System.in)
    print("what range integers would you like?")
    val n = kbd.nextInt
    print("how many numbers do you want?")
    val size = kbd.nextInt

    for (i <- 0 until size) {
      val num = (Math.random * n + 1).toInt
      myStrings += num
    }
    for (i <- 0 until size)
      print(s"${myStrings(i)}  ,1")
  }
}
