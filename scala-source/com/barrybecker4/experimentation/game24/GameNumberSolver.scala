package com.barrybecker4.experimentation.game24



object GameNumberSolver {

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
      if (exp.result == num)
        return exp.expression
    }

    "No solution"
  }

  def findAllPossibleExpressions(digits: Seq[Int], parentIsTimesOrDivide: Boolean = false): Seq[Exp] = {

    val leftParen = if (parentIsTimesOrDivide) "(" else ""
    val rightParen = if (parentIsTimesOrDivide) ")" else ""

    if (digits.length == 1) {
      Seq(Exp(digits.head, digits.head.toString))
    }
    else {
      val partitions = getPartitions(digits)

        var expList: Seq[Exp] = Seq()

        for (partition <- partitions) {
          for (exp1 <- findAllPossibleExpressions(partition._1)) {
            for (exp2 <- findAllPossibleExpressions(partition._2)) {
              expList :+= Exp(exp1.result + exp2.result, leftParen + exp1.expression + "+" + exp2.expression + rightParen)
              expList :+= Exp(exp1.result - exp2.result, leftParen + exp1.expression + "-" + exp2.expression + rightParen)
              expList :+= Exp(exp2.result - exp1.result, leftParen + exp2.expression + "-" + exp1.expression + rightParen)
            }
          }

          for (exp1 <- findAllPossibleExpressions(partition._1, parentIsTimesOrDivide = true)) {
            for (exp2 <- findAllPossibleExpressions(partition._2, parentIsTimesOrDivide = true)) {
              expList :+= Exp(exp1.result * exp2.result, exp1.expression+ "*" + exp2.expression)
              expList :+= Exp(exp1.result / exp2.result, exp1.expression + "/" + exp2.expression)
              expList :+= Exp(exp2.result / exp1.result, exp2.expression + "/" + exp1.expression)
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
