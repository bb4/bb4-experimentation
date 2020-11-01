package com.barrybecker4.experimentation.puzzlers.prisonorsandswitch.simulation.strategies

import com.barrybecker4.experimentation.puzzlers.prisonorsandswitch.simulation.{Prisoner, RoomState}
import BinaryTokensStrategy._


object BinaryTokensStrategy {

  private val LOG2 = Math.log10(2)
  private def log2(x: Double): Double = Math.log10(x) / LOG2
  private var NLNN_FUN_CACHE = Map[Int, Double]()
  private var T_CACHE = Map[Int, Int]()

  /** T = log2(n) * ( ln(n) + ln(ln(n)) )  */
  def computeT(n: Int): Int = {
    if (T_CACHE.contains(n)) {
      T_CACHE(n)
    } else {
      val result = Math.floor(log2(n) * nlnFun(n)).toInt
      T_CACHE += n -> result
      result
    }
  }

  def computeM(k: Int, n: Int, T: Int): Int =
    Math.floor((k % T) / nlnFun(n)).toInt

  /** Pk = 2m where m is
   * floor(
   *     (k % T) / (ln(n) + ln(ln(n)) )
   * )
   * and n = number of prisoners
   * The paper seems to have a typo. It says that the denominator is
    * (n (ln(n) + ln(ln(n)) ), but I think it should be (ln(n) + ln(ln(n))
   */
  def computeP(k: Int, n: Int, T: Int): Int =
    Math.pow(2, computeM(k, n, T)).toInt

  // memoized for performance
  def nlnFun(n: Int): Double = {
    if (NLNN_FUN_CACHE.contains(n))
      NLNN_FUN_CACHE(n)
    else {
      val ln_n = Math.log(n)
      val result = Math.round(ln_n + Math.log(ln_n)) // floor?
      // Math.round(n * (ln_n + Math.log(ln_n))) // the paper says this, but I think it is wrong
      NLNN_FUN_CACHE += n -> result
      result
    }
  }

}

/**
  * Each prisoner keeps an integer in their head; call it T. Initialize it to T = 1.
  * • Let Tm denote the mth bit of T expressed in binary.
  * • Upon entering the room on day k, where Pk = 2{^literal ^}m for some m, go through four steps:
  *   (1) If the bulb is ON, set T += Pk−1, and turn it OFF.
  *   (2) If T >= n, declare victory.
  *   (3) If Tm = 1, turn the bulb ON, and set T -= Pk.
  *   (4) Else, if Tm = 0, leave the bulb OFF and do nothing.
  */
class BinaryTokensStrategy(numPrisoners: Int) extends PrisonerStrategy(numPrisoners) {

  override def decideNewSwitchState(prisoner: Prisoner, state: RoomState): RoomState = {

    var hasEveryoneVisited = false
    var newSwitchState = false
    val TT = computeT(numPrisoners)

    if (prisoner.counter == 0) {
      prisoner.counter = 1
    }
    val Tbinary = prisoner.counter.toBinaryString
    val m = computeM(state.dayCount, numPrisoners, TT)
    val mm1 = computeM(state.dayCount - 1, numPrisoners, TT)
    val Tm = if (m >= Tbinary.length) 0 else Tbinary.reverse.charAt(m).toString.toInt
    //println("prisoner" + prisoner.id +" T = " + prisoner.counter + " Tbinary = " + Tbinary + " T(" + m + ") = " + Tm + " ")
    val Pk = Math.pow(2, m).toInt
    val Pkm1 = Math.pow(2, mm1).toInt
    //println("bulb is worth " + Pk + " on day " + state.dayCount)

    if (state.switchState) { // switch is on
      prisoner.counter += Pkm1
      newSwitchState = false
    }
    if (prisoner.counter >= numPrisoners) {
      hasEveryoneVisited = true
    }
    if (Tm == 1) {
      newSwitchState = true
      prisoner.counter -= Pk
    }
    //  Else, if Tm = 0, leave the bulb OFF and do nothing.

    state.nextState(newSwitchState, hasEveryoneVisited)
  }

}
