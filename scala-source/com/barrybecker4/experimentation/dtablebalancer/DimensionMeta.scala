// Copyright by Barry G. Becker, 2015 - 2018. Licensed under MIT License: http://www.opensource.org/licenses/MIT
package com.barrybecker4.experimentation.dtablebalancer

/**
  * @param length either the width or height for the dimension
  * @author Barry Becker
  */
class DimensionMeta private[dtablebalancer](var length: Double) {
  private var max = 0
  private var total: Int = 0

  def getMax: Int = max
  def getMean: Double = total.toDouble / length
  def getLength: Double = length
  def getTotal: Int = total

  def update(max: Int, sum: Int): Unit = {
    this.max = max
    this.total = sum
  }

  /** if you set this, then it is also necessary to call updateMeta on the table */
  def setLength(newLength: Double): Unit = {
    length = newLength
  }
}