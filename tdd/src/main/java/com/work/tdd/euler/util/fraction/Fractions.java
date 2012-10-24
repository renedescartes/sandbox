package com.work.tdd.euler.util.fraction;

import java.math.BigInteger;

public class Fractions {

    public static Fraction longFraction(long num, long den) {
        return new LongFraction(num, den);
    }

    public static Fraction longFraction(long num) {
        return longFraction(num, 1);
    }

    public static Fraction bigIntegerFraction(long num, long den) {
        return new BigIntegerFraction(new BigInteger("" + num), new BigInteger("" + den));
    }

    public static Fraction bigIntegerFraction(long num) {
        return bigIntegerFraction(num, 1);
    }
}
