/** Copyright by Barry G. Becker, 2000-2011. Licensed under MIT License: http://www.opensource.org/licenses/MIT  */
package com.barrybecker4.experimentation.pingpong

import com.barrybecker4.common.concurrency.ThreadUtil


/**
  * Table at which the ping pong players play.
  * see http://www.javaworld.com/jw-04-1996/jw-04-synch.html?page=1
  */
object PingPongTable {
  private val DONE = "DONE"
  private val TIMEOUT_DURATION = 1500
}

class PingPongTable {
  /** state variable identifying whose turn it is. */
  private var playerToPlay: Option[String] = None

  /**
    * One player hits the ball to his opponent.
    * @param opponent opposite player that we are hitting the ball to.
    * @return true if we should keep playing, else terminate.
    */
  def hit(opponent: String): Boolean = {
    val currentPlayer = Thread.currentThread.getName
    // Initialize with whichever thread gets here first
    if (playerToPlay.isEmpty) {
      playerToPlay = Some(currentPlayer)
      return true
    }
    if (playerToPlay.get.compareTo(PingPongTable.DONE) == 0) return false
    if (opponent.compareTo(PingPongTable.DONE) == 0) {
      playerToPlay = Some(PingPongTable.DONE)
      synchronized{notifyAll()}
      return false
    }
    if (currentPlayer == playerToPlay.get) {
      println("PING!  " + currentPlayer + " hit it to " + opponent)
      playerToPlay = Some(opponent)
      // random pause before moving on
      ThreadUtil.sleep((500 * Math.random).toInt)
      synchronized{ notifyAll() }
    }
    else {
      val t1 = System.currentTimeMillis
      synchronized(wait(PingPongTable.TIMEOUT_DURATION))
      if ((System.currentTimeMillis - t1) > PingPongTable.TIMEOUT_DURATION)
        println("*** TIMEOUT! " + currentPlayer + " is waiting for " + playerToPlay + " to play.")
    }
    true
  }

  /** Stop playing ping pong. Causes all players to quit their threads. */
  def stop(): Unit = hit(PingPongTable.DONE)
}
