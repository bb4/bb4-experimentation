package com.barrybecker4.experimentation.game24

import Exp._

object Exp {
  val PLUS = "+"
  val MINUS = "-"
  val TIMES = "*"
  val DIVIDE = "/"
}

case class Exp(result: Float, expression: String) {

  def combine(exp: Exp, op: String, useParen: Boolean = false): Exp = {
    val leftParen = if (useParen) "(" else ""
    val rightParen = if (useParen) ")" else ""

    val newExp = leftParen + expression + op + exp.expression + rightParen

    op match {
      case PLUS => Exp(result + exp.result, newExp)
      case MINUS => Exp(result - exp.result, newExp)
      case TIMES => Exp(result * exp.result, newExp)
      case DIVIDE => Exp(result / exp.result, newExp)
      case _ => throw new UnsupportedOperationException("Unsupported operator: " + op)
    }
  }
}
