package com.barrybecker4.experimentation.sedit

import com.barrybecker4.ui.util.GUIUtil
import javax.swing._
import java.awt._


object SimpleEditorFrame {
  def main(args: Array[String]): Unit = {
    new SimpleEditorFrame
  }
}

class SimpleEditorFrame() extends JFrame("Simple Editor") {
  GUIUtil.setCustomLookAndFeel()
  val editArea: SimpleEditor = createSimpleEditor
  val menubar: EditorMenuBar = new EditorMenuBar(editArea)
  getRootPane.setJMenuBar(menubar)
  createContentPane(editArea)
  pack()
  setVisible(true)

  private def createSimpleEditor = {
    val editArea = new SimpleEditor(30, 45)
    editArea.setEditable(true)
    editArea.setFont(new Font(GUIUtil.DEFAULT_FONT_FAMILY, Font.PLAIN, 12))
    editArea
  }

  private def createContentPane(editArea: SimpleEditor): Unit = {
    val contentPane = new JPanel
    contentPane.setLayout(new BorderLayout)
    contentPane.add(editArea, BorderLayout.CENTER)
    contentPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10))
    setContentPane(contentPane)
  }
}
