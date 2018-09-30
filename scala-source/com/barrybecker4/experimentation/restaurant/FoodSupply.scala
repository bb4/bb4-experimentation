package com.barrybecker4.experimentation.restaurant

/**
  * The kitchen contains the order that the chef produces and the waiter consumes.
  * Synchronize on this.
  */
case class FoodSupply (foodAmount: Int) {

  def hasFood: Boolean = foodAmount > 0

  def takeSomeFood(): FoodSupply = FoodSupply(foodAmount - 1)
}
