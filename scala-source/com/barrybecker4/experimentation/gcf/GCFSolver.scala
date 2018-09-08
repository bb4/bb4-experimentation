/** Copyright by Barry G. Becker, 2000-2018. Licensed under MIT License: http://www.opensource.org/licenses/MIT  */
package com.barrybecker4.experimentation.gcf

/**
  * Interface for greatest common factor solvers.
  * @author Barry Becker
  */
trait GCFSolver {
  def findSolution(a: Long, b: Long): Long
}
