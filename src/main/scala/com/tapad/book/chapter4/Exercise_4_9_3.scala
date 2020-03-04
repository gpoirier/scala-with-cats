package com.tapad.book.chapter4

import cats.syntax.applicative._
import cats.syntax.apply._

object Exercise_4_9_3 {
  import cats.data.State
  type CalcState[A] = State[List[Int], A]

  def operator(f: (Int, Int) => Int): CalcState[Int] = State {
    case first :: second :: tail =>
      val result = f(first, second)
      (result :: tail) -> result
    case _ =>
      sys.error("Bad state")
  }


  def evalOne(sym: String): CalcState[Int] = sym match {
    case "+" => operator(_ + _)
    case "-" => operator(_ - _)
    case "*" => operator(_ * _)
    case "/" => operator(_ / _)
    case num => State { stack =>
      val i = num.toInt
      (i :: stack) -> i
    }
  }

  def evalAll(input: List[String]): CalcState[Int] =
    input.foldLeft(0.pure[CalcState])((acc, input) => acc *> evalOne(input))

  def evalInput(input: String): Int = evalAll(input.split(" ").toList).runA(Nil).value
}
