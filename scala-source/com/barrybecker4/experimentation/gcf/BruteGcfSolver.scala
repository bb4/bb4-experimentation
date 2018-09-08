/** Copyright by Barry G. Becker, 2000-2018. Licensed under MIT License: http://www.opensource.org/licenses/MIT  */
package com.barrybecker4.experimentation.gcf

/**
  * Find the GCF by brute force approach.
  * This is <i>much</i> slower than Euclid's algorithm
  * @author Barry Becker
  */
final class BruteGcfSolver extends GcfSolver {
  override def findSolution(a: Long, b: Long): Long = {
    var aa = a
    var bb = b
    if (a > b) {
      aa = b
      bb = a
    }

    var i = aa
    while (i > 1) {
      if ((aa % i == 0) && (bb % i == 0)) return i
      i -= 1
    }
    1
  }
}
