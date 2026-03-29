package com.barrybecker4.experimentation.readnumber

import com.barrybecker4.sound.MusicMaker

/** Simple music test */
object Music:

  private val DURATION = 100
  private val music = new MusicMaker

  def main(args: Array[String]): Unit =
    var i = 0
    while i < 30 do
      music.playNote(i / 8, i % 8, 50, 1, DURATION, 200)
      i += 1
