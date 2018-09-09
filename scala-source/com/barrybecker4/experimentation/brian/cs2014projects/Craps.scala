package com.barrybecker4.experimentation.brian.cs2014projects

import java.util.Scanner


/**
  * @author Period 3
  */
object Craps {
  def main(args: Array[String]): Unit = {
    val kbd = new Scanner(System.in)
    var dice1 = 0
    var dice2 = 0
    var point = 0
    var bet = 0
    var bank = 2000
    while (bank < 100000000) {
      println("how much would you like to bet")
      bet = kbd.nextInt
      while (bet > bank) {
        println("you bet too much try again")
        bet = kbd.nextInt
      }
      dice1 = Dice.dice(6)
      dice2 = Dice.dice(6)
      System.out.println(dice1 + dice2)
      if (dice1 + dice2 == 7 || dice1 + dice2 == 11) {
        System.out.println("you win $" + bet)
        bank = bank + bet
      }
      if (dice1 + dice2 == 2 || dice1 + dice2 == 3 || dice1 + dice2 == 12) {
        System.out.println("YOU LOSE! $" + bet)
        bank = bank - bet
      }
      point = dice1 + dice2
      var done = false
      while (dice1 + dice2 != 7 && !done) {
        dice1 = Dice.dice(6)
        dice2 = Dice.dice(6)
        println(dice1 + dice2)
        if (dice1 + dice2 == point) {
          println("you win " + bet)
          bank = bank + bet
          done = true
        }
        else if (dice1 + dice2 == 7) {
          println("YOU LOSE! " + bet + "$")
          bank = bank - bet
          done = true
        }
      }
      println("your balance is " + bank + "$")
    }
    print("you have too much money don't lose it gambling!")
  }
}