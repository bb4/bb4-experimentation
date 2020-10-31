package com.barrybecker4.experimentation.puzzlers.prisonorsandswitch.simulation

import com.barrybecker4.experimentation.puzzlers.prisonorsandswitch.simulation.strategies.PrisonerStrategy
import scala.util.Random



case class PrisonersAndSwitchSimulation(strategy: PrisonerStrategy, rnd: Random = new Random(0)) {

  private val prisoners: Array[Prisoner] =
    Array.range(1, strategy.numPrisoners + 1).map(i => new Prisoner(i, strategy))

  def run(): Int = {

    var roomState = RoomState(1, switchState = false)
    val startTime = System.currentTimeMillis()

    while (!roomState.hasEveryoneVisited) {
      val prisoner = prisoners(rnd.nextInt(prisoners.length))
      roomState = prisoner.decideNewSwitchState(roomState)
      if (roomState.dayCount % strategy.numPrisoners == 0) {
        //println(roomState.dayCount + " days have elapsed")
      }
    }

    val elapsedTime = (System.currentTimeMillis() - startTime).toDouble / 1000
    println("Completed in " + (roomState.dayCount - 1) + " days (" + elapsedTime + " seconds)")
    roomState.dayCount
  }
}
