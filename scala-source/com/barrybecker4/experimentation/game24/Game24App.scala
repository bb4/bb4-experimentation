package com.barrybecker4.experimentation.game24

import com.barrybecker4.common.util.Input


object Game24App extends App {

  val num = Input.getBigInteger("Enter 4 digits, where each is in [1-9]: ")
  val digits = num.toString().map(c => c.toString.toInt)

  val solver = new GameNumberSolver(24);
  if (valid(digits)) {
    println("digits = " + digits.mkString(","))
    println("The answer is : " + solver.find24Expression(digits))
  }

  def valid(digits: IndexedSeq[Int]): Boolean =
    digits.count(c => c > 0) == 4
}
