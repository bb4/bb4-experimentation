package com.barrybecker4.experimentation.givechange

object Coin {
  val VALUES = Array(PENNY, NICKEL, DIME, QUARTER, HALF_DOLLAR)
}

sealed abstract class Coin(val name: String, val pluralName: String, val worthInPennies: Int)

case object PENNY extends Coin("penny", "pennies", 1)
case object NICKEL extends Coin("nickel", "nickels", 5)
case object DIME extends Coin("dime", "dimes", 10)
case object QUARTER extends Coin("quarter", "quarters", 25)
case object HALF_DOLLAR extends Coin("half dollar", "half dollars", 50)

