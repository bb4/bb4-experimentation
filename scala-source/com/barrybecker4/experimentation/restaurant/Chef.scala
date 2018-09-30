package com.barrybecker4.experimentation.restaurant

import com.barrybecker4.common.concurrency.ThreadUtil
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

/**
  * A Chef that takes some variable amount of time to prepare orders
  */
class Chef private[restaurant](val kitchen: Kitchen) extends Thread {
  override def run(): Unit = {
    while (true) {
      ThreadUtil.sleep(100)
      if (kitchen.hasOrder) println("  Chef waiting for last order to be picked up by waiter...")
      else prepareOrder()
    }
  }

  private def prepareOrder(): Order = {
    println("Chef about to prepare order")
    prepareFood()
    val order: Order = kitchen.createOrder()
    println(s"Order up!  (for order ${order.id})")
    order
  }

  private def prepareFood(): Unit = {
    ThreadUtil.sleep((5000 * Math.random).toInt)
  }
}