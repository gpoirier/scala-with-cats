package com.tapad.book.chapter1

import org.scalatest.wordspec._
import org.scalatest.matchers.should.Matchers

class PrintableSpec extends AnyWordSpec with Matchers {
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