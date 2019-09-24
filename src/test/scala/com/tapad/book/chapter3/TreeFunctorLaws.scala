package com.tapad.book.chapter3

import cats.implicits._
import cats.laws.discipline.FunctorTests

import org.scalacheck._
import org.scalatest.FunSuite
import org.typelevel.discipline.scalatest.Discipline

class TreeFunctorLaws extends FunSuite with Discipline {
  def randomTree[A](implicit gen: Gen[A]): Gen[Tree[A]] = {
    val genLeaf: Gen[Tree[A]] =
      for {
        a <- gen
      } yield Leaf(a)

    def genNode = for {
      left <- genTree
      right <- genTree
    } yield Branch[A](left, right)

    def genTree: Gen[Tree[A]] = Gen.oneOf(genLeaf, Gen.lzy(genNode))

    genTree
  }

  implicit def posNum: Gen[Int] = Gen.posNum[Int]
  implicit def arbTree[A: Gen]: Arbitrary[Tree[A]] = Arbitrary(randomTree)

  checkAll("Tree.FunctorLaws",  FunctorTests[Tree].functor[Int, Double, String])
}