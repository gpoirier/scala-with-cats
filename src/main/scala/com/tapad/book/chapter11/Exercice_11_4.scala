package com.tapad.book.chapter11

import cats.kernel.CommutativeMonoid
import cats.instances.list._
import cats.instances.map._
import cats.syntax.semigroup._
import cats.syntax.foldable._

object Exercice_11_4 {

  trait BoundedSemiLattice[A] extends CommutativeMonoid[A] {
    def combine(a1: A, a2: A): A
    def empty: A
  }

  trait GCounter[F[_,_],K, V] {
    def increment(f: F[K, V])(k: K, v: V)
                 (implicit m: CommutativeMonoid[V]): F[K, V]

    def merge(f1: F[K, V], f2: F[K, V])
             (implicit b: BoundedSemiLattice[V]): F[K, V]

    def total(f: F[K, V])
             (implicit m: CommutativeMonoid[V]): V
  }

  object GCounter {
    def apply[F[_,_], K, V](implicit counter: GCounter[F, K, V]): GCounter[F, K, V] = counter

    implicit def forMap[A]: GCounter[Map, String, A] = MapGCounter[A]()
  }

  final case class MapGCounter[A]() extends GCounter[Map, String, A] {

    type F[Key, Value] = Map[Key, Value]
    type K = String
    type V = A

    def increment(f: F[K, V])(k: K, v: V)(implicit m: CommutativeMonoid[V]): F[K, V] = {
      val value = v |+| f.getOrElse(k, m.empty)
      f + (k -> value)
    }

    def merge(f1: F[K, V], f2: F[K, V])(implicit b: BoundedSemiLattice[V]): F[K, V] = f1 |+| f2

    def total(f: F[K, V])(implicit m: CommutativeMonoid[V]): V = f.values.toList.combineAll
  }
}
