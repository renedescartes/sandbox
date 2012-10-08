package com.work.tdd.euler

import org.scalatest.FunSuite

class TestProblem38 extends FunSuite {

  test("Simple") {
    assert(Problem38.isPanDigital(345126789));
    assert(!Problem38.isPanDigital(45126789));
    assert(!Problem38.isPanDigital(4512670));
    assert(Problem38.canBeConstructed(192, 192384576));
  }

}
