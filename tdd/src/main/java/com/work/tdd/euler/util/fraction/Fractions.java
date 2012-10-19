package com.work.tdd.euler.util.fraction;

public class Fractions {

    public static Fraction longFraction(long num, long den) {
        return new LongFraction(num, den);
    }
}
