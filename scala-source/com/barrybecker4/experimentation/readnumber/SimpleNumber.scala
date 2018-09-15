// Copyright by Barry G. Becker, 2000 - 2018. Licensed under MIT License: http://www.opensource.org/licenses/MIT
package com.barrybecker4.experimentation.readnumber

object SimpleNumber {
  val values = Array(
    ONE, TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, TEN, ELEVEN,
    TWELVE, THIRTEEN, FOURTEEN, FIFTEEN, SIXTEEN, SEVENTEEN, EIGHTEEN, NINETEEN
  )

  def toEnum(theName: String): NumberEnum = TensNumber.values.find(_.label == theName).get
}

case object ONE extends NumberEnum("one", "w|o|n")
case object TWO extends NumberEnum("two", "t|ou|ou")
case object THREE extends NumberEnum("three", "th|r|ee")
case object FOUR extends NumberEnum("four", "f|or")
case object FIVE extends NumberEnum("five", "f|ii|v")
case object SIX extends NumberEnum("six", "s|i|k|s")
case object SEVEN extends NumberEnum("seven", "s|e|v|e|n")
case object EIGHT extends NumberEnum("eight", "aa|t")
case object NINE extends NumberEnum("nine", "nn|ii|n")
case object TEN extends NumberEnum("ten", "t|e|n")
case object ELEVEN extends NumberEnum("eleven", "e|l|e|v|e|n")
case object TWELVE extends NumberEnum("twelve", "t|w|e|l|v")
case object THIRTEEN extends NumberEnum("thirteen", "th|i|r|t|ee|ee|n")
case object FOURTEEN extends NumberEnum("fourteen", "f|or|t|ee|n")
case object FIFTEEN extends NumberEnum("fifteen", "f|f|i|f|v|t|ee|n")
case object SIXTEEN extends NumberEnum("sixteen", "s|i|k|s|t|ee|n")
case object SEVENTEEN extends NumberEnum("seventeen", "s|e|v|e|n|t|ee|n")
case object EIGHTEEN extends NumberEnum("eighteen", "aa|t|ee|n")
case object NINETEEN extends NumberEnum("nineteen", "nn|ii|n|t|ee|n")

case object HUNDRED extends NumberEnum("hundred", "h|u|n|d|r|e|d")