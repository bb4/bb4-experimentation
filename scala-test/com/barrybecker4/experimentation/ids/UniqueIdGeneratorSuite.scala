package com.barrybecker4.experimentation.ids

import java.util.Random

import org.scalatest.funsuite.AnyFunSuite

class UniqueIdGeneratorSuite extends AnyFunSuite:

  test("formatId pads with leading zeros") {
    assert(UniqueIdGenerator.formatId(42, 9, useDashes = false) == "000000042")
  }

  test("formatId inserts dashes every three digits") {
    assert(UniqueIdGenerator.formatId(123456789, 9, useDashes = true) == "123-456-789")
    assert(UniqueIdGenerator.formatId(42, 9, useDashes = true) == "000-000-042")
  }

  test("addDashes on a fixed-width string") {
    assert(UniqueIdGenerator.addDashes("123456789") == "123-456-789")
  }

  test("getRandomNumber stays in range for fixed seed") {
    val r = new Random(1)
    val n = UniqueIdGenerator.getRandomNumber(4, r)
    assert(n >= 1 && n <= 9999)
  }
