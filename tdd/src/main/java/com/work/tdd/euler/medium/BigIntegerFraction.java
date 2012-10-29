package com.work.tdd.euler.medium;

import java.math.BigInteger;

public class BigIntegerFraction implements Comparable<BigIntegerFraction> {
    final BigInteger numerator, denominator;

    public BigIntegerFraction(long numerator, long denominator) {
        this(new BigInteger("" + numerator), new BigInteger("" + denominator));
    }

    public BigIntegerFraction(BigInteger numerator, BigInteger denominator) {
        this.numerator = numerator;
        this.denominator = denominator;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BigIntegerFraction fraction = (BigIntegerFraction) o;

        if (denominator != null ? !denominator.equals(fraction.denominator) : fraction.denominator != null)
            return false;
        if (numerator != null ? !numerator.equals(fraction.numerator) : fraction.numerator != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = numerator != null ? numerator.hashCode() : 0;
        result = 31 * result + (denominator != null ? denominator.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "BigIntegerFraction {" + numerator +
                " / " + denominator +
                '}';
    }

    public BigIntegerFraction reduce() {
        BigInteger gcd = numerator.gcd(denominator);
        return gcd.equals(new BigInteger("1")) ? this : new BigIntegerFraction(numerator.divide(gcd), denominator.divide(gcd));
    }

    @Override
    public int compareTo(BigIntegerFraction o) {
        BigInteger lcm = lcm(denominator, o.denominator);
        return numerator.multiply(lcm.divide(denominator)).compareTo(o.numerator.multiply(lcm.divide(o.denominator)));
    }

    private static BigInteger lcm(BigInteger a, BigInteger b) {
        return a.multiply(b).divide(a.gcd(b));
    }
}
