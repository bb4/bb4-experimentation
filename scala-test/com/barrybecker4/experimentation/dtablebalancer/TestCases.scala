package com.barrybecker4.experimentation.dtablebalancer

import com.barrybecker4.experimentation.dtablebalancer.balancers.{Balancer, MaxBalancer, NoOpBalancer, SqrRootBalancer}


case class TestCase(name: String, table: Table, balancer: Balancer,
                    expNormScale: Double, expInitialOverallRatio: Double, expBalancedRatio: Double)

/**
  * @author Barry Becker
  */
object TestCases {

  val CASES = Array(
    TestCase("Uniform 2 by 2 (200x200) (noop balancer)",
      new Table(TableExamples.UNIFORM_2x2, 200, 200),
      new NoOpBalancer, 5000.0, 1.0, 1.0),
    TestCase("Uniform 2 by 2 (200x200)",
      new Table(TableExamples.UNIFORM_2x2, 200, 200),
      new SqrRootBalancer, 5000.0, 1.0, 1.0),
    TestCase("Uniform 2 by 2 (100x100)",
      new Table(TableExamples.UNIFORM_2x2, 100, 100),
      new SqrRootBalancer, 1250.0, 1.0, 1.0),
    TestCase("Uniform 2 by 2 (130x130)",
      new Table(TableExamples.UNIFORM_2x2, 130, 130),
      new SqrRootBalancer, 2112.5, 1.0, 1.0),
    TestCase("Uniform 3 by 3 (300x300)",
      new Table(TableExamples.UNIFORM_3x3, 300, 300),
      new SqrRootBalancer, 100.0, 1.0, 1.0),
    TestCase("Narrow middle column 3 by 3 (1000x1000)", //  5
      new Table(TableExamples.NARROW_MIDDLE_COLUMN_3x3, 1000, 1000),
      new SqrRootBalancer, 1111.111111, 0.73889, 0.84232),
    TestCase("Narrow middle row 3 by 3 (500x500)",
      new Table(TableExamples.NARROW_MIDDLE_ROW_3x3, 500, 500),
      new SqrRootBalancer, 277.7777777, 0.738888889, 0.842583948),
    TestCase("Random 3 by 3 (500x500)", //   7
      new Table(TableExamples.RANDOM_3x3, 500, 500),
      new SqrRootBalancer, 138.888888888889, 0.4216667, 0.64144138),
    TestCase("Random 4 by 4 (600x600)",
      new Table(TableExamples.RANDOM_4x4, 600, 600),
      new SqrRootBalancer, 47.07113, 0.36833159, 0.57406665),
    TestCase("Random 4 by 4 (600x600)",
      new Table(TableExamples.RANDOM_4x4, 600, 600),
      new MaxBalancer, 47.07113, 0.36833159, 0.5720941),
    TestCase("Race by Native (1200x1200)",
      new Table(TableExamples.RACE_BY_NATIVE_4x4, 1200, 1200),
      new SqrRootBalancer, 54.38066, 0.159441, 0.369373496),
    TestCase("Race by Native (100x100)",
      new Table(TableExamples.RACE_BY_NATIVE_4x4, 100, 100),
      new SqrRootBalancer, 0.3776435, 0.159441, 0.36663332),
    TestCase("Race by Native (70x70)", // 12
      new Table(TableExamples.RACE_BY_NATIVE_4x4, 70, 70),
      new SqrRootBalancer, 0.185045317, 0.159441087, 0.36544273),
    TestCase("Race by Native (100x100)",
      new Table(TableExamples.RACE_BY_NATIVE_4x4, 100, 100),
      new MaxBalancer, 0.3776435, 0.159441, 0.3696368),
    TestCase("Race by Native (70x70)", // 11
      new Table(TableExamples.RACE_BY_NATIVE_4x4, 70, 70),
      new MaxBalancer, 0.185045317, 0.159441087, 0.36963677)
  )
}
