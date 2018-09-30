package com.barrybecker4.experimentation.restaurant

/**
  * The kitchen contains the order that the chef produces and the waiter consumes.
  * @param supply the amount of initial food
  */
class Kitchen(supply: Int) {

  private var foodSupply = FoodSupply(supply)
  private var order: Option[Order] = None

  def hasOrder: Boolean = order.isDefined

  def getAndClearOrder: Order = {
    assert(hasOrder)
    val o = order.get
    order = None
    o
  }

  def createOrder(): Order = {
    val o = Order(getNewOrderId)
    println("Kitchen created order " + o.id)
    order = Some(o)
    o
  }

  private def getNewOrderId = {
    if (!foodSupply.hasFood) {
      println("Out of food, closing")
      System.exit(0)
    }
    foodSupply = foodSupply.takeSomeFood()
    foodSupply.foodAmount
  }
}
