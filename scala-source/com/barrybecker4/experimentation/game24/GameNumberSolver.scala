package com.barrybecker4.experimentation.game24

import javax.script.ScriptEngineManager


object GameNumberSolver {
  val manager = new ScriptEngineManager

  // Since nashborn is deprecated, we could try rhino if it is ever removed
  // Instead of using an engine, we could do the computation locally while building the expression
  private val ENGINE = manager.getEngineByName("js")

  private val PARTITION_INDICES = Seq(
    Seq(0), Seq(1), Seq(2), Seq(3),
    Seq(0, 1), Seq(0, 2), Seq(0, 3), Seq(1, 2), Seq(1, 3), Seq(2, 3)
  )

  private val ALL_INDICES = Seq(0, 1, 2, 3)
}

class GameNumberSolver(num: Float = 24.0f) {

  /**
    * @param digits 4 digits in range [1 - 9]
    * @return an expression that sums to 24
    */
  def find24Expression(digits: IndexedSeq[Int]): String = {

    for (exp <- findAllPossibleExpressions(digits)) {
      if (GameNumberSolver.ENGINE.eval(exp).toString.toFloat == num)
        return exp
    }

    "No solution"
  }

  def findAllPossibleExpressions(digits: Seq[Int], parentIsTimesOrDivide: Boolean = false): Seq[String] = {

    val leftParen = if (parentIsTimesOrDivide) "(" else ""
    val rightParen = if (parentIsTimesOrDivide) ")" else ""

    if (digits.length == 1) {
      Seq(digits.head.toString)
    }
    else {
      val partitions = getPartitions(digits)

        var expList: Seq[String] = Seq()

        for (partition <- partitions) {
          for (exp1 <- findAllPossibleExpressions(partition._1)) {
            for (exp2 <- findAllPossibleExpressions(partition._2)) {
              expList :+= leftParen + exp1 + "+" + exp2 + rightParen
              expList :+= leftParen + exp1 + "-" + exp2 + rightParen
              expList :+= leftParen + exp2 + "-" + exp1 + rightParen
            }
          }

          for (exp1 <- findAllPossibleExpressions(partition._1, parentIsTimesOrDivide = true)) {
            for (exp2 <- findAllPossibleExpressions(partition._2, parentIsTimesOrDivide = true)) {
              expList :+= exp1 + "*" + exp2
              expList :+= exp1 + "/" + exp2
              expList :+= exp2 + "/" + exp1
            }
          }
        }
      expList
    }
  }

  def getPartitions(digits: Seq[Int]): Seq[Tuple2[Seq[Int], Seq[Int]]] = {
    if (digits.length == 2)
      Seq( ( Seq( digits.head), Seq(digits(1)) ) )
    else if (digits.length == 3)
      Seq(
        (Seq(digits.head), digits.drop(1)),
        (Seq(digits(1)), Seq(digits.head, digits(2))),
        (Seq(digits(2)), digits.take(2))
      )
    else GameNumberSolver.PARTITION_INDICES.map(indices => partition(digits, indices))
  }

  def partition(digits: Seq[Int], indices: Seq[Int]): (Seq[Int], Seq[Int]) = {
    def otherIndices = GameNumberSolver.ALL_INDICES.filter(i => !indices.contains(i))
    (indices.collect(digits), otherIndices.collect(digits))
  }

}
