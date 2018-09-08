/** Copyright by Barry G. Becker, 2000-2018. Licensed under MIT License: http://www.opensource.org/licenses/MIT  */
package com.barrybecker4.experimentation.gcf

/**
  * Compare two different ways to find the Greatest common factor of two numbers.
  * @author Barry Becker
  */
object GcfProgram {
  private val bruteSolver = new BruteGcfSolver
  private val euclidSolver = new EuclidGcfSolver

  def main(args: Array[String]): Unit = {
    // these will take about 15s for the brute force approach.
    val a = 2342343454L
    val b = 456787697786L
    //val a = 36618
    //val b = 8105362
    System.out.println("Finding Greatest Common Factor of a=" + a + " and b=" + b)
    showResult(a, b, euclidSolver)
    showResult(a, b, bruteSolver)
  }

  private def showResult(a: Long, b: Long, solver: GcfSolver): Unit = {
    System.out.println("finding answer using " + solver.getClass.getName + " ... ")
    val time = System.currentTimeMillis
    val answer = solver.findSolution(a, b)
    val elapsedTime = System.currentTimeMillis - time
    System.out.println("found answer = " + answer + " in time = " + elapsedTime + " milliseconds\n")
  }
}
