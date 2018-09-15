package com.barrybecker4.experimentation.euler

import com.barrybecker4.common.math.MathUtil

/**
  * @author Barry Becker
  */
object LatticePaths extends App {
  println("Num lattice paths for 20, 20 grid is " + new LatticePaths().getNumPaths(20, 20))
}

class LatticePaths {
  def getNumPaths(a: Int, b: Int): Long = MathUtil.combination(2 * a, b).longValue
}
