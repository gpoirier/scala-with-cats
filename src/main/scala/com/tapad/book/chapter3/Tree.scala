package com.tapad.book.chapter3

import cats._
import cats.implicits._

sealed trait Tree[+A]

object Tree {
  implicit val treeFunctor: Functor[Tree] = new Functor[Tree] {
    override def map[A, B](fa: Tree[A])(f: A => B): Tree[B] = fa match {
      case Branch(left, right) => Branch(left.map(f), right.map(f))
      case Leaf(value) => Leaf(f(value))
    }
  }
  implicit def treeEq[A: Eq]: Eq[Tree[A]] = Eq.fromUniversalEquals[Tree[A]]
}

final case class Branch[A](left: Tree[A], right: Tree[A])
  extends Tree[A]
final case class Leaf[A](value: A) extends Tree[A]