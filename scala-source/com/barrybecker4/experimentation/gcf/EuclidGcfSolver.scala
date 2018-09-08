/** Copyright by Barry G. Becker, 2000-2011. Licensed under MIT License: http://www.opensource.org/licenses/MIT  */
package com.barrybecker4.experimentation.gcf

/**
  * Find the GCF by an elegant recursive approach developer by Euclid  over 2300 years ago.
  * See http://en.wikipedia.org/wiki/Euclidean_algorithm
  */
class EuclidGcfSolver extends GcfSolver {
  override def findSolution(a: Long, b: Long): Long = if (a == 0) b
  else findSolution(b % a, a)
}
