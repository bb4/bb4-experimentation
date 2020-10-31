package com.barrybecker4.experimentation.puzzlers.prisonorsandswitch.simulation

case class RoomState(dayCount: Int, switchState: Boolean, hasEveryoneVisited: Boolean = false) {

  def nextState(newSwitchState: Boolean, hasEveryoneVisited: Boolean = false): RoomState =
    RoomState(dayCount + 1, newSwitchState, hasEveryoneVisited)

}
