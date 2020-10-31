package com.barrybecker4.experimentation.prisonorsandswitch.simulation.strategies

import com.barrybecker4.experimentation.prisonorsandswitch.simulation.{Prisoner, RoomState}


class FeelingLuckyStrategy(numPrisoners: Int) extends PrisonerStrategy(numPrisoners) {

  override def decideNewSwitchState(prisoner: Prisoner, state: RoomState): RoomState = {

    val dayInBlock = state.dayCount % numPrisoners
    val isDayOneOfBlock = dayInBlock == 1
    val blockStartDay = state.dayCount - dayInBlock + 1
    val isFirstBlock = state.dayCount <= numPrisoners
    val isFirstTimeVisitingThisBlock = prisoner.lastVisitedDay >= blockStartDay
    var hasEveryoneVisited = false

    val newSwitchState =
      if (isDayOneOfBlock) {
        if (!state.switchState) true
        else if (!isFirstBlock) {
          hasEveryoneVisited = true
          true
        }
        else state.switchState
      } else {
        if (isFirstTimeVisitingThisBlock) {
          prisoner.visitCount = 0
          state.switchState
        }
        else if (prisoner.visitCount == 1) false
        else state.switchState
      }

    prisoner.lastVisitedDay = state.dayCount
    prisoner.visitCount += 1

    state.nextState(newSwitchState, hasEveryoneVisited)
  }
}
