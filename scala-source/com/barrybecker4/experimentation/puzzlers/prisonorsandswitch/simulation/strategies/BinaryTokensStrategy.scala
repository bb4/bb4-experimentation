package com.barrybecker4.experimentation.puzzlers.prisonorsandswitch.simulation.strategies

import com.barrybecker4.experimentation.puzzlers.prisonorsandswitch.simulation.{Prisoner, RoomState}
import BinaryTokensStrategy._


object BinaryTokensStrategy {

  private val LOG2 = Math.log10(2)

  /** T = log2 (n * n (ln(n) + ln(ln(n)) ))
    */
  def computeT(n: Int): Int =
    Math.floor(Math.log10(n * nlnFun(n)) / LOG2).toInt

  /*
   * Pk = 2m where m is
   * floor(
   *     (k % T) / (n (ln(n) + ln(ln(n)) ))
   * )
   * and n = number of prisoners
   */
  def computeP(k: Int, n: Int, T: Int): Int = {
    val exp = Math.floor((k % T) / nlnFun(n)).toInt
    Math.pow(2, exp).toInt
  }


  def nlnFun(n: Int): Double = {
    val ln_n = Math.log(n)
    n * (ln_n + Math.log(ln_n))
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

  var count = 1

  override def decideNewSwitchState(prisoner: Prisoner, state: RoomState): RoomState = {

    // WIP
    var hasEveryoneVisited = false
    val T = computeT(numPrisoners)

    if (prisoner.counter == 0) {
      prisoner.counter = 1
    }
    val tBinary = prisoner.counter.toBinaryString

    val newSwitchState =
      if (prisoner.id == 1) { // the counter
        if (!state.switchState) false
        else {
          count += 1
          if (count == numPrisoners)
            hasEveryoneVisited = true
          false
        }
      } else { // not counter
        if (!state.switchState) {
          val newState = prisoner.counter == 0
          prisoner.counter += 1
          newState
        } else true
      }

    state.nextState(newSwitchState, hasEveryoneVisited)
  }

}
