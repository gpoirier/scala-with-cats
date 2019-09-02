package com.tapad.book.chapter1

trait Printable[A] {
  def format(a: A): String
}

object Printable {
  def format[A](a: A)(implicit instance: Printable[A]): String = instance.format(a)
  def print[A: Printable](a: A): Unit = Console.println(format[A](a))
}

object PrintableInstanes {
  implicit object PritableString extends Printable[String] {
    def format(s: String): String = s
  }
  implicit object PritableInt extends Printable[Int] {
    def format(i: Int): String = i.toString
  }
}

object PrintableSyntax {
  implicit class PrintableOps[A: Printable](a: A) {
    def format: String = Printable.format(a)
    def print(): Unit = Printable.print(a)
  }
}