package com.work.tdd.euler.util.fraction;

import com.work.tdd.euler.Utility;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;

class BigIntegerFraction extends NumberFraction {

    public BigIntegerFraction(BigInteger numerator, BigInteger denominator) {
        super(numerator, denominator);
    }

    @Override
    public Fraction reduce() {
        BigInteger gcd = getBigIntNumerator().gcd(getBigIntDenominator());
        return gcd.equals(BigInteger.ONE) ? this : new BigIntegerFraction(getBigIntNumerator().divide(gcd), getBigIntDenominator().divide(gcd));
    }

    @Override
    public Fraction multiply(Fraction f) {
        BigIntegerFraction bf = (BigIntegerFraction) f;
        BigIntegerFraction product = new BigIntegerFraction(getBigIntNumerator().multiply(bf.getBigIntNumerator()),
                getBigIntDenominator().multiply(bf.getBigIntDenominator()));
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
        BigInteger den = lcm(getBigIntDenominator(), bf.getBigIntDenominator());
        BigInteger num1 = ((BigIntegerFraction) equivalent(den)).getBigIntNumerator();
        BigInteger num2 = ((BigIntegerFraction) f.equivalent(den)).getBigIntNumerator();
        BigInteger num = num1.add(num2);
        return new BigIntegerFraction(num, den).reduce();
    }

    @Override
    public int compareTo(Fraction o) {
        if(!(o instanceof BigIntegerFraction)) {
            throw new IllegalStateException("Cant compare to other fractions");
        }
        Long lcm = Utility.lcm(denominator().longValue(), o.denominator().longValue());
        Comparable value1 = (Comparable) equivalent(lcm).numerator();
        Comparable value2 = (Comparable) o.equivalent(lcm).numerator();
        return value1.compareTo(value2);
    }

    @Override
    public Fraction equivalent(Number requiredDenominator) {
        BigInteger den = (BigInteger) requiredDenominator;
        BigInteger factor = den.divide(getBigIntDenominator());
        return new BigIntegerFraction(getBigIntNumerator().multiply(factor), den);
    }

    public Fraction negate() {
        return new BigIntegerFraction(getBigIntNumerator().negate(), getBigIntDenominator());
    }

    public Fraction reciprocal() {
        return new BigIntegerFraction(getBigIntDenominator(), getBigIntNumerator()).reduce();
    }

    private BigInteger getBigIntNumerator() {
        return (BigInteger) numerator();
    }

    private BigInteger getBigIntDenominator() {
        return (BigInteger) denominator();
    }

    private static BigInteger lcm(BigInteger n1, BigInteger n2) {
        return n1.multiply(n2).divide(n1.gcd(n2));
    }

    @Override
    public String decimalValue() {
        BigDecimal b1 = new BigDecimal(getBigIntNumerator());
        BigDecimal b2 = new BigDecimal(getBigIntDenominator());
        return b1.divide(b2, 15, RoundingMode.DOWN).toString();
    }
}
