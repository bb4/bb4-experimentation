package com.barrybecker4.experimentation.prisonorsandswitch.simulation.strategies

import com.barrybecker4.experimentation.prisonorsandswitch.simulation.{Prisoner, RoomState}

/**
  * The first prisoner will be the counter
  */
class OneCounterStrategy(numPrisoners: Int) extends PrisonerStrategy(numPrisoners) {

  var count = 1

  override def decideNewSwitchState(prisoner: Prisoner, state: RoomState): RoomState = {

    var hasEveryoneVisited = false

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
          val newState = prisoner.visitCount == 0
          prisoner.visitCount += 1
          newState
        } else true
      }

    state.nextState(newSwitchState, hasEveryoneVisited)
  }
}
