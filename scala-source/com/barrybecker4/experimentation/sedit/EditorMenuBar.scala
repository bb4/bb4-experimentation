package com.barrybecker4.experimentation.sedit

import com.barrybecker4.ui.file.ExtensionFileFilter
import com.barrybecker4.ui.file.FileChooserUtil
import javax.swing._
import java.awt.event.ActionEvent
import java.awt.event.ActionListener


/**
  * A top level menu to allow opening and saving of edited files.
  *
  * @author Barry Becker
  */
object EditorMenuBar {
  private var chooser: JFileChooser = _
  private val EXT = "sed"

  private def getFileChooser = {
    if (chooser == null) {
      chooser = FileChooserUtil.getFileChooser
      chooser.setFileFilter(new ExtensionFileFilter(EXT))
    }
    chooser
  }
}

class EditorMenuBar private[sedit](var editArea: SimpleEditor) extends JMenuBar with ActionListener {

  val fileMenu = new JMenu("File")
  fileMenu.setBorder(BorderFactory.createEtchedBorder)

  // menu options
  private var openItem = createMenuItem("Open")
  private var saveItem = createMenuItem("Save")
  private var exitItem = createMenuItem("Exit")
  fileMenu.add(openItem)
  fileMenu.add(saveItem)
  fileMenu.add(exitItem)
  add(fileMenu)

  private def createMenuItem(name: String) = {
    val item = new JMenuItem(name)
    item.addActionListener(this)
    item
  }

  /** The actionPerformed method in this class
    * Open and save files.
    */
  override def actionPerformed(e: ActionEvent): Unit = {
    val item = e.getSource.asInstanceOf[JMenuItem]
    if (item eq openItem) openDoc()
    else if (item eq saveItem) saveDoc()
    else if (item eq exitItem) System.exit(0)
  }

  /** Restore a game from a previously saved file (in SGF = Smart Game Format)
    * Derived classes should implement the details of the open
    */
  def openDoc(): Unit = {
    val chooser = EditorMenuBar.getFileChooser
    val state = chooser.showOpenDialog(null)
    val file = chooser.getSelectedFile
    if (file != null && state == JFileChooser.APPROVE_OPTION) editArea.loadFile(file.getAbsolutePath)
  }

  /** Save the current game to the specified file (in SGF = Smart Game Format)
    * Derived classes should implement the details of the save
    */
  def saveDoc(): Unit = {
    val chooser = EditorMenuBar.getFileChooser
    val state = chooser.showSaveDialog(null)
    val file = chooser.getSelectedFile
    if (file != null && state == JFileChooser.APPROVE_OPTION) { // if it does not have the .sgf extension already then add it
      var fPath = file.getAbsolutePath
      fPath = ExtensionFileFilter.addExtIfNeeded(fPath, EditorMenuBar.EXT)
      editArea.saveFile(fPath)
    }
  }
}
