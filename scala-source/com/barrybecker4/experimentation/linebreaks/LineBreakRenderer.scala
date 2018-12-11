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

import java.awt.{Graphics, Graphics2D}
import java.awt.font.LineBreakMeasurer
import java.text.AttributedString


/**
  * Demonstrates how to line-break and draw a paragraph
  * of text using LineBreakMeasurer and TextLayout.
  */
class LineBreakRenderer(textAttribute: AttributedString) {

  // The LineBreakMeasurer used to line-break the paragraph.
  private var lineMeasurer: LineBreakMeasurer = _
  // index of the first character in the paragraph.
  private var paragraphStart = 0
  // index of the first character after the end of the paragraph.
  private var paragraphEnd = 0

  /**
    * Constructs a LineBreakMeasurer from an AttributedCharacterIterator and caches it.
    * It uses the LineBreakMeasurer to create and draw TextLayouts (lines of text) which fit within
    * the specified width.
    * @param width width that the text must be displayed in.
    */
  def render(g: Graphics, width: Int): Unit = {

    val g2d = g.asInstanceOf[Graphics2D]

    if (lineMeasurer == null) {
      val paragraph = textAttribute.getIterator
      paragraphStart = paragraph.getBeginIndex
      paragraphEnd = paragraph.getEndIndex
      val frc = g2d.getFontRenderContext
      lineMeasurer = new LineBreakMeasurer(paragraph, frc)
    }

    val breakWidth = width.toFloat
    var drawPosY: Float = 0
    lineMeasurer.setPosition(paragraphStart)

    // Get lines until the entire paragraph has been displayed.
    while (lineMeasurer.getPosition < paragraphEnd) {
      // Retrieve next layout. Consider caching these layouts until the component is re-sized.
      val layout = lineMeasurer.nextLayout(breakWidth)
      // Compute pen x position. If the paragraph is right-to-left, align the TextLayouts
      // to the right edge of the panel.
      // Note: this won't occur for the English text in this sample.
      // Note: drawPosX is always where the LEFT of the text is placed.
      val drawPosX = if (layout.isLeftToRight) 0 else breakWidth - layout.getAdvance
      drawPosY += layout.getAscent
      layout.draw(g2d, drawPosX, drawPosY)
      drawPosY += layout.getDescent + layout.getLeading
    }
  }
}
