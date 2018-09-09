package com.barrybecker4.experimentation.brian.cs2014projects

import java.util.Scanner


object Dice {

  def main(args: Array[String]): Unit = {
    val kbd = new Scanner(System.in)
    var sides = 0
    var count = 0
    while (count < 10000) {
      println("enter a number:")
      sides = kbd.nextInt
      print(dice(sides))
      count += 1
      println(" count: " + count)
    }
  }

  def dice(size: Int): Int = (Math.random * size + 1).toInt
}