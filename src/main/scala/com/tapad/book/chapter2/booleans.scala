package com.tapad.book.chapter2

import cats._

object BooleanInstances {
  implicit object And extends Monoid[Boolean] {
    def empty: Boolean = true
    def combine(x: Boolean, y: Boolean): Boolean = x && y
  }
  implicit object Or extends Monoid[Boolean] {
    def empty: Boolean = false
    def combine(x: Boolean, y: Boolean): Boolean = x || y
  }
}