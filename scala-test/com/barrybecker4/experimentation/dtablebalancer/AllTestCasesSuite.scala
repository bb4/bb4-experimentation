package com.barrybecker4.experimentation.dtablebalancer

import org.junit.Assert.assertEquals
import org.scalatest.FunSuite
import AllTestCasesSuite.TOL

object AllTestCasesSuite {
  val TOL = 0.00001
}
/**
  * @author Barry Becker
  */
class AllTestCasesSuite extends FunSuite {

  test("runAllTestCases") {

     for (tc <- TestCases.CASES) {
       assertEquals(tc.name + "\nWrong normalization scale\n", tc.expNormScale, tc.table.getNormalizationScale, TOL)
       assertEquals(tc.name + "\nWrong overall coverage for " + tc.table + "\n", tc.expInitialOverallRatio, tc.table.getOverallCoverage, TOL)

       tc.balancer.doBalancing(tc.table)

       assertEquals(tc.name + "\nWrong balanced coverage for " + tc.table + "\n", tc.expBalancedRatio, tc.table.getOverallCoverage, TOL)
     }
  }
}
