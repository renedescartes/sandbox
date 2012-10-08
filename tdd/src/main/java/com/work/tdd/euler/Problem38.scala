package com.work.tdd.euler

import scala.collection.mutable;


object Problem38 {

  def isPanDigital(n : Long) : Boolean = {
    val s : String = n.toString();
    val digitSet = mutable.Set.empty[Char];
    s.foreach(c => digitSet+= c);
    return s.length == 9 && digitSet.size == 9 && !digitSet.contains('0');
  }

  def main(args : Array[String]) {

  }
}
