package com.work.tdd.euler.util.fraction;

import com.work.tdd.euler.Utility;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;

class BigIntegerFraction extends NumberFraction<BigInteger> {

    public BigIntegerFraction(BigInteger numerator, BigInteger denominator) {
        super(numerator, denominator);
    }

    @Override
    public Fraction reduce() {
        BigInteger gcd = numerator().gcd(denominator());
        return gcd.equals(BigInteger.ONE) ? this : new BigIntegerFraction(numerator().divide(gcd), denominator().divide(gcd));
    }

    @Override
    public Fraction multiply(Fraction f) {
        BigIntegerFraction bf = (BigIntegerFraction) f;
        BigIntegerFraction product = new BigIntegerFraction(numerator().multiply(bf.numerator()),
                denominator().multiply(bf.denominator()));
        return product.reduce();
    }

    @Override
    public Fraction subtract(Fraction f) {
        return add(((BigIntegerFraction) f).negate());
    }

    @Override
    public Fraction divide(Fraction f) {
        return multiply(f.reciprocal());
    }

    @Override
    public Fraction add(Fraction f) {
        BigIntegerFraction bf = (BigIntegerFraction) f;
        BigInteger den = lcm(denominator(), bf.denominator());
        BigInteger num1 = ((BigIntegerFraction) equivalent(den)).numerator();
        BigInteger num2 = ((BigIntegerFraction) f.equivalent(den)).numerator();
        BigInteger num = num1.add(num2);
        return new BigIntegerFraction(num, den).reduce();
    }

    @Override
    public Fraction equivalent(Number requiredDenominator) {
        BigInteger den = (BigInteger) requiredDenominator;
        BigInteger factor = den.divide(denominator());
        return new BigIntegerFraction(numerator().multiply(factor), den);
    }

    public Fraction negate() {
        return new BigIntegerFraction(numerator().negate(), denominator());
    }

    public Fraction reciprocal() {
        return new BigIntegerFraction(denominator(), numerator()).reduce();
    }

    private static BigInteger lcm(BigInteger n1, BigInteger n2) {
        return n1.multiply(n2).divide(n1.gcd(n2));
    }

    @Override
    public String decimalValue() {
        BigDecimal b1 = new BigDecimal(numerator());
        BigDecimal b2 = new BigDecimal(denominator());
        return b1.divide(b2, 150, RoundingMode.DOWN).toString();
    }

    @Override
    public int compareTo(Object other) {
        if (!(other instanceof BigIntegerFraction)) {
            throw new IllegalStateException("Cant compare to other fractions");
        }
        BigIntegerFraction o = (BigIntegerFraction) other;
        Long lcm = Utility.lcm(denominator().longValue(), o.denominator().longValue());
        Comparable value1 = (Comparable) equivalent(lcm).numerator();
        Comparable value2 = (Comparable) o.equivalent(lcm).numerator();
        return value1.compareTo(value2);
    }
}
