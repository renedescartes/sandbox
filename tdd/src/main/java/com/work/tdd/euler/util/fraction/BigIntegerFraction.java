package com.work.tdd.euler.util.fraction;

import com.work.tdd.euler.Utility;

import java.math.BigInteger;

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
        BigIntegerFraction product = new BigIntegerFraction(numerator().longValue() * f.numerator().longValue(),
                denominator().longValue() * f.denominator().longValue());
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
        long den = Utility.lcm(denominator().longValue(), f.denominator().longValue());
        long num = equivalent(den).numerator().longValue() + f.equivalent(den).numerator().longValue();
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
        long f = requiredDenominator.longValue()/denominator().longValue();
        return new BigIntegerFraction(numerator().longValue() * f, requiredDenominator.longValue());
    }

    public Fraction negate() {
        return new BigIntegerFraction(-1 * numerator().longValue(), denominator().longValue());
    }

    public Fraction reciprocal() {
        return new BigIntegerFraction(denominator().longValue(), numerator().longValue()).reduce();
    }

    private BigInteger getBigIntNumerator() {
        return (BigInteger) numerator();
    }

    private BigInteger getBigIntDenominator() {
        return (BigInteger) denominator();
    }
}
