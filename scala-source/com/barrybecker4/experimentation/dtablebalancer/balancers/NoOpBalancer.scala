package com.barrybecker4.experimentation.dtablebalancer.balancers

import com.barrybecker4.experimentation.dtablebalancer.Table


/**
  * The approach used here is to set the row or column height/width to the average of the mean and max values normalized
  * by the overall width/height.
  * @author Barry Becker
  */
class NoOpBalancer extends Balancer {

  override def doBalancing(table: Table): Unit = {
    // intentionally does nothing
  }
}
