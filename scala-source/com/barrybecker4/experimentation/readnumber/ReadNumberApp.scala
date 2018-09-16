package com.barrybecker4.experimentation.readnumber

import com.barrybecker4.sound.speech.SpeechSynthesizer
import java.math.BigInteger
import java.util.Scanner


/**
  * Reads a positive integer entered by the user.
  * @author Barry Becker
  */
object ReadNumberApp extends App {
  /** A greeting specified using allophones. See SpeechSynthesizer.  */
  private val GREETING = "p|l|ee|z e|n|t|er aa nn|u|m|b|er .|."
  private val speech = new SpeechSynthesizer
  private val translator = new NumberTranslator

  def translateToEnglish(number: BigInteger): String = translator.translateToEnglish(number)

  def sayInEnglish(number: BigInteger): Unit = {
    val phonetic = translator.translateToPhonetic(number)
    speech.sayText(phonetic)
  }

  private def numberPronunciation(nums: Array[NumberEnum]): Unit = {
    val words = new Array[String](nums.length)
    var ct = 0
    for (num <- nums) {
      words(ct) = num.pronunciation
      ct += 1
    }
    speech.sayPhoneWords(words)
  }

  private def testNumberSpeach(): Unit = {
    numberPronunciation(SimpleNumber.values)
    numberPronunciation(TensNumber.values)
    numberPronunciation(GroupNumber.values)
  }

  println("Enter numbers to speak and write long hand (or Q to quit)")
  // This works for arbitrary strings, but is not as nice sounding as the pre-generated wav file.
  speech.sayText(GREETING)
  val scanner = new Scanner(System.in)
  var done = false
  while (!done) {
    println("Enter a positive integer:")
    val nextString = scanner.nextLine
    done = nextString.toUpperCase.startsWith("Q")
    try {
      val number = new BigInteger(nextString)
      println(translateToEnglish(number))
      sayInEnglish(number)
    } catch {
      case e: NumberFormatException =>
        println("That was not a valid number.")
      case _ => println("unknown error")
    }
  }
}