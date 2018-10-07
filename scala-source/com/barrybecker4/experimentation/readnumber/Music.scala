package com.barrybecker4.experimentation.readnumber

import com.barrybecker4.sound.MusicMaker

/** Simple music test */
object Music extends App {

  private val DURATION = 100
  /** instance under test. */
  private val music = new MusicMaker

  val instruments = Music.music.getInstruments
  var i = 0
  while (i < 30) {
    Music.music.playNote(i / 8, i % 8, 50, 1, Music.DURATION, 200)
    i += 1
  }

}

