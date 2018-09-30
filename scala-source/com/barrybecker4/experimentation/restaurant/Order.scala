package com.barrybecker4.experimentation.restaurant


case class Order(var id: Int) {
  override def toString: String = "Order " + id
}