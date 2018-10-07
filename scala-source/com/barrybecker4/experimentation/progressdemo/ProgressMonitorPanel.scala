package com.barrybecker4.experimentation.progressdemo

import com.barrybecker4.ui.components.ScrollingTextArea
import javax.swing.BorderFactory
import javax.swing.JButton
import javax.swing.JPanel
import javax.swing.ProgressMonitor
import javax.swing.Timer
import java.awt.BorderLayout
import java.awt.Toolkit
import java.awt.event.ActionEvent
import java.awt.event.ActionListener


/**
  * Demonstrates proper use of java ProgressMonitor for long running tasks.
  */
object ProgressMonitorPanel {
  val ONE_SECOND = 1000
  val TASK_LENGTH = 550
}

class ProgressMonitorPanel() extends JPanel {
  private var task = new LongTask(ProgressMonitorPanel.TASK_LENGTH)
  createUI()
  private var progressMonitor: ProgressMonitor = _
  private var timer: Timer = _
  private var startButton: JButton = _
  private var taskOutput: ScrollingTextArea = _

  protected def createUI(): Unit = {
    startButton = new JButton("Start")
    startButton.addActionListener(new ButtonListener)
    taskOutput = new ScrollingTextArea(10, 40)
    setLayout(new BorderLayout)
    add(startButton, BorderLayout.NORTH)
    add(taskOutput, BorderLayout.CENTER)
    setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20))
    // Create a timer.
    timer = new Timer(ProgressMonitorPanel.ONE_SECOND, new TimerListener)
  }

  private[progressdemo] class ButtonListener extends ActionListener {

    /** Called when the user presses the start button. */
    override def actionPerformed(evt: ActionEvent): Unit = {
      progressMonitor = new ProgressMonitor(null, "Running a Long Task", "", 0, task.getLengthOfTask)
      progressMonitor.setProgress(0)
      progressMonitor.setMillisToDecideToPopup(ProgressMonitorPanel.ONE_SECOND)
      startButton.setEnabled(false)
      task.go()
      timer.start()
    }
  }

  private[progressdemo] class TimerListener extends ActionListener {

    /** Called each time the Timer is triggered (each second). */
    override def actionPerformed(evt: ActionEvent): Unit = {
      val newline = "\n"
      if (progressMonitor.isCanceled || task.done) {
        progressMonitor.close()
        task.stop()
        Toolkit.getDefaultToolkit.beep()
        timer.stop()
        if (task.done) taskOutput.append("Task completed." + newline)
        startButton.setEnabled(true)
      }
      else {
        progressMonitor.setNote(task.getMessage)
        progressMonitor.setProgress(task.getCurrent.toInt)
        taskOutput.append(task.getMessage + newline)
      }
    }
  }

}
