package com.barrybecker4.experimentation.euler

import org.scalatest.funsuite.AnyFunSuite

class TriangleNumberSuite extends AnyFunSuite:

  test("nth triangle value") {
    assert(new TriangleNumber(1).getValue == 1)
    assert(new TriangleNumber(5).getValue == 15)
    assert(new TriangleNumber(7).getValue == 28)
    assert(new TriangleNumber(8).getValue == 36)
  }

  test("factor counts for small triangle numbers") {
    // 28 = 1+2+3+4+5+6+7 has factors 1,2,4,7,14,28 → 6
    assert(new TriangleNumber(7).getNumFactors == 6)
    // 36 has factors 1,2,3,4,6,9,12,18,36 → 9
    assert(new TriangleNumber(8).getNumFactors == 9)
  }

  test("getNumFactors is cached") {
    val t = new TriangleNumber(7)
    assert(t.getNumFactors == 6)
    assert(t.getNumFactors == 6)
  }
