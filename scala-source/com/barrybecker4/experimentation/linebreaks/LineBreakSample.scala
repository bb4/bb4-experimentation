package com.barrybecker4.experimentation.linebreaks

import java.awt.{BorderLayout, Dimension}
import javax.swing.JFrame


object LineBreakSample extends App {

  val TEXT = """Many people believe that Vincent van Gogh painted his best works during the two-year period he spent in Provence. Here is where he painted The Starry Night--which some consider to be his greatest work of all. However, as his artistic brilliance reached new heights in Provence, his physical and mental health plummeted."""

  val f = new JFrame("Line Break Sample")

  val lineBreakPanel = new LineBreakPanel(TEXT)
  f.getContentPane.add(lineBreakPanel, BorderLayout.CENTER)
  f.setSize(new Dimension(400, 250))
  f.setVisible(true)
}

