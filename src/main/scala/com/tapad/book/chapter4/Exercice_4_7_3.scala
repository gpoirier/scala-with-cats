package com.tapad.book.chapter4

import cats.syntax.writer._
import cats.syntax.applicative._
import cats.data._

object Exercice_4_7_3 {

  object WithPrintln {
    def slowly[A](body: => A) =
      try body finally Thread.sleep(100)

    def factorial(n: Int): Int = {
      val ans = slowly(if (n == 0) 1 else n * factorial(n - 1))
      println(s"fact $n $ans")
      ans
    }
  }

  object WithWriteT {
    def slowly[A](body: => A) =
      try body finally Thread.sleep(100)

    type Logged[A] = Writer[Chain[String], A]

    def factorial(n: Int): Logged[Int] = {
      for {
        answer <- {
          if (n == 0) 1.pure[Logged]
          else slowly(factorial(n - 1).map(_ * n))
        }
        _ <- Chain(s"fact $n $answer").tell
      } yield answer
    }
  }
}
