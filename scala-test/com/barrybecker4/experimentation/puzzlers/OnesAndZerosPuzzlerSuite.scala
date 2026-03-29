package com.barrybecker4.experimentation.puzzlers

import java.math.BigInteger

import org.scalatest.funsuite.AnyFunSuite

class OnesAndZerosPuzzlerSuite extends AnyFunSuite:

  test("getOnesNumber builds repunits") {
    assert(OnesAndZerosPuzzler.getOnesNumber(1) == BigInteger.ONE)
    assert(OnesAndZerosPuzzler.getOnesNumber(3) == new BigInteger("111"))
  }

  test("remainder for small repunit vs modulus") {
    val n = BigInteger.valueOf(3)
    val ones = OnesAndZerosPuzzler.getOnesNumber(3)
    assert(ones.divideAndRemainder(n)(1) == BigInteger.ZERO)
  }
