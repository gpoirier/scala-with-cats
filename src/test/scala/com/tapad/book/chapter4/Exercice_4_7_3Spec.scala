package com.tapad.book.chapter4

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class Exercice_4_7_3Spec extends AnyFlatSpec with Matchers {

  it should "print logs" in {
    import Exercice_4_7_3.WithPrintln.factorial

    factorial(10) should be (3628800)

    import scala.concurrent._
    import scala.concurrent.ExecutionContext.Implicits.global
    import scala.concurrent.duration._

    val results = Await.result(Future.sequence(Vector(
      Future(factorial(3)),
      Future(factorial(3))
    )), 5.seconds)

    results should be (Vector(6, 6))
  }

  it should "accumulate logs" in {
    import Exercice_4_7_3.WithWriteT.factorial

    factorial(10).value should be (3628800)

    val (logs, answer) = factorial(3).run
    answer should be (6)
    logs.toList should contain theSameElementsInOrderAs List("fact 0 1", "fact 1 1", "fact 2 2", "fact 3 6")
  }

  it should "work concurrently" in {
    import scala.concurrent._
    import scala.concurrent.ExecutionContext.Implicits.global
    import scala.concurrent.duration._
    import Exercice_4_7_3.WithWriteT.factorial

    val Vector((logs1, answer1), (logs2, answer2)) = Await.result(Future.sequence(Vector(
      Future(factorial(4).run),
      Future(factorial(3).run)
    )), 5.seconds)

    answer1 should be (24)
    answer2 should be (6)

    logs1.toList should contain theSameElementsInOrderAs List("fact 0 1", "fact 1 1", "fact 2 2", "fact 3 6", "fact 4 24")
    logs2.toList should contain theSameElementsInOrderAs List("fact 0 1", "fact 1 1", "fact 2 2", "fact 3 6")
  }
}
