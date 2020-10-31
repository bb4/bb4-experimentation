package com.barrybecker4.experimentation.prisonorsandswitch

import java.util.IntSummaryStatistics
import com.barrybecker4.common.util.Input
import com.barrybecker4.experimentation.prisonorsandswitch.simulation.PrisonersAndSwitchSimulation
import com.barrybecker4.experimentation.prisonorsandswitch.simulation.strategies.{FeelingLuckyStrategy, OneCounterStrategy, PrisonerStrategy}

import scala.util.Random

object PrisonersAndSwitchSimulator extends App {

  // specified a seed parameter to Random if you want repeatable results
  private val rnd = new Random()

  private val numPrisoners = Input.getLong("How many prisoners?[1 - 100]:", 1, 100).toInt
  private val numTrials = Input.getLong("How many trials should be run?[1 - 1000]:", 1, 1000).toInt
  private val strategyIdx =
      Input.getLong("Which strategy to use?\n[1: Feeling Lucky, 2:Counter]:", 1, 2).toInt

  val stats = runTrials(numTrials, strategyIdx)

  println("Summary statistics for " + numTrials + " trials of " + numPrisoners + " prisoners playing the game.")
  println(stats.toString)


  def runTrials(numTrials: Int, strategyIdx: Int): IntSummaryStatistics = {
    var numDaysResults = List[Int]()
    val stats = new IntSummaryStatistics()

    for (i <- 0 until numTrials) {

      val strategy = strategyIdx match {
        case 1 => new FeelingLuckyStrategy(numPrisoners)
        case 2 => new OneCounterStrategy(numPrisoners)
      }
      val simulation = PrisonersAndSwitchSimulation(strategy, rnd)

      val numDays = simulation.run()
      numDaysResults :+= numDays
      stats.accept(numDays)
    }
    stats
  }
}
