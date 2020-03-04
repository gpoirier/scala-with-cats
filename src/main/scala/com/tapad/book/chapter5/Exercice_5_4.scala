package com.tapad.book.chapter5

import cats.data.EitherT
import cats.implicits._

import scala.concurrent._
import scala.concurrent.duration._
import scala.concurrent.ExecutionContext.Implicits.global

object Exercice_5_4 {
  // type Response[A] = Future[Either[String, A]]
  type Response[A] = EitherT[Future, String, A]


  val powerLevels = Map(
    "Jazz"      -> 6,
    "Bumblebee" -> 8,
    "Hot Rod"   -> 10
  )

  def getPowerLevel(name: String): Response[Int] = EitherT {
    Future {
      powerLevels.get(name).toRight(s"The autobot $name cannot be reached")
    }
  }

  def canSpecialMove(ally1: String, ally2: String): Response[Boolean] =
    (getPowerLevel(ally1), getPowerLevel(ally2)).mapN(_ + _ > 15)

  def tacticalReport(ally1: String, ally2: String): String = {
    val result = canSpecialMove(ally1, ally2) map {
      case true => s"$ally1 and $ally2 can perform a special move"
      case false => s"$ally1 and $ally2 cannot perform a special move"
    }
    Await.result(result.value, 5.seconds).fold(error => s"Communication error: $error", identity)
  }
}