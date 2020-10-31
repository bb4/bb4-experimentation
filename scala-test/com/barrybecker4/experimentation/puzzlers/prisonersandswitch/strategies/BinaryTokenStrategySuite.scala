package com.barrybecker4.experimentation.puzzlers.prisonersandswitch.strategies

import com.barrybecker4.experimentation.puzzlers.prisonorsandswitch.simulation.strategies.BinaryTokensStrategy
import com.barrybecker4.experimentation.puzzlers.prisonorsandswitch.simulation.strategies.BinaryTokensStrategy._
import org.scalatest.funsuite.AnyFunSuite



class BinaryTokenStrategySuite extends AnyFunSuite {

  val binTokenStrategy = new BinaryTokensStrategy(10)

  test("T function 1") {
    assertResult(0) {
      computeT(1)
    }
  }

  test("T function") {
    assertResult(Array[Int](0, 0, 3, 4, 5, 6, 6, 7, 7, 8)) {
      Array.range(1, 11).map(computeT)
    }
  }


  test("nlnFun 10") {
    assertResult(31.366175382420018) {
      nlnFun(10)
    }
  }

  test("nlnFun 20") {
    assertResult(81.85841947837879) {
      nlnFun(20)
    }
  }
  test("nlnFun 100") {
    assertResult(613.2349811795992) {
      nlnFun(100)
    }
  }

  test("p sequence for n=8") {
    val n = 8
    val T = computeT(n)

    assertResult(Array[Int](1, 1, 1, 1, 1, 1, 1)) {
      Array.range(1, T + 1).map(k => computeP(k, T, n))
    }
  }

  test("p sequence for n=100") {
    val n = 100
    val T = computeT(n)

    assertResult(Array[Int](1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1)) {
      Array.range(1, T + 1).map(k => computeP(k, T, n))
    }
  }
}

