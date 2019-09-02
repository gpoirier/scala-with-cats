package com.tapad.book.chapter2

import cats.Monoid

case class Order(totalCost: Double, quantity: Double)
case class Order1(totalCost: Double, quantity: Double)

object Order {
  implicit object OrderMonoid extends Monoid[Order] {
    override def empty: Order = Order(0, 0)
    override def combine(x: Order, y: Order): Order =
      Order(x.totalCost + y.totalCost, x.quantity + y.quantity)
  }
  object TupleOrderMonoid extends Monoid[Order] {
    import cats.implicits._

    private type Tuple = (Double, Double)
    private val tupleM = Monoid[Tuple]

    def from: Tuple => Order = (Order.apply _).tupled
    def to: Order => Tuple = order => Order.unapply(order).get

    override def empty: Order = from(tupleM.empty)
    override def combine(x: Order, y: Order): Order = from(to(x) |+| to(y))
  }
}
