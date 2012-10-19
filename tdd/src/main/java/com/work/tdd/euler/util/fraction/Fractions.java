package com.work.tdd.euler.util.fraction;

public class Fractions {

    public static Fraction longFraction(long num, long den) {
        return new LongFraction(num, den);
    }

    public static Fraction longFraction(long num) {
        return longFraction(num, 1);
    }
}
