package com.barrybecker4.experimentation.game24

import com.barrybecker4.common.util.Input

/**
  * See https://www.freecodecamp.org/learn/coding-interview-prep/rosetta-code/24-game
  */
object Game24App:

  def main(args: Array[String]): Unit =
    val num = Input.getBigInteger("Enter 4 digits, where each is in [1-9]: ")
    val digits = num.toString.map(c => c.asDigit)

    val solver = new GameNumberSolver(24)

    if valid(digits) then
      println("digits = " + digits.mkString(","))
      println("The answer is : " + solver.find24Expression(digits))
    else println("Need exactly four digits, each from 1 through 9.")

  def valid(digits: IndexedSeq[Int]): Boolean =
    digits.length == 4 && digits.forall(d => d >= 1 && d <= 9)
