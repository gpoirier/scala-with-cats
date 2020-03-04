package com.tapad.book.chapter2

import cats.implicits._
import cats.kernel.laws.discipline.MonoidTests

import org.scalatest.funsuite.AnyFunSuite
import org.typelevel.discipline.scalatest.Discipline

class BooleanMonoidLaws extends AnyFunSuite with Discipline {
  checkAll("And.MonoidLaws", MonoidTests[Boolean](BooleanInstances.And).monoid)
  checkAll("Or.MonoidLaws", MonoidTests[Boolean](BooleanInstances.Or).monoid)
  checkAll("Xor.MonoidLaws", MonoidTests[Boolean](BooleanInstances.Xor).monoid)
}