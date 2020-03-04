package com.tapad.book.chapter11

import cats.kernel.CommutativeMonoid
import cats.instances.list._
import cats.instances.map._
import cats.syntax.semigroup._
import cats.syntax.foldable._

object Exercice_11_3_3 {

  trait BoundedSemiLattice[A] extends CommutativeMonoid[A] {
    def combine(a1: A, a2: A): A
    def empty: A
  }

  final case class GCounter[A](counters: Map[String, A]) {
    def increment(machine: String, amount: A)(implicit A: CommutativeMonoid[A]): GCounter[A] = {
      val value = amount |+| counters.getOrElse(machine, A.empty)
      GCounter(counters + (machine -> value))
    }

    def merge(that: GCounter[A])(implicit A: BoundedSemiLattice[A]): GCounter[A] =
      GCounter(this.counters |+| that.counters)

    def total(implicit A: CommutativeMonoid[A]): A =
      counters.values.toList.combineAll
  }

}
