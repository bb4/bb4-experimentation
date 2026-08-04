package com.barrybecker4.experimentation.game24

object Exp:
  val PLUS = "+"
  val MINUS = "-"
  val TIMES = "*"
  val DIVIDE = "/"

case class Exp(result: Float, expression: String):

  def combine(exp: Exp, op: String, useParen: Boolean = false): Exp =
    val leftParen = if useParen then "(" else ""
    val rightParen = if useParen then ")" else ""
    val newExp = leftParen + expression + op + exp.expression + rightParen

    op match
      case Exp.PLUS => Exp(result + exp.result, newExp)
      case Exp.MINUS => Exp(result - exp.result, newExp)
      case Exp.TIMES => Exp(result * exp.result, newExp)
      case Exp.DIVIDE => Exp(result / exp.result, newExp)
      case _ => throw new UnsupportedOperationException("Unsupported operator: " + op)
