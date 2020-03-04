package com.tapad.book.chapter4

import cats.data._
import cats.implicits._

object Exercise_4_8_3 {
  case class Db(usernames: Map[Int, String], passwords: Map[String, String])
  type DbReader[A] = Reader[Db, A]

  def findUsername(userId: Int): DbReader[Option[String]] = Reader { db =>
    db.usernames.get(userId)
  }

  def checkPassword(username: String, password: String): DbReader[Boolean] = Reader { db =>
    db.passwords.get(username).contains[String](password)
  }

  def checkLogin(userId: Int, password: String): DbReader[Boolean] =
    for {
      maybeUsername <- findUsername(userId)
      answer <- maybeUsername.map(checkPassword(_, password)).getOrElse(false.pure[DbReader])
    } yield answer
}
