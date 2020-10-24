package com.barrybecker4.experimentation.game24

import org.scalatest.funsuite.AnyFunSuite

class Game24SolverSuite extends AnyFunSuite {

  val solver = new GameNumberSolver(24)

  test("partition") {
    assertResult((Seq(8), Seq(4, 2, 3))) {
      solver.partition(Seq(4, 8, 2, 3), Seq(1))
    }
  }

  test("exp for 7587") {
      assertResult("4+5+8+7") {
        solver.find24Expression(IndexedSeq(4, 5, 8, 7))
      }
  }

  test("exp for 1111") {
    assertResult("No solution") {
      solver.find24Expression(IndexedSeq(1, 1, 1, 1))
    }
  }
  test("exp for 4878") {
    assertResult("4*(7-8/8)") {
      solver.find24Expression(IndexedSeq(4, 8, 7, 8))
    }
  }
  test("exp for 1234") {
    assertResult("1*2*3*4") {
      solver.find24Expression(IndexedSeq(1, 2, 3, 4))
    }
  }
  test("exp for 6789") {
    assertResult("6*8/(9-7)") {
      solver.find24Expression(IndexedSeq(6, 8, 9, 7))
    }
  }
  test("exp for 1127") {
    assertResult("(1+2)*(1+7)") {
      solver.find24Expression(IndexedSeq(1, 1, 2, 7))
    }
  }

  test("exp for 1111 - no solution") {
    assertResult("No solution") {
      solver.find24Expression(IndexedSeq(1, 1, 1, 1))
    }
  }
}



