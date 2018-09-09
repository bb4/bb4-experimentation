package com.barrybecker4.experimentation.brian.cs2014projects

import java.util.Scanner


object Fib {

  def main(args: Array[String]): Unit = {
    val kbd = new Scanner(System.in)
    print("What place of the sequence would you like?")
    val x = kbd.nextInt
    print(Fib(x, 0, 1))
  }

  def Fib(place: Int, x: Int, y: Int): Int = {
    var temp = 0
    if (place <= 1) return y
    temp = x
    val xx = y
    val yy = temp + y
    Fib(place - 1, xx, yy)
  }
}