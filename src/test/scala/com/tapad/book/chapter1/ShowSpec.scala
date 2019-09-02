package com.tapad.book.chapter1

import org.scalatest._

import cats._
import cats.implicits._

class ShowSpec extends WordSpec with Matchers {
  "Show" should {
    "format cats" in {
      val cat = Cat("Felix", 5, "gray")
      Show[Cat].show(cat) should be ("Felix is a 5 year-old gray cat.")
    }
    "also with syntax" in {
      val cat = Cat("Felix", 5, "gray")
      cat.show should be ("Felix is a 5 year-old gray cat.")
    }
  }
}