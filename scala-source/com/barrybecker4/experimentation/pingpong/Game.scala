/** Copyright by Barry G. Becker, 2000-2018. Licensed under MIT License: http://www.opensource.org/licenses/MIT  */
package com.barrybecker4.experimentation.pingpong

import com.barrybecker4.common.concurrency.ThreadUtil


/**
  * Ping pong game that uses multiple threads.
  * Its a good example of using wait and notify.
  * see http://www.javaworld.com/jw-04-1996/jw-04-synch.html?page=1
  */
object Game extends App {
    val PLAYERS = Array("Alice", "Barry", "Bob", "Brian", "Joe", "Duy", "Shanna")
    new Game().startGame(PLAYERS, 8000)
}

class Game private() {
  private val table = new PingPongTable

  private def startGame(players: Array[String], duration: Int): Unit = {
    val numPlayers = players.length
    var i = 0
    while (i < numPlayers) {
      val nextPlayer = (i + 1) % numPlayers
      val t = new Thread(new Player(players(nextPlayer), table), players(i))
      t.start()
      i += 1
    }
    ThreadUtil.sleep(duration)
    table.stop()
  }
}

