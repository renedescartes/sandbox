package com.work.tdd.euler;

import com.google.common.base.Preconditions;

class RootFraction {
    int whole, num1, num2, den1, den2;

    RootFraction(int whole, int num1, int num2, int den1, int den2) {
        this.whole = whole;
        this.num1 = num1;
        this.num2 = num2;
        this.den1 = den1;
        this.den2 = den2;
    }

    RootFraction reciprocal() {
        return new RootFraction(whole, den1, den2, num1, num2);
    }

    RootFraction normalise() {
        Preconditions.checkArgument(num1 == 0);
        Preconditions.checkArgument(num2 != 0);
        int den = (den1 * den1 - den2)/num1;
        return null;
    }

    @Override
    public String toString() {
        return "RootFraction{" +
                "whole=" + whole +
                ", num1=" + num1 +
                ", num2=" + num2 +
                ", den1=" + den1 +
                ", den2=" + den2 +
                '}';
    }

}

public class Problem64 {

    public RootFraction transform(RootFraction t) {
        RootFraction one = t.reciprocal();
        return null;
    }
}
