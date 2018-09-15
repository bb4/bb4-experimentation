// Copyright by Barry G. Becker, 2015 - 2018. Licensed under MIT License: http://www.opensource.org/licenses/MIT
package com.barrybecker4.experimentation.dtablebalancer

/**
  * @author Barry Becker
  */
object TableValidator {
  private val TOL = 0.0000000001

  /** verify that the sum of the meta widths and heights are still equal to the overall width and height */
  def verifyDimensions(table: Table): Unit = {
    var totalWidth: Double = 0
    var totalHeight: Double = 0
    var i = 0
    while (i < table.getSize) {
      totalWidth += table.getColMeta(i).getLength
      totalHeight += table.getRowMeta(i).getLength
      i += 1
    }
    assert(totalWidth - table.getWidth < TOL, "Width is off. Got: " + totalWidth + " expected: " + table.getWidth)
    assert(totalHeight - table.getHeight < TOL, "Height is off. Got: " + totalHeight + " expected: " + table.getHeight)
  }
}
