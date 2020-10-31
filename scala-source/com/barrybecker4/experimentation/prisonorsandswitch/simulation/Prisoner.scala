package com.barrybecker4.experimentation.prisonorsandswitch.simulation

import com.barrybecker4.experimentation.prisonorsandswitch.simulation.strategies.PrisonerStrategy
import Prisoner.NEVER


object Prisoner {
  val NEVER: Int = -1
}

class Prisoner(val id: Int, val strategy: PrisonerStrategy) {

  var lastVisitedDay: Int = NEVER
  var visitCount: Int = 0

  def decideNewSwitchState(state: RoomState): RoomState = {
      strategy.decideNewSwitchState(this, state)
  }
}
