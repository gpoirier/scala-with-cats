package com.tapad.book.chapter2

import org.scalatest._

import cats.instances.int._
import cats.instances.option._

class SuperAdderSpec extends WordSpec with Matchers {
  "IntSuperAdd" should {
    "add numbers" in {
      IntSuperAdder.add(List(1, 2, 3)) should be (6)
    }
    "support empty lists" in {
      IntSuperAdder.add(Nil) should be (0)
    }
  }
  "SuperAdd" should {
    "add numbers" in {
      SuperAdder.add(List(1, 2, 3)) should be(6)
    }
    "add Options" in {
      SuperAdder.add(List(Option(1), None, Option(3))) should be (Some(4))
    }
    "still support empty" in {
      SuperAdder.add(List.empty[Option[Int]]) should be (None)
      SuperAdder.add(List(Option.empty[Int])) should be (None)
    }
    "add orders" in {
      SuperAdder.add(List.empty[Order]) should be (Order(0, 0))
      SuperAdder.add(List(Order(10, 2), Order(20, 3))) should be (Order(30, 5))
    }
  }
}
