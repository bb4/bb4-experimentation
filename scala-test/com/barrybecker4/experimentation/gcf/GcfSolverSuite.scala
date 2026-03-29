package com.barrybecker4.experimentation.gcf

import org.scalatest.funsuite.AnyFunSuite

class GcfSolverSuite extends AnyFunSuite:

  test("euclid and brute agree on simple pairs") {
    for ((a, b, expected) <- Seq((48L, 18L, 6L), (17L, 13L, 1L), (1071L, 462L, 21L))) {
      assert(new EuclidGcfSolver().findSolution(a, b) == expected)
      assert(new BruteGcfSolver().findSolution(a, b) == expected)
    }
  }

  test("solvers agree when one argument is zero") {
    assert(new EuclidGcfSolver().findSolution(0L, 9L) == 9L)
    assert(new BruteGcfSolver().findSolution(0L, 9L) == 9L)
    assert(new EuclidGcfSolver().findSolution(9L, 0L) == 9L)
    assert(new BruteGcfSolver().findSolution(9L, 0L) == 9L)
  }
