package com.ekanathk.parser.log

/**
 * Created by IntelliJ IDEA.
 * User: kannan
 * Date: 03/09/12
 * Time: 16:29
 * To change this template use File | Settings | File Templates.
 */

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