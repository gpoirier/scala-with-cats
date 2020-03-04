package com.tapad.book.chapter4

import cats.data._
import cats.effect._

object ExerciceReader {

  case class PostgresConfig(host: String, port: Int)
  case class MysqlConfig(host: String, port: Int)

  case class Person(id: Long, name: String, age: Int)
  case class Company(id: Long, name: String, mission: String)

  case class Organization(company: Company, employees: List[Person])

  trait PersonRepository[F[_]] {
    type Context
    type G[A] = ReaderT[F, Context, A]

    def get(id: Long): G[Person]
    def create(name: String, age: Int): G[Person]
    def list: G[List[Person]]
  }

  object PersonRepository extends PersonRepository[IO] {
    type Context = PostgresConfig
    def get(id: Long): G[Person] = ???
    def create(name: String, age: Int): G[Person] = ???
    def list: G[List[Person]] = ReaderT { config =>
      IO.pure(List(Person(1, config.host, config.port)))
    }
  }

  trait CompanyRepository[F[_]] {
    type Context
    type G[A] = ReaderT[F, Context, A]

    def get(id: Long): G[Company]
    def create(name: String, mission: String): G[Company]
    def list: G[List[Company]]
  }

  object CompanyRepository extends CompanyRepository[IO] {
    type Context = MysqlConfig
    def get(id: Long): G[Company] = ReaderT { config =>
      IO.pure(Company(id, "Company Name", config.host))
    }
    def create(name: String, mission: String): G[Company] = ???
    def list: G[List[Company]] = ???
  }

  trait Service[F[_]] {
    type Context
    type G[A] = ReaderT[F, Context, A]

    def org(companyId: Long): G[Organization]
  }

  object Service extends Service[IO] {
    type Context = (CompanyRepository.Context, PersonRepository.Context)
    def org(companyId: Long): G[Organization] =
      for {
        company <- CompanyRepository.get(companyId).local[Context](_._1)
        persons <- PersonRepository.list.local[Context](_._2)
      } yield Organization(company, persons)
  }
}
