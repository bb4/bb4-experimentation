package com.barrybecker4.experimentation.progressdemo

import com.barrybecker4.common.concurrency.ThreadUtil
import com.barrybecker4.common.concurrency.Worker


/**
  * Uses a Worker to perform a time-consuming, fake task.
  * Compute magnitude of task...
  * In a real program, this would figure out the number of bytes to read or whatever.
  * @param lengthOfTask size of the task
  */
class LongTask private[progressdemo](var lengthOfTask: Int) {
  private var current: Int = 0
  private var statMessage = "begun"

  /** Called from ProgressBarDemo to start the task. */
  private[progressdemo] def go(): Unit = {
    current = 0
    new Worker() {
      override def construct = new ActualTask
    }.start()
  }

  /** Called from ProgressBarDemo to find out how much work needs to be done. */
  private[progressdemo] def getLengthOfTask = lengthOfTask

  /** Called from ProgressBarDemo to find out how much has been done. */
  private[progressdemo] def getCurrent = current

  private[progressdemo] def stop(): Unit = {
    current = lengthOfTask
  }

  /** Called from ProgressBarDemo to find out if the task has completed. */
  private[progressdemo] def done = current >= lengthOfTask

  private[progressdemo] def getMessage = statMessage

  /** The actual long running task.  This runs in a Worker thread. */
  private class ActualTask() { // Fake a long task,
    // make a random amount of progress every second.
    while (current < lengthOfTask) {
      ThreadUtil.sleep(1000)
      // make some progress
      current += (Math.random * 100).toInt
      if (current > lengthOfTask) current = lengthOfTask
      statMessage = "Completed " + current + " out of " + lengthOfTask + "."
    }
  }
}