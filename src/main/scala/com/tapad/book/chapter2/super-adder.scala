package com.tapad.book.chapter2

import cats.Monoid
import cats.instances.int._

object IntSuperAdder {
  def add(items: List[Int]): Int = Monoid.combineAll(items)
}

object SuperAdder {
  def add[A: Monoid](items: List[A]): A = Monoid.combineAll(items)
}