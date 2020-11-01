package com.barrybecker4.experimentation.puzzlers.prisonersandswitch.strategies

import com.barrybecker4.experimentation.puzzlers.prisonorsandswitch.simulation.strategies.BinaryTokensStrategy
import com.barrybecker4.experimentation.puzzlers.prisonorsandswitch.simulation.strategies.BinaryTokensStrategy._
import org.scalatest.funsuite.AnyFunSuite



class BinaryTokenStrategySuite extends AnyFunSuite {

  val binTokenStrategy = new BinaryTokensStrategy(10)

  /** https://math.stackexchange.com/questions/285700/how-many-random-samples-needed-to-pick-all-elements-of-set
    * expected number of days for all N prisoners to be sampled
    */
  def expNumDaysForAll(n: Int): Int = {
    var sum: Double = 0
    for (i <- 1 to n) {
      sum += 1.0 / i.toDouble
    }
    (n * sum).toInt
  }

  test("T function 1") {
    assertResult(0) {
      computeT(n = 1)
    }
  }

  test("T function") {
    assertResult(Array[Int](0, 0, 1, 4, 4, 5, 8, 9, 9, 9)) {
      Array.range(1, 11).map(computeT)
    }
  }

  test("nlnFun 10") {
    assertResult(3.0) {
      nlnFun(10)
    }
  }

  test("nlnFun 20") {
    assertResult(4.0) {
      nlnFun(20)
    }
  }
  test("nlnFun 100") {
    assertResult(6.0) {
      nlnFun(100)
    }
  }

  // Every Pk should be a non-negative power of 2 ( e.g. 1, 2, 4, 8, etc)
  test("p sequence for n=8") {
    val n = 8
    val T = computeT(n)

    assertResult(Array[Int](1, 1, 2, 2, 2, 4, 4, 1, 1)) {
      Array.range(1, T + 1).map(k => computeP(k, T, n))
    }
  }

  test("p sequence for n=100") {
    val n = 100
    val T = computeT(n)

    assertResult(Array[Int](
      1, 1, 1, 1, 2, 2, 2, 2, 2, 4, 4, 4, 4, 4, 8, 8, 8, 8, 8, 16, 16, 16, 16, 16,
      32, 32, 32, 32, 32, 64, 64, 64, 64, 64, 128, 128, 128, 128, 128)) {
      Array.range(1, T + 1).map(k => computeP(k, T, n))
    }
  }

  test("Value of nlnFun for some power of 2 values") {
    assertResult(Array[Double](3.0, 5.0, 6.0, 7.0)) {
      Array(8, 32, 64, 256).map(n => nlnFun(n))
    }
  }

  test("Value of nlnFun for other values") {
    assertResult(Array[Double](3.0, 4.0, 5.0, 6.0)) {
      Array(10, 20, 50, 100).map(n => nlnFun(n))
    }
  }

  test("expected number of days to sample all") {
    assertResult(Array[Int](29, 71, 224, 518)) {
      Array(10, 20, 50, 100).map(n => expNumDaysForAll(n))
    }
  }
}

