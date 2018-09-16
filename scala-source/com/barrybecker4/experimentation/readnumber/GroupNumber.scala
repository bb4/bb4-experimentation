// Copyright by Barry G. Becker, 2000 - 2018. Licensed under MIT License: http://www.opensource.org/licenses/MIT
package com.barrybecker4.experimentation.readnumber

/**
  * Large number groups.
  * a VIGINTILLION, for example, is pow(10, 63).
  * @author Barry Becker
  */
object GroupNumber {
  val values = Array[NumberEnum](THOUSAND, MILLION, BILLION, TRILLION, QUADRILLION,
    PENTILLION, SEXTILLION, SEPTILLION, OCTILLION, NONILLION, DECILLION, UNDECILLION, DUODECILLION,
  TREDECILLION, QUATTUORDECILLION, QUINDECILLION, SEPTENDECILLION, OCTODECILLION, NOVEMDECILLION, VIGINTILLION)

  def toEnum(theName: String): NumberEnum = GroupNumber.values.find(_.label == theName).get
}

case object THOUSAND extends NumberEnum("thousand", "th|o|w|s|a|n|d")
case object MILLION extends NumberEnum("million", "m|i|ll|y|o|n")
case object BILLION extends NumberEnum("billion", "b|i|ll|y|o|n")
case object TRILLION extends NumberEnum("trillion", "t|r|i|ll|y|o|n")
case object QUADRILLION extends NumberEnum("quadrillion", "k|wh|a|dd|dd|r|i|l|y|o|n")
case object PENTILLION extends NumberEnum("pentillion", "p|e|n|t|t|i|ll|y|o|n")
case object SEXTILLION extends NumberEnum("sextillion", "s|e|k|s|t|i|ll|y|o|n")
case object SEPTILLION extends NumberEnum("septillion", "s|e|p|t|t|i|ll|y|o|n")
case object OCTILLION extends NumberEnum("octillion", "o|k|t|t|i|ll|y|o|n")
case object NONILLION extends NumberEnum("nonillion", "n|o|n|i|ll|y|o|n")
case object DECILLION extends NumberEnum("decillion", "d|e|s|s|i|ll|y|o|n")
case object UNDECILLION extends NumberEnum("undecillion", "u|n|dd|e|s|i|ll|y|o|n")
case object DUODECILLION extends NumberEnum("duodecillion", "d|ou|oo|d|e|s|i|ll|y|o|n")
case object TREDECILLION extends NumberEnum("tredecillion", "t|r|aa|d|e|s|i|ll|y|o|n")
case object QUATTUORDECILLION extends NumberEnum("quattuordecillion", "k|w|o|t|ou|oo|r|d|e|s|i|ll|y|o|n")
case object QUINDECILLION extends NumberEnum("quindecillion", "k|w|i|n|d|e|s|i|ll|y|o|n")
case object SEPTENDECILLION extends NumberEnum("septendecillion", "s|e|p|t|e|n|d|e|s|i|ll|y|o|n")
case object OCTODECILLION extends NumberEnum("octodecillion", "o|k|t|oo|d|e|s|i|ll|y|o|n")
case object NOVEMDECILLION extends NumberEnum("novemdecillion", "n|oo|v|e|m|d|e|s|i|ll|y|o|n")
case object VIGINTILLION extends NumberEnum("vigintillion", "v|i|j|i|n|t|i|ll|y|o|n")

/* add these some day
  unvigintillion
  dovigintillion
  trevigintillion
  quattuorvigintillion
  quinvigintillion
  sexvigintillion
  septenvigintillion
  octovigintillion
  novemvigintillion
  trigintillion
  untrigintillion
  dotrigintillion
  tretrigintillion
  quattuortrigintillion
  quintrigintillion
  sextrigintillion
  septentrigintillion
  octotrigintillion
  novemtrigintillion
    */