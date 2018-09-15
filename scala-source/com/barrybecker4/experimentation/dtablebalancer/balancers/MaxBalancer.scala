package com.barrybecker4.experimentation.dtablebalancer.balancers

import com.barrybecker4.experimentation.dtablebalancer.DimensionMeta
import com.barrybecker4.experimentation.dtablebalancer.Table


object MaxBalancer {
  /** never less than this many pixels */
  private val MIN_DIM = 1
}

/**
  * The approach here is to set the row or column height/width to the average of the mean and max values normalized
  * by the overall width/height.
  * @author Barry Becker
  */
class MaxBalancer extends Balancer {
  override def doBalancing(table: Table): Unit = {
    //val normScale = table.getNormalizationScale
    val newWidths = new Array[Double](table.getSize)
    val newHeights = new Array[Double](table.getSize)
    var totalWidth: Double = 0
    var totalHeight: Double = 0
    var i = 0
    while (i < table.getSize) {
      var meta = table.getColMeta(i)
      newWidths(i) = Math.sqrt(meta.getMax)
      totalWidth += newWidths(i)
      meta = table.getRowMeta(i)
      newHeights(i) = Math.sqrt(meta.getMax)
      totalHeight += newHeights(i)
      i += 1
    }

    i = 0
    while (i < table.getSize) {
      val newWidth = Math.max(MaxBalancer.MIN_DIM, table.getWidth * newWidths(i) / totalWidth)
      table.getColMeta(i).setLength(newWidth)
      val newHeight = Math.max(MaxBalancer.MIN_DIM, table.getHeight * newHeights(i) / totalHeight)
      table.getRowMeta(i).setLength(newHeight)
      i += 1
    }
    finalAdjust(table)
    table.updateMetaData()
  }
}
