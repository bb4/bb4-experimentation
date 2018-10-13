package com.barrybecker4.experimentation.dtablebalancer

import org.scalatest.FunSuite

/**
  * @author Barry Becker
  */
class TableSuite extends FunSuite {
  test("Uniform3x3TableWithIdealArea") {
    val table = new Table(TableExamples.UNIFORM_3x3, 300, 300)
    assertResult("1.00\t1.00\t1.00\t\n" + "1.00\t1.00\t1.00\t\n" + "1.00\t1.00\t1.00\t\n" + "Overall coverage: 1.00") {
      table.toString
    }
  }

  test("Uniform3x3Table") {
    val table = new Table(TableExamples.UNIFORM_3x3, 100, 100)
    assertResult("1.00\t1.00\t1.00\t\n" + "1.00\t1.00\t1.00\t\n" + "1.00\t1.00\t1.00\t\n" + "Overall coverage: 1.0") {
      table.toString
    }
  }
}
