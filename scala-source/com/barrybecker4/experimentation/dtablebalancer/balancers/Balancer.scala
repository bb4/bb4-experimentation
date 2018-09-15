// Copyright by Barry G. Becker, 2015 - 2018. Licensed under MIT License: http://www.opensource.org/licenses/MIT
package com.barrybecker4.experimentation.dtablebalancer.balancers

import com.barrybecker4.experimentation.dtablebalancer.Table


/**
  * @author Barry Becker
  */
trait Balancer {

  def doBalancing(balance: Table): Unit

  /** If the new dimensions are just off by a pixel or two because of round-off,
    * then adjust the last row/column
    * @param table the table to adjust
    */
  protected def finalAdjust(table: Table): Unit = {
    var totalWidth: Double = 0
    var totalHeight: Double = 0
    var i = 0
    while (i < table.getSize) {
      totalWidth += table.getColMeta(i).getLength
      totalHeight += table.getRowMeta(i).getLength
      i += 1
    }
    val widthDiff = table.getWidth - totalWidth
    val heightDiff = table.getHeight - totalHeight
    assert(widthDiff <= 2)
    assert(heightDiff <= 2)
    if (widthDiff > 0) {
      val lastCol = table.getColMeta(table.getSize - 1)
      lastCol.setLength(lastCol.getLength + widthDiff)
    }
    if (heightDiff > 0) {
      val lastRow = table.getRowMeta(table.getSize - 1)
      lastRow.setLength(lastRow.getLength + heightDiff)
    }
  }
}
