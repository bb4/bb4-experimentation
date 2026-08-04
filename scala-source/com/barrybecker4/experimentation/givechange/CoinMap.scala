package com.barrybecker4.experimentation.givechange

/**
  * Map from coin to number of coins. Represents a collection of coins of different types.
  *
  * @author Barry Becker
  */
class CoinMap:

  private var counts = Map.empty[Coin, Int]

  def add(coin: Coin, number: Int): Unit =
    counts += coin -> (counts.getOrElse(coin, 0) + number)

  def remove(coin: Coin, number: Int): Unit =
    assert(counts(coin) >= number)
    counts += coin -> (counts(coin) - number)

  override def toString: String =
    val bldr = new StringBuilder
    for i <- Coin.VALUES.length - 1 to 0 by -1 do
      val coin = Coin.VALUES(i)
      val num = counts.getOrElse(coin, 0)
      if num > 0 then
        val c = if num == 1 then coin.name else coin.pluralName
        bldr.append(num).append(" ").append(c).append("   ")
    bldr.toString
