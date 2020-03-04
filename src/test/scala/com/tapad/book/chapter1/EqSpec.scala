package com.tapad.book.chapter1

import org.scalatest.wordspec._
import org.scalatest.matchers.should.Matchers
import Compare._

class EqSpec extends AnyWordSpec with Matchers {
  "Eq" should {
    "return true for the same cat" in {
      testSame shouldBe true
    }
    "return false if they are different" in {
      testDifferent shouldBe true
    }
    "work for the book example" in {
      fromBook shouldBe true
    }
  }
}

// The tests using =!= and === are moved
// to their own class because scalatest 
// also define those operators
object Compare {
  import cats.implicits._

  def testSame: Boolean = {
    val cat1 = Cat("Felix", 5, "gray")
    val cat2 = Cat("Felix", 5, "gray")
    (cat1 === cat2) && !(cat1 =!= cat2)
  }

  def testDifferent: Boolean = {
    val cat1 = Cat("Felix", 5, "gray")
    val cat2 = Cat("Felix", 6, "gray")
    !(cat1 === cat2) && (cat1 =!= cat2)
  }

  def fromBook: Boolean = {
    val cat1 = Cat("Garfield",   38, "orange and black")
    val cat2 = Cat("Heathcliff", 33, "orange and black")
    val optionCat1 = Option(cat1)
    val optionCat2 = Option.empty[Cat]

    (cat1 =!= cat2) && (optionCat1 =!= optionCat2)
  }
}