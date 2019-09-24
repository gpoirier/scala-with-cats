package com.tapad.book.chapter3

trait Printable[A] {
  def format(value: A): String

  def contramap[B](fn: B => A): Printable[B] = { value =>
    format(fn(value))
  }
}

object Printable {

  def apply[A](implicit ev: Printable[A]): Printable[A] = ev

  implicit val stringPrintable: Printable[String] =
    value => "\"" + value + "\""

  implicit val booleanPrintable: Printable[Boolean] =
    value => if (value) "yes" else "no"
}