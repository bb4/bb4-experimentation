// Copyright by Barry G. Becker, 2015 - 2018. Licensed under MIT License: http://www.opensource.org/licenses/MIT
package com.barrybecker4.experimentation.dtablebalancer

import com.barrybecker4.common.format.FormatUtil


/**
  * Represents a decision table graph.
  * We want to optimize the area of each cell that is filled with a bar chart coloring
  * by adjusting the width and height of cells. Each row and column have a constant height or width,
  * but they are can all independently adjustable.
  *
  * @author Barry Becker
  */
object Table {

  private def makeCopy(g: Array[Array[Int]]) = {
    val len = g.length
    val gridCopy = Array.ofDim[Int](len, len)
    for (i <- 0 until len) {
      System.arraycopy(g(i), 0, gridCopy(i), 0, len)
    }
    gridCopy
  }
}

/**
  * Constructor.
  * Initially all the row heights and column widths are equal sized.
  * @param data initial weights in each cell
  */
class Table(val data: Array[Array[Int]], var width: Int, var height: Int) {
  private val grid = Table.makeCopy(data)
  private val size = grid.length
  private var rowMeta: Array[DimensionMeta] = _
  private var colMeta: Array[DimensionMeta] = _

  /** The amount of area covered by each unit of grid value.
    * It is the inverse of the largest grid value to cell area ratio.
    */
  private var normalizationScale: Double = 0.0

  /** Ratio of colored area in the table. A value between 0 and 1.
    * The thing to optimize - ratio of painted are to total area. Coverage is optimal when this is 1.
    */
  private var overallCoverage = 0.0

  initializeMeta()
  updateMetaData()

  /** Copy constructor */
  def this(table: Table) {
    this(table.grid, table.width, table.height)
  }

  def getRowMeta(i: Int): DimensionMeta = rowMeta(i)
  def getColMeta(i: Int): DimensionMeta = colMeta(i)
  def getNormalizationScale: Double = normalizationScale
  def getSize: Int = size
  def getOverallCoverage: Double = overallCoverage
  def getWidth: Int = width
  def getHeight: Int = height

  private def initializeMeta(): Unit = {
    rowMeta = Array.ofDim[DimensionMeta](size)
    colMeta = Array.ofDim[DimensionMeta](size)
    val w = this.width.toDouble / size
    val h = this.height.toDouble / size
    for (i <- grid.indices) {
      rowMeta(i) = new DimensionMeta(h)
      colMeta(i) = new DimensionMeta(w)
    }
  }

  /** this needs to be called any time the meta data is modified */
  def updateMetaData(): Unit = {

    for (i <- 0 until size) {
      updateRowMeta(i)
      updateColMeta(i)
    }
    var grandTotal: Double = 0
    var max: Double = 0
    var largestValueToGridAreaRatio: Double = 0

    for (i <- 0 until size) {
      grandTotal += rowMeta(i).getTotal
      if (rowMeta(i).getMax > max) max = rowMeta(i).getMax
      for (j <- 0 until size) {
        val cellArea = rowMeta(i).getLength * colMeta(j).getLength
        val valueToGridAreaRatio = grid(i)(j).toDouble / cellArea
        //System.out.println("valueToGridRat=" + valueToGridAreaRatio);
        if (valueToGridAreaRatio > largestValueToGridAreaRatio)
          largestValueToGridAreaRatio = valueToGridAreaRatio
      }
    }
    normalizationScale = 1.0 / largestValueToGridAreaRatio
    println("normScale=" + normalizationScale)
    overallCoverage = grandTotal.toDouble * normalizationScale / (width * height)
    TableValidator.verifyDimensions(this)
  }

  private def updateRowMeta(i: Int): Unit = {
    val row = new Array[Int](size)
    System.arraycopy(grid(i), 0, row, 0, size)
    updateDimMeta(row, rowMeta(i))
  }

  private def updateColMeta(j: Int): Unit = {
    val col = new Array[Int](size)

    for (i <- 0 until size)
      col(i) = grid(i)(j)

    updateDimMeta(col, colMeta(j))
  }

  private def updateDimMeta(dim: Array[Int], meta: DimensionMeta): Unit = {
    var min = Integer.MAX_VALUE
    var max = 0
    var total = 0

    for (j <- 0 until size) {
      val v = dim(j)
      total += v
      if (v < min) min = v
      if (v > max) max = v
    }
    meta.update(max, total)
  }

  /** @return proportion of cell coverage  */
  private def getCellCoverage(i: Int, j: Int) = {
    val area = rowMeta(i).getLength * colMeta(j).getLength
    grid(i)(j) * normalizationScale / area
  }

  /** @return string form - the proportion that each table cell is filled and an overall fill proportion. */
  override def toString: String = {
    var s = ""

    for (i <- 0 until size) {
      for (j <- 0 until size)
        s += FormatUtil.formatNumber(getCellCoverage(i, j)) + "\t"
      s += "\n"
    }
    s += "Overall coverage: " + FormatUtil.formatNumber(overallCoverage)
    s
  }
}