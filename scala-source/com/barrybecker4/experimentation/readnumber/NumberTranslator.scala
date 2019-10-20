// Copyright by Barry G. Becker, 2000 - 2018. Licensed under MIT License: http://www.opensource.org/licenses/MIT
package com.barrybecker4.experimentation.readnumber

import java.math.BigInteger
import NumberTranslator.Type.{ENGLISH, BRITISH, PHONETIC}

/**
  * Translates a big integer number into english or spoken word.
  * @author Barry Becker
  */
object NumberTranslator {
  private val ZERO = new BigInteger("0")
  private val ONE_THOUSAND = new BigInteger("1000")
  /** biggest number we will allow (vigintillion) */
  private val BIGGEST = new BigInteger("999999999999999999999999999999999999999999999999999999999999999")

  /** British uses "and "as in "one hundred and 15", instead of "one hundred 15".    */
  object Type extends Enumeration {
    type Type = Value
    val ENGLISH, BRITISH, PHONETIC = Value
  }
}

class NumberTranslator() {

  /** @param number number to translate to english.
    * @return the number written out long hand.
    */
  def translateToEnglish(number: BigInteger): String = translateNumber(number, NumberTranslator.Type.ENGLISH)
  def translateToBritish(number: BigInteger): String = translateNumber(number, NumberTranslator.Type.BRITISH)

  /** @param number number to translate phonetically.
    * @return the number written as a series of pipe delimited allophones.
    */
  def translateToPhonetic(number: BigInteger): String = translateNumber(number, NumberTranslator.Type.PHONETIC)

  /** @param number the number to translate.
    * @return the english form of the number
    */
  private def translateNumber(number: BigInteger, theType: NumberTranslator.Type.Type): String = {

    if (number.compareTo(NumberTranslator.BIGGEST) > 0)
      return theType match {
        case ENGLISH | BRITISH => "I give up. That number is too big even for me."
        case PHONETIC => "ii g|i|v u|p|. th|a|t n|u|m|b|e|r i|s t|oo b|i|g| ee|v|e|n f|o|r m|ee|."
      }

    var result = getTextForGroup(number.mod(NumberTranslator.ONE_THOUSAND).intValue, theType)
    var n = number.divide(NumberTranslator.ONE_THOUSAND)
    var group = 0
    while (n.compareTo(NumberTranslator.ZERO) > 0) {
      val groupVal = n.mod(NumberTranslator.ONE_THOUSAND).intValue
      if (groupVal != 0) {
        val res = result
        val grouping = translate(GroupNumber.values(group), theType)
        result = getTextForGroup(groupVal, theType) + ' ' + grouping
        if ("" != res) {
          val engOrBrit = (theType eq NumberTranslator.Type.ENGLISH) || (theType eq NumberTranslator.Type.BRITISH)
          val sep = if (engOrBrit) ",\n" else "|, "
          result += sep + res
        }
      }
      n = n.divide(NumberTranslator.ONE_THOUSAND)
      group += 1
    }
    result
  }

  private def getTextForGroup(number: Int, theType: NumberTranslator.Type.Type): String = {
    assert(number >= 0 && number < 1000)
    if (number == 0) return ""
    if (number < 20) {
      val num = SimpleNumber.values(number - 1)
      translate(num, theType)
    }
    else if (number < 100) {
      val tens = number / 10
      val num = TensNumber.values(tens - 2)
      val tensPart = translate(num, theType)
      tensPart + ' ' + getTextForGroup(number - 10 * tens, theType)
    }
    else {
      val hundreds = number / 100
      val hundredsPart = translate(SimpleNumber.values(hundreds - 1), theType)

      val remainder = number - 100 * hundreds
      val hundred = theType match {
        case ENGLISH => HUNDRED.label
        case BRITISH => HUNDRED.label + (if (remainder == 0) "" else " and")
        case PHONETIC => HUNDRED.pronunciation
      }
      s" $hundredsPart $hundred ${getTextForGroup(remainder, theType)}"
    }
  }

  private def translate(number: NumberEnum, theType: NumberTranslator.Type.Type) = {
    theType match {
      case ENGLISH | BRITISH => number.toString
      case PHONETIC => number.pronunciation
    }
  }
}
