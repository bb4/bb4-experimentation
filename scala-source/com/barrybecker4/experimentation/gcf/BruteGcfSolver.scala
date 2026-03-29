/** Copyright by Barry G. Becker, 2000-2018. Licensed under MIT License: http://www.opensource.org/licenses/MIT  */
package com.barrybecker4.experimentation.gcf

/**
  * Find the GCF by brute force approach.
  * This is <i>much</i> slower than Euclid's algorithm
  * @author Barry Becker
  */
final class BruteGcfSolver extends GcfSolver {
  override def findSolution(a: Long, b: Long): Long =
    if a == 0 then math.abs(b)
    else if b == 0 then math.abs(a)
    else
      val (small, big) = if a <= b then (a, b) else (b, a)
      Iterator
        .iterate(small)(_ - 1)
        .takeWhile(_ > 1)
        .find(i => small % i == 0 && big % i == 0)
        .getOrElse(1L)
}
