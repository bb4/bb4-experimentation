package com.barrybecker4.experimentation.puzzlers.prisonorsandswitch.simulation.strategies

import com.barrybecker4.experimentation.puzzlers.prisonorsandswitch.simulation.{Prisoner, RoomState}

abstract class PrisonerStrategy(val numPrisoners: Int) {

  def decideNewSwitchState(prisoner: Prisoner, state: RoomState): RoomState

}
