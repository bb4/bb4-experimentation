package com.barrybecker4.experimentation.givechange

import scala.collection.mutable


/**
  * Map from coin to number of coins. Represents a collection of coins of different types.
  *
  * @author Barry Becker
  */
class CoinMap  {

  private var m = Map[Coin, Int]()

  def add(coin: Coin, number: Int): Unit = {
    if (m.contains(coin)) m +=coin -> (m(coin) + number)
    else m += coin -> number
  }

  def remove(coin: Coin, number: Int): Unit = {
    assert(m(coin) >= number)
    m += coin -> (m(coin) - number)
  }

  override def toString: String = {
    /*
    Coin.VALUES.filter(coin => m.contains(coin)).map(coin => {
      val num = m(coin)
      if (num > 0)
        s"$num  ${if (this(coin) == 1) coin.name else coin.pluralName}"
    }).mkString("   ") */

    val bldr = new StringBuilder
    for (i <- Coin.VALUES.length - 1 to 0 by -1) {
      val coin: Coin = Coin.VALUES(i)
      if (m.contains(coin)) {
        val num = m(coin)
        if (num > 0) {
          val c = if (num == 1) coin.name else coin.pluralName
          bldr.append(num).append(" ").append(c).append("   ")
        }
      }
    }
    bldr.toString
  }
}
