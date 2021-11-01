package com.barrybecker4.experimentation.restaurant

/**
  * The producer-consumer approach to thread cooperation.
  * The restaurant uses multiple threads for chef and waiter.
  * Originally derived from Bruce Eckel's Thinking in Java book, but now ported to scala.
  * In the scala implementation, I now use immutable objects to avoid the complexity of the java implementation.
  */
object Restaurant extends App {
  val kitchen = new Kitchen(10)
  new Chef(kitchen).start()
  new Waiter(kitchen).start()
}


