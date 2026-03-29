package com.barrybecker4.experimentation.game24

import scala.collection.mutable

import Exp._

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
  def find24Expression(digits: IndexedSeq[Int]): String =
    findAllPossibleExpressions(digits)
      .find(_.result == num)
      .map(_.expression)
      .getOrElse("No solution")

  def findAllPossibleExpressions(digits: Seq[Int], parentIsTimesOrDivide: Boolean = false): Seq[Exp] =

    if digits.length == 1 then Seq(Exp(digits.head.toFloat, digits.head.toString))
    else
      val out = mutable.ArrayBuffer.empty[Exp]
      for partition <- getPartitions(digits) do
        appendAdditiveCombinations(out, partition, parentIsTimesOrDivide)
        appendMultiplicativeCombinations(out, partition)
      out.toSeq

  private def appendAdditiveCombinations(
      out: mutable.ArrayBuffer[Exp],
      partition: (Seq[Int], Seq[Int]),
      parentIsTimesOrDivide: Boolean
  ): Unit =
    val (left, right) = partition
    for
      exp1 <- findAllPossibleExpressions(left)
      exp2 <- findAllPossibleExpressions(right)
    do
      out += exp1.combine(exp2, PLUS, parentIsTimesOrDivide)
      out += exp1.combine(exp2, MINUS, parentIsTimesOrDivide)
      out += exp2.combine(exp1, MINUS, parentIsTimesOrDivide)

  private def appendMultiplicativeCombinations(
      out: mutable.ArrayBuffer[Exp],
      partition: (Seq[Int], Seq[Int])
  ): Unit =
    val (left, right) = partition
    for
      exp1 <- findAllPossibleExpressions(left, parentIsTimesOrDivide = true)
      exp2 <- findAllPossibleExpressions(right, parentIsTimesOrDivide = true)
    do
      out += exp1.combine(exp2, TIMES)
      out += exp1.combine(exp2, DIVIDE)
      out += exp2.combine(exp1, DIVIDE)

  def getPartitions(digits: Seq[Int]): Seq[(Seq[Int], Seq[Int])] =
    if digits.length == 2 then Seq((Seq(digits.head), Seq(digits(1))))
    else if digits.length == 3 then
      Seq(
        (Seq(digits.head), digits.drop(1)),
        (Seq(digits(1)), Seq(digits.head, digits(2))),
        (Seq(digits(2)), digits.take(2))
      )
    else GameNumberSolver.PARTITION_INDICES.map(indices => partition(digits, indices))

  def partition(digits: Seq[Int], indices: Seq[Int]): (Seq[Int], Seq[Int]) =
    val d = digits.toIndexedSeq
    def valuesAt(idxs: Seq[Int]): Seq[Int] = idxs.map(d.apply)
    val otherIndices = GameNumberSolver.ALL_INDICES.filterNot(indices.contains)
    (valuesAt(indices), valuesAt(otherIndices))

}
