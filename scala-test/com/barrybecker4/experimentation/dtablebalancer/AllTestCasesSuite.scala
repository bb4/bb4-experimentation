package com.barrybecker4.experimentation.dtablebalancer

import org.junit.jupiter.api.Assertions.assertEquals
import AllTestCasesSuite.TOL
import org.scalatest.funsuite.AnyFunSuite

object AllTestCasesSuite {
  val TOL = 0.00001
}
/**
  * @author Barry Becker
  */
class AllTestCasesSuite extends AnyFunSuite {

  test("runAllTestCases") {

     for (tc <- TestCases.CASES) {
       assertEquals(tc.expNormScale, tc.table.getNormalizationScale, TOL, tc.name + "\nWrong normalization scale\n")
       assertEquals(tc.expInitialOverallRatio, tc.table.getOverallCoverage, TOL, tc.name + "\nWrong overall coverage for " + tc.table + "\n")

       tc.balancer.doBalancing(tc.table)

       assertEquals(tc.expBalancedRatio, tc.table.getOverallCoverage, TOL, tc.name + "\nWrong balanced coverage for " + tc.table + "\n")
     }
  }
}
