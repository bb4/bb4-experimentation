package com.barrybecker4.experimentation.brian.cs2014projects

import java.util.Scanner


/**
  * @author Period 5
  */
object CandP {

  def main(args: Array[String]): Unit = {
    val kbd = new Scanner(System.in)
    println("how many choices do you have to choose from:")
    val choices = kbd.nextInt
    println("how many choices can you choose:")
    val selection = kbd.nextInt
    println("P = " + choices + "!/(" + choices + "-" + selection + ")!")
    println("C = " + choices + "!/(" + selection + "!)(" + choices + "-" + selection + ")!")
  }
}