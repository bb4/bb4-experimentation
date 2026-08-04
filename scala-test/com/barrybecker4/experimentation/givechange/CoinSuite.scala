package com.barrybecker4.experimentation.givechange

import org.scalatest.funsuite.AnyFunSuite

class CoinSuite extends AnyFunSuite:

  test("VALUES are ascending by worth") {
    assert(Coin.VALUES.map(_.worthInPennies).toSeq == Seq(1, 5, 10, 25, 50))
  }

  test("each coin has singular and plural names") {
    assert(Coin.PENNY.name == "penny")
    assert(Coin.PENNY.pluralName == "pennies")
    assert(Coin.HALF_DOLLAR.name == "half dollar")
    assert(Coin.HALF_DOLLAR.pluralName == "half dollars")
  }

  test("Coin.values matches VALUES") {
    assert(Coin.values.toSeq == Coin.VALUES.toSeq)
  }


class CoinMapSuite extends AnyFunSuite:

  test("empty map renders as empty string") {
    assert(new CoinMap().toString == "")
  }

  test("add and toString list coins largest-first") {
    val map = new CoinMap
    map.add(Coin.PENNY, 3)
    map.add(Coin.QUARTER, 1)
    map.add(Coin.DIME, 2)
    assert(map.toString == "1 quarter   2 dimes   3 pennies   ")
  }

  test("singular name when count is one") {
    val map = new CoinMap
    map.add(Coin.NICKEL, 1)
    assert(map.toString == "1 nickel   ")
  }

  test("remove reduces count") {
    val map = new CoinMap
    map.add(Coin.DIME, 5)
    map.remove(Coin.DIME, 2)
    assert(map.toString == "3 dimes   ")
  }

  test("add accumulates for the same coin") {
    val map = new CoinMap
    map.add(Coin.PENNY, 2)
    map.add(Coin.PENNY, 4)
    assert(map.toString == "6 pennies   ")
  }


class GiveChangeSuite extends AnyFunSuite:

  /** Capture printed ideal-change lines for a given cent amount. */
  private def changeLines(cents: Long): List[String] =
    val out = new java.io.ByteArrayOutputStream
    Console.withOut(out) {
      GiveChange.showChangeFor(cents)
    }
    out.toString.linesIterator.toList.filter(_.startsWith("  "))

  test("ideal change for 0 cents prints no coin lines") {
    assert(changeLines(0) == Nil)
  }

  test("ideal change for 41 cents is 1 half dollar? no — 1 quarter + 1 dime + 1 nickel + 1 penny") {
    // 41 = 25 + 10 + 5 + 1 (greedy; half dollar is 50)
    assert(changeLines(41) == List(
      "  1 quarter",
      "  1 dime",
      "  1 nickel",
      "  1 penny"
    ))
  }

  test("ideal change for 99 cents uses largest denominations") {
    assert(changeLines(99) == List(
      "  1 half dollar",
      "  1 quarter",
      "  2 dimes",
      "  4 pennies"
    ))
  }
