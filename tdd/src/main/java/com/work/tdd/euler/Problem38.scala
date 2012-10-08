package com.work.tdd.euler

import java.lang.Integer;
import scala.collection.mutable;


object Problem38 {

  def isPanDigital(n : Long) : Boolean = {
    val s : String = n.toString();
    val digitSet = mutable.Set.empty[Char];
    s.foreach(c => digitSet+= c);
    return s.length == 9 && digitSet.size == 9 && !digitSet.contains('0');
  }

  def canBeConstructed(f : Integer, n : Long) : Boolean = {
    var s : String = "" + f;
    var i = 2;
    for (i <- 1 to 9) {
      s += (i * f);
      if (s.equals(n.toString())) {
        return true;
      }
    }
    return false;
  }

  def main(args : Array[String]) {

  }
}
