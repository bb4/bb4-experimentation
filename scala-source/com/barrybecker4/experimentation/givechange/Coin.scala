package com.barrybecker4.experimentation.givechange

enum Coin(val name: String, val pluralName: String, val worthInPennies: Int):
  case PENNY extends Coin("penny", "pennies", 1)
  case NICKEL extends Coin("nickel", "nickels", 5)
  case DIME extends Coin("dime", "dimes", 10)
  case QUARTER extends Coin("quarter", "quarters", 25)
  case HALF_DOLLAR extends Coin("half dollar", "half dollars", 50)

object Coin:
  /** Coins in ascending order of worth (same order as the former case-object hierarchy). */
  val VALUES: Array[Coin] = Coin.values
