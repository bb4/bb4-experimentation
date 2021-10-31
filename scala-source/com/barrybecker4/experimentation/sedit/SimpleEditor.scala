package com.barrybecker4.experimentation.sedit

import com.barrybecker4.common.util.Base64Codec
import com.barrybecker4.ui.components.ScrollingTextArea
import java.io._
import scala.io.Source


object SimpleEditor {
  private val COMPRESS = true
}

/**
  * A top level menu to allow opening and saving of edited files.
  */
class SimpleEditor private[sedit](val rows: Int, val cols: Int) extends ScrollingTextArea(rows, cols) {

  def loadFile(fileName: String): Unit = {
    val source = Source.fromFile(fileName)
    var text = source.getLines().mkString

    if (SimpleEditor.COMPRESS)
      text = Base64Codec.decompress(text)

    setText(text)
    source.close()
  }

  def saveFile(fileName: String): Unit = {
    val out = new BufferedWriter(new FileWriter(fileName))
    var text = getText
    if (SimpleEditor.COMPRESS)
      text = Base64Codec.compress(text)

    out.write(text)
    out.flush()
    out.close()
  }
}