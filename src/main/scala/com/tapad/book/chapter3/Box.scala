package com.tapad.book.chapter3

final case class Box[A](value: A)

object Box {
  implicit def printBox[A: Printable]: Printable[Box[A]] =
    Printable[A].contramap(_.value)

  implicit def codec[A: Codec]: Codec[Box[A]] =
    Codec[A].imap(Box(_), _.value)
}