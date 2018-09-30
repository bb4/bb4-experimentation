package com.barrybecker4.experimentation.restaurant

import com.barrybecker4.common.concurrency.ThreadUtil

class Waiter(val kitchen: Kitchen) extends Thread {
  override def run(): Unit = {
    while (true) {
      while (!kitchen.hasOrder) {
        ThreadUtil.sleep(200)
      }
      println("Waiter got " + kitchen.getAndClearOrder)
    }
  }
}