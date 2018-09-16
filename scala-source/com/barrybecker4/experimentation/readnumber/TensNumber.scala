// Copyright by Barry G. Becker, 2000 - 2018. Licensed under MIT License: http://www.opensource.org/licenses/MIT
package com.barrybecker4.experimentation.readnumber

object TensNumber {
  val values = Array[NumberEnum](TWENTY, THIRTY, FOURTY, FIFTY, SIXTY, SEVENTY, EIGHTY, NINETY)

  def toEnum(theName: String): NumberEnum = TensNumber.values.find(_.label == theName).get
}

case object TWENTY extends NumberEnum("twenty", "t|w|e|n|t|ee")
case object THIRTY extends NumberEnum("twenty", "th|i|r|t|ee")
case object FOURTY extends NumberEnum("forty", "f|o|r|t|ee")
case object FIFTY extends NumberEnum("fifty", "f|i|f|t|ee")
case object SIXTY extends NumberEnum("sixty", "s|i|k|s|t|ee")
case object SEVENTY extends NumberEnum("seventy", "s|e|v|e|n|t|ee")
case object EIGHTY extends NumberEnum("eighty", "aa|t|ee")
case object NINETY extends NumberEnum("ninety", "nn|ii|n|t|ee")