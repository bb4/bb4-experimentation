package com.barrybecker4.experimentation.puzzlers.prisonorsandswitch.simulation

import com.barrybecker4.experimentation.puzzlers.prisonorsandswitch.simulation.strategies.PrisonerStrategy
import Prisoner.NEVER


object Prisoner {
  val NEVER: Int = -1
}

class Prisoner(val id: Int, val strategy: PrisonerStrategy) {

  var lastVisitedDay: Int = NEVER

  // a generic counter whose purpose varies with the strategy
  var counter: Int = 0

  def decideNewSwitchState(state: RoomState): RoomState = {
      strategy.decideNewSwitchState(this, state)
  }
}
