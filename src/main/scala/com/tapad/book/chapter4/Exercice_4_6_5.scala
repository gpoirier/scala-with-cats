package com.tapad.book.chapter4

import cats.Eval

object Exercice_4_6_5 {
  def foldRight0[A, B](as: List[A], acc: B)(fn: (A, B) => B): B = as match {
    case head :: tail =>
      fn(head, foldRight0(tail, acc)(fn))
    case Nil =>
      acc
  }

  def foldRight[A, B](as: List[A], acc: B)(fn: (A, B) => B): B = {
    def go(as: List[A], acc: B): Eval[B] =
      as match {
        case head :: tail =>
          Eval.defer(go(tail, acc)).map(b => fn(head, b))
        case Nil =>
          Eval.now(acc)
      }
    go(as, acc).value
  }
}
