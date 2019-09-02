package com.tapad.book.chapter1

import org.scalatest._

class PrintableSpec extends WordSpec with Matchers {
  "Printable" should {
    "format cats" in {
      val cat = Cat("Felix", 5, "gray")
      Printable.format(cat) should be ("Felix is a 5 year-old gray cat.")
    }
    "also with syntax" in {
      import PrintableSyntax._
      val cat = Cat("Felix", 5, "gray")
      cat.format should be ("Felix is a 5 year-old gray cat.")
    }
  }
}