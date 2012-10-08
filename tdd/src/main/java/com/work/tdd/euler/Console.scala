package com.work.tdd.euler

import collection.mutable

class Console {

  def main(args : Array[String]) {
    val s : String = "13425";
    val digitSet = mutable.Set.empty[Char];
    s.foreach(c => digitSet+= c);
    println(digitSet);
  }

}
