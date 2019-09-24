package com.tapad.book.chapter3

import org.scalatest._

class CodecSpec extends WordSpec with Matchers {
  "Codec" should {
    "encode strings" in {
      Codec.encode("hello") should be ("hello")
    }
    "decode strings" in {
      Codec.decode[String]("world") should be ("world")
    }
    "encode ints" in {
      Codec.encode(42) should be ("42")
    }
    "decode ints" in {
      Codec.decode[Int]("23") should be (23)
    }
    "encode boolean" in {
      Codec.encode(true) should be ("true")
    }
    "decode boolean" in {
      Codec.decode[Boolean]("false") should be (false)
    }
    "encode doubles" in {
      Codec.encode(42.0) should be ("42.0")
    }
    "decode doubles" in {
      Codec.decode[Double]("23") should be (23.0)
    }
    "encode box" in {
      Codec.encode(Box(42)) should be ("42")
    }
    "decode box" in {
      Codec.decode[Box[Double]]("23") should be (Box(23.0))
    }
  }
}