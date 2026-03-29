// Copyright by Barry G. Becker, 2011. Licensed under MIT License: http://www.opensource.org/licenses/MIT
package com.barrybecker4.experimentation.combinations

import com.barrybecker4.common.format.FormatUtil
import com.barrybecker4.math.MathUtil
import java.math.BigDecimal

/** I want to determine what are the odds of at least one student scoring higher
  * than 30 out of 40 on a multiple choice test (with CHOICES_PER_QUESTION possible answers for each question)
  * if there are N students taking it.
  *
  * The chance of getting at least one right = 1.0 - the chance of getting none right.
  *     1 - pow(3/4, 40)  0.999989
  * In other words, you have only 1/100,000 chance of getting all of them wrong if you guess randomly.
  *
  * @author Barry Becker
  */
object CombinationApp:

  private val NUM_RIGHT = 30
  private val NUM_QUESTIONS = 40

  /** num answers for one of the multiple choice questions. */
  private val CHOICES_PER_QUESTION = 4

  /** The chance of getting any given problem wrong. */
  private val CHANCE_WRONG = (CHOICES_PER_QUESTION - 1.0) / CHOICES_PER_QUESTION.toDouble

  /**
    * The general formula for probability of getting numRight or more right out of numQuestions is:
    *
    * SUM i=(0, numQuestions-numRight) {
    *   diff = numQuestions - numRight
    *   C(numQuestions, numRight+i)  * pow(numChoices-1, diff - i)
    *   -------------------------------------------------------------------
    *   pow(numChoices, numQuestions)
    * }
    *
    * @param numRight     the student must get at least this number correct
    * @param numQuestions the number of questions on the test.
    * @return the probability of a single student getting numRight or more questions correct out of numQuestions.
    */
  private def getProbabilityOfNorMoreRight(numRight: Int, numQuestions: Int): Double =
    var prob = BigDecimal.ZERO
    val diff = numQuestions - numRight
    var i = 1
    while i <= diff do
      val comb = new BigDecimal(MathUtil.combination(numQuestions, numRight + i).doubleValue)
      var result = comb.multiply(new BigDecimal(Math.pow(CHANCE_WRONG, diff - i)))
      result = result.divide(new BigDecimal(Math.pow(CHOICES_PER_QUESTION, numQuestions - diff + i)))
      prob = prob.add(result)
      i += 1
    prob.doubleValue

  private def printProbabilitiesUpToMaxQuestions(): Unit =
    var i = 0
    while i <= NUM_QUESTIONS do
      val x = getProbabilityOfNorMoreRight(i, NUM_QUESTIONS)
      println("Probability " + i + " or more right when taking test is " + FormatUtil.formatNumber(x))
      i += 1

  private def printClassProbability(studentCount: Int): Unit =
    val p = getProbabilityOfNorMoreRight(NUM_RIGHT, NUM_QUESTIONS)
    val prob = 1.0 - Math.pow(1.0 - p, studentCount)
    println(
      "Probability of having at least one student out of " + studentCount + " get >=" + NUM_RIGHT + " is " + prob
    )

  def main(args: Array[String]): Unit =
    printProbabilitiesUpToMaxQuestions()
    printClassProbability(20)
    printClassProbability(100)
