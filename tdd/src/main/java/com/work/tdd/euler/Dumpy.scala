package com.work.tdd.euler

import scala.collection.JavaConversions._
import scala.collection.mutable.ListBuffer

object Dumpy {

  def main(args : Array[String]) {
    var myList : java.util.List[Int] = ListBuffer(List(1,2,3): _*);
    println(Utility.permutes(myList));
  }

}
