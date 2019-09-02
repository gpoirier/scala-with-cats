package com.tapad.book.chapter1

import cats._
import cats.implicits._

final case class Cat(name: String, age: Int, color: String)

object Cat {
  implicit object Printer extends Printable[Cat] {
    def format(cat: Cat): String = {
      import cat._
      s"$name is a $age year-old $color cat."
    }
  }
  implicit val showCat: Show[Cat] = Show.show { cat =>
    import cat._
    s"$name is a $age year-old $color cat."
  }

  implicit val eqCat: Eq[Cat] = Eq.by(Cat.unapply)
}