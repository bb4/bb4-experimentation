package com.barrybecker4.experimentation.puzzlers.prisonorsandswitch

import java.util.IntSummaryStatistics

import com.barrybecker4.common.util.Input
import com.barrybecker4.experimentation.puzzlers.prisonorsandswitch.simulation.PrisonersAndSwitchSimulation
import com.barrybecker4.experimentation.puzzlers.prisonorsandswitch.simulation.strategies.{BinaryTokensStrategy, FeelingLuckyStrategy, OneCounterStrategy, OptimalStrategy, PrisonerStrategy}

import scala.util.Random

/**
  * See https://www.ocf.berkeley.edu/~wwu/papers/100prisonersLightBulb.pdf
  * For a definition of the puzzle and several solutions with analysis.
  */
object PrisonersAndSwitchSimulator extends App {

  // specified a seed parameter to Random if you want repeatable results
  private val rnd = new Random()

  private val numPrisoners = Input.getLong("How many prisoners?[1 - 500]:", 1, 500).toInt
  private val numTrials = Input.getLong("How many trials should be run?[1 - 1000]:", 1, 1000).toInt
  private val strategyIdx =
      Input.getLong("Which strategy to use? " +
        "[\n  1: Feeling Lucky\n  2: Counter\n  3: Binary Tokens\n  4: Optimal\n]:", 1, 4).toInt

  val stats = runTrials(numTrials, strategyIdx)

  println("Summary statistics for " + numTrials + " trials of " + numPrisoners + " prisoners playing the game.")
  println(stats.toString)


  def runTrials(numTrials: Int, strategyIdx: Int): IntSummaryStatistics = {
    val stats = new IntSummaryStatistics()

    for (i <- 0 until numTrials) {

      val strategy = strategyIdx match {
        case 1 => new FeelingLuckyStrategy(numPrisoners)
        case 2 => new OneCounterStrategy(numPrisoners)
        case 3 => new BinaryTokensStrategy(numPrisoners)
        case 4 => new OptimalStrategy(numPrisoners)
      }
      val simulation = PrisonersAndSwitchSimulation(strategy, rnd)

      val numDays = simulation.run()
      stats.accept(numDays)
    }
    stats
  }
}
