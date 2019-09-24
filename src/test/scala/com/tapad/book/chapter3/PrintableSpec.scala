package com.tapad.book.chapter3

import org.scalatest._

class PrintableSpec extends WordSpec with Matchers {
  "Printable" should {
    "format strings" in {
      Printable[String].format("hello") should be ("\"hello\"")
    }
    "format booleans" in {
      Printable[Boolean].format(true) should be ("yes")
    }
    "format boxes" in {
      Printable[Box[Boolean]].format(Box(true)) should be ("yes")
      Printable[Box[String]].format(Box("hello")) should be ("\"hello\"")
    }
  }
}