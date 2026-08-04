package com.barrybecker4.experimentation.restaurant

case class Order(val id: Int):
  override def toString: String = "Order " + id
