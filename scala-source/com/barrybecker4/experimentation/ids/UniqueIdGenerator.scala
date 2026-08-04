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
object UniqueIdGenerator:

  /** Number of ids to generate  */
  private val NUM_IDS = 3530
  private val NUM_DIGITS_IN_ID = 9
  private val USE_DASHES = true

  /* Interval between dashes.  If 3, then id will be something like XXX-XXX-XXX-X   */
  private val DASH_INTERVAL = 3
  private val DASH = "-"
  private val LOG10 = Math.log(10.0)
  private val RANDOM = new Random(30556)

  def main(args: Array[String]): Unit =
    var idSet = Set.empty[Long]
    while idSet.size < NUM_IDS do idSet = tryAddUniqueId(idSet)

  private def tryAddUniqueId(idSet: Set[Long]): Set[Long] =
    val idNum = getRandomNumber(NUM_DIGITS_IN_ID)
    if idSet.contains(idNum) then idSet
    else
      val formatted = formatId(idNum, NUM_DIGITS_IN_ID, USE_DASHES)
      println(formatted)
      idSet + idNum

  /** Package-visible for tests: pad and optionally dash an id number. */
  private[ids] def formatId(idNum: Long, numDigits: Int, useDashes: Boolean): String =
    val numLeadingZeros = numDigits - Math.ceil(Math.log(idNum.toDouble + 1) / LOG10).toInt
    val padded = "0" * numLeadingZeros + idNum.toString
    if useDashes then addDashes(padded, numDigits) else padded

  private[ids] def addDashes(id: String, numDigits: Int = NUM_DIGITS_IN_ID): String =
    val numDashes = (numDigits - 1) / DASH_INTERVAL
    var j = numDashes
    var newId = id
    while j > 0 do
      val pos = j * DASH_INTERVAL
      newId = newId.substring(0, pos) + DASH + newId.substring(pos)
      j -= 1
    newId

  /** @return a number between 1 and pow(10, NUM_DIGITS)-1 */
  private[ids] def getRandomNumber(numDigits: Int, random: Random = RANDOM): Long =
    Math.floor((Math.pow(10, numDigits) - 1.0) * random.nextDouble).toLong + 1
