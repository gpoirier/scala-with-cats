package com.tapad.book.chapter4

import org.scalatest.wordspec._
import org.scalatest.matchers.should.Matchers

class Exercice_4_6_5_Spec extends AnyWordSpec with Matchers {
  "foldRight" should {
    "stacksafe" in {
      import Exercice_4_6_5._

      val list = (1 to 100000).toList
      foldRight(list, 0)(_ + _) should be (list.sum)
    }
  }
}
