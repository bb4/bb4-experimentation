/*
 * Copyright (c) 1995, 2008, Oracle and/or its affiliates. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 *   - Redistributions of source code must retain the above copyright
 *     notice, this list of conditions and the following disclaimer.
 *
 *   - Redistributions in binary form must reproduce the above copyright
 *     notice, this list of conditions and the following disclaimer in the
 *     documentation and/or other materials provided with the distribution.
 *
 *   - Neither the name of Oracle or the names of its
 *     contributors may be used to endorse or promote products derived
 *     from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS
 * IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE ARE DISCLAIMED.  IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package com.barrybecker4.experimentation.linebreaks

import javax.swing._
import java.awt._
import java.util
import java.awt.font.LineBreakMeasurer
import java.awt.font.TextAttribute
import java.text.AttributedString


object LineBreakPanel {
  private val map = new util.HashMap[TextAttribute, Object]
  private val vanGogh = new AttributedString(
    """Many people believe that Vincent van Gogh painted his best works during the two-year period he spent in Provence. Here is where he painted The Starry Night--which some consider to be his greatest work of all. However, as his artistic brilliance reached new heights in Provence, his physical and mental health plummeted. """,
    map)

  map.put(TextAttribute.FAMILY, "Serif")
  map.put(TextAttribute.SIZE, 18.0.toFloat.asInstanceOf[java.lang.Float])
}

/**
  * This class demonstrates how to line-break and draw a paragraph
  * of text using LineBreakMeasurer and TextLayout.
  *
  * This class constructs a LineBreakMeasurer from an
  * AttributedCharacterIterator.  It uses the LineBreakMeasurer
  * to create and draw TextLayouts (lines of text) which fit within
  * the Component's width.
  */
class LineBreakPanel extends JPanel {
  // The LineBreakMeasurer used to line-break the paragraph.
  private var lineMeasurer: LineBreakMeasurer = _
  // index of the first character in the paragraph.
  private var paragraphStart = 0
  // index of the first character after the end of the paragraph.
  private var paragraphEnd = 0

  override def paintComponent(g: Graphics): Unit = {
    super.paintComponent(g)
    setBackground(Color.white)
    val g2d = g.asInstanceOf[Graphics2D]
    // Create a new LineBreakMeasurer from the paragraph.
    // It will be cached and re-used.
    if (lineMeasurer == null) {
      val paragraph = LineBreakPanel.vanGogh.getIterator
      paragraphStart = paragraph.getBeginIndex
      paragraphEnd = paragraph.getEndIndex
      val frc = g2d.getFontRenderContext
      lineMeasurer = new LineBreakMeasurer(paragraph, frc)
    }
    // Set break width to width of Component.
    val breakWidth = this.getWidth.toFloat
    var drawPosY: Float = 0
    // Set position to the index of the first character in the paragraph.
    lineMeasurer.setPosition(paragraphStart)
    // Get lines until the entire paragraph has been displayed.
    while (lineMeasurer.getPosition < paragraphEnd) {
      // Retrieve next layout. A cleverer program would also cache
      // these layouts until the component is re-sized.
      val layout = lineMeasurer.nextLayout(breakWidth)
      // Compute pen x position. If the paragraph is right-to-left we
      // will align the TextLayouts to the right edge of the panel.
      // Note: this won't occur for the English text in this sample.
      // Note: drawPosX is always where the LEFT of the text is placed.
      val drawPosX = if (layout.isLeftToRight) 0
      else breakWidth - layout.getAdvance
      // Move y-coordinate by the ascent of the layout.
      drawPosY += layout.getAscent
      // Draw the TextLayout at (drawPosX, drawPosY).
      layout.draw(g2d, drawPosX, drawPosY)
      // Move y-coordinate in preparation for next layout.
      drawPosY += layout.getDescent + layout.getLeading
    }
  }
}
