package com.work.parser.log


object MonadUnderstanding {

  def main(args: Array[String]) {
    val first = List(1, 2)
    val next = List(8, 9)

    for {
      i <- first
      j <- next
    }
    yield (println(i * j))


    first flatMap {
      f => next map {
        n => f * n
      }
    }
  }

}
