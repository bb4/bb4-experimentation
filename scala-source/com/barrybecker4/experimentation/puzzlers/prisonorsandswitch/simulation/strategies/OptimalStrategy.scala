package com.barrybecker4.experimentation.puzzlers.prisonorsandswitch.simulation.strategies

import com.barrybecker4.experimentation.puzzlers.prisonorsandswitch.simulation.{Prisoner, RoomState}

/**
  * This strategy "cheats" in order to answer the question, "when exactly has everyone been in the
  * room at least once?".
  */
class OptimalStrategy(numPrisoners: Int) extends PrisonerStrategy(numPrisoners) {

  private var visitedPlayers = Set[Int]()

  override def decideNewSwitchState(prisoner: Prisoner, state: RoomState): RoomState = {

    visitedPlayers += prisoner.id
    val hasEveryoneVisited = visitedPlayers.size == numPrisoners

    state.nextState(newSwitchState = true, hasEveryoneVisited = hasEveryoneVisited)
  }
}
