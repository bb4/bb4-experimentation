/** Copyright by Barry G. Becker, 2000-2011. Licensed under MIT License: http://www.opensource.org/licenses/MIT  */
package com.barrybecker4.experimentation.ids

import java.util.Random

/**
  * Generators random N digit id strings.
  * Relies on the fact that the space of possible ids is much larger than
  * the actual number of unique ids needed. If this is not the case, it could be slow.
  *
  * @author Barry Becker
  */
object UniqueIdGenerator extends App {

  /** Number of ids to generate  */
  private val NUM_IDS = 3530
  private val NUM_DIGITS_IN_ID = 9
  private val USE_DASHES = true

  /* Interval between dashes.  If 3, then id will be something like XXX-XXX-XXX-X   */
  private val DASH_INTERVAL = 3
  private val DASH = "-"
  private val LOG10 = Math.log(10.0)
  private var idSet = Set[Long](NUM_IDS)
  private val RANDOM = new Random(30556)

  while (idSet.size < NUM_IDS) printRandomId()

  private def printRandomId(): Unit = {
    val idNum = getRandomNumber(NUM_DIGITS_IN_ID)
    val numLeadingZeros = NUM_DIGITS_IN_ID - Math.ceil(Math.log(idNum.toDouble + 1) / LOG10).toInt
    if (!idSet.contains(idNum)) {
      var id = idNum.toString
      var i = 0
      while (i < numLeadingZeros) {
        id = "0" + id
        i += 1
      }
      if (USE_DASHES) id = addDashes(id)
      println(id)
      idSet += idNum
    }
  }

  private def addDashes(id: String) = {
    val numDashes = (NUM_DIGITS_IN_ID - 1) / DASH_INTERVAL
    var j = numDashes
    var newId = id
    while (j > 0) {
      val pos = j * DASH_INTERVAL
      newId = newId.substring(0, pos) + DASH + newId.substring(pos)
      j -= 1
    }
    newId
  }

  /** @return a number between 1 and pow(10, NUM_DIGITS)-1 */
  private def getRandomNumber(numDigits: Int) =
    Math.floor((Math.pow(10, numDigits) - 1.0) * RANDOM.nextDouble).toLong + 1
}
