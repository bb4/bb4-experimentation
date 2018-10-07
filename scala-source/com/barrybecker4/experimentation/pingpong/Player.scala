/** Copyright by Barry G. Becker, 2000-2011. Licensed under MIT License: http://www.opensource.org/licenses/MIT  */
package com.barrybecker4.experimentation.pingpong

/**
  * @param table the table where the players play
  */
class Player (var opponent: String, var table: PingPongTable) extends Runnable {

  override def run(): Unit = {
    while (table.hit(opponent)) {
    }
  }
}