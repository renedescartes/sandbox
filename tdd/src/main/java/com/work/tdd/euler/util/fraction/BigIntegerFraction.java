package com.work.tdd.euler.util.fraction;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;

class BigIntegerFraction extends NumberFraction<BigInteger> {

    public BigIntegerFraction(BigInteger numerator, BigInteger denominator) {
        super(numerator, denominator);
    }

    @Override
    public Fraction<BigInteger> reduce() {
        BigInteger gcd = numerator().gcd(denominator());
        return gcd.equals(BigInteger.ONE) ? this : new BigIntegerFraction(numerator().divide(gcd), denominator().divide(gcd));
    }

    @Override
    public Fraction<BigInteger> multiply(Fraction<BigInteger> f) {
        BigIntegerFraction bf = (BigIntegerFraction) f;
        BigIntegerFraction product = new BigIntegerFraction(numerator().multiply(bf.numerator()),
                denominator().multiply(bf.denominator()));
        return product.reduce();
    }

    @Override
    public Fraction<BigInteger> subtract(Fraction<BigInteger> f) {
        return add(((BigIntegerFraction) f).negate());
    }

    @Override
    public Fraction<BigInteger> divide(Fraction<BigInteger> f) {
        return multiply(f.reciprocal());
    }

    @Override
    public Fraction<BigInteger> add(Fraction<BigInteger> f) {
        BigIntegerFraction bf = (BigIntegerFraction) f;
        BigInteger den = lcm(denominator(), bf.denominator());
        BigInteger num1 = equivalent(den).numerator();
        BigInteger num2 = f.equivalent(den).numerator();
        BigInteger num = num1.add(num2);
        return new BigIntegerFraction(num, den).reduce();
    }

    @Override
    public Fraction<BigInteger> equivalent(BigInteger requiredDenominator) {
        BigInteger factor = requiredDenominator.divide(denominator());
        return new BigIntegerFraction(numerator().multiply(factor), requiredDenominator);
    }

    public Fraction<BigInteger> negate() {
        return new BigIntegerFraction(numerator().negate(), denominator());
    }

    public Fraction<BigInteger> reciprocal() {
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
    public int compareTo(Fraction<? extends Number> other) {
        if (!(other instanceof BigIntegerFraction)) {
            throw new IllegalStateException("Cant compare to other fractions");
        }
        BigIntegerFraction o = (BigIntegerFraction) other;
        BigInteger lcm = lcm(denominator(), o.denominator());
        BigInteger value1 = equivalent(lcm).numerator();
        BigInteger value2 = o.equivalent(lcm).numerator();
        return value1.compareTo(value2);
    }
}
