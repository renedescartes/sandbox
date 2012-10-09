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
    var s : String = f.toString();
    for (i <- 2 to 9) {
      s += (i * f);
      if (s.equals(n.toString())) {
        return true;
      }
    }
    return false;
  }

  def numberPermutations(n : Integer) : List[Integer] = {
    return Nil;
  }

  def digits(n : Integer) : List[Int] = {
    var l : List[Int] = Nil
    n.toString.foreach(c => l ::= (c.toInt - '0'))
    return l.reverse
  }

  def main(args : Array[String]) {
    for( i <- 2 to 9) {
      println(i);
    }
  }
}
