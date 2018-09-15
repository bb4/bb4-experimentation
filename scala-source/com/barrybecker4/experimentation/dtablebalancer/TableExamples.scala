// Copyright by Barry G. Becker, 2015 - 2018. Licensed under MIT License: http://www.opensource.org/licenses/MIT
package com.barrybecker4.experimentation.dtablebalancer

/**
  * @author Barry Becker
  */
object TableExamples {

  val UNIFORM_2x2: Array[Array[Int]] =
    Array[Array[Int]](Array(2, 2), Array(2, 2))

  val UNIFORM_3x3: Array[Array[Int]] =
    Array[Array[Int]](Array(100, 100, 100), Array(100, 100, 100), Array(100, 100, 100))

  val NARROW_MIDDLE_COLUMN_3x3: Array[Array[Int]] =
    Array[Array[Int]](Array(100, 10, 100), Array(100, 40, 100), Array(100, 15, 100))

  val NARROW_MIDDLE_ROW_3x3: Array[Array[Int]] =
    Array[Array[Int]](Array(100, 100, 100), Array(10, 40, 15), Array(100, 100, 100))

  val RANDOM_3x3: Array[Array[Int]] =
    Array[Array[Int]](Array(11, 100, 60), Array(40, 55, 15), Array(200, 100, 178))

  val RANDOM_4x4: Array[Array[Int]] =
    Array[Array[Int]](
      Array(11, 100, 60, 321),
      Array(40, 55, 15, 33),
      Array(200, 100, 178, 378),
      Array(120, 450, 278, 478)
    )

  /**
    * Simulate race by native country census data.
    * Rows are race (Black, Asian, Hispanic, White),
    * cols native country (Africa, China, Brazil, U.S)
    */
  val RACE_BY_NATIVE_4x4: Array[Array[Int]] =
    Array[Array[Int]](Array(411, 6, 9, 351), Array(6, 1655, 11, 273), Array(9, 5, 98, 268), Array(17, 11, 14, 1078))
}
