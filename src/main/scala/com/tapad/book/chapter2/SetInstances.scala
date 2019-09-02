package com.tapad.book.chapter2

import cats._

object SetInstances {
  def concat[A]: Monoid[Set[A]] = new Monoid[Set[A]] {
    override def empty: Set[A] = Set.empty
    override def combine(x: Set[A], y: Set[A]): Set[A] = x ++ y
  }
}
