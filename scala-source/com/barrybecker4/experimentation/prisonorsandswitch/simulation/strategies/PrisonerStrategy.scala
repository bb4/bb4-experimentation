package com.barrybecker4.experimentation.prisonorsandswitch.simulation.strategies

import com.barrybecker4.experimentation.prisonorsandswitch.simulation.{Prisoner, RoomState}

abstract class PrisonerStrategy(val numPrisoners: Int) {

  def decideNewSwitchState(prisoner: Prisoner, state: RoomState): RoomState

}
