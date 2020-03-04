package com.tapad.book.chapter2

import cats.implicits._
import cats.kernel.laws.discipline.MonoidTests
import org.scalatest.funsuite.AnyFunSuite
import org.typelevel.discipline.scalatest.Discipline

class SetMonoidLaws extends AnyFunSuite with Discipline {
  checkAll("Concat.MonoidLaws",  MonoidTests[Set[Int]](SetInstances.concat).monoid)
}