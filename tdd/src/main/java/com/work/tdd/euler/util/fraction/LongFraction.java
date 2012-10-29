package com.work.tdd.euler.util.fraction;

import com.work.tdd.euler.medium.Utility;

class LongFraction extends NumberFraction<Long> {

    public LongFraction(Long numerator, Long denominator) {
        super(numerator, denominator);
    }

    @Override
    public Fraction<Long> reduce() {
        long gcd = Utility.gcd(numerator(), denominator());
        return gcd == 1 ? this : new LongFraction(numerator() / gcd, denominator() / gcd);
    }

    @Override
    public Fraction<Long> multiply(Fraction<Long> f) {
        LongFraction product = new LongFraction(numerator() * f.numerator(),
                denominator() * f.denominator());
        return product.reduce();
    }

    @Override
    public Fraction<Long> subtract(Fraction<Long> f) {
        return add(((LongFraction) f).negate());
    }

    @Override
    public Fraction<Long> divide(Fraction<Long> f) {
        return multiply(f.reciprocal());
    }

    @Override
    public Fraction<Long> add(Fraction<Long> f) {
        Long den = Utility.lcm(denominator(), f.denominator());
        Long num = equivalent(den).numerator() + f.equivalent(den).numerator();
        return new LongFraction(num, den).reduce();
    }

    @Override
    public Fraction<Long> equivalent(Long requiredDenominator) {
        long f = requiredDenominator / denominator();
        return new LongFraction(numerator() * f, requiredDenominator);
    }

    public Fraction<Long> negate() {
        return new LongFraction(-1 * numerator(), denominator());
    }

    public Fraction<Long> reciprocal() {
        return new LongFraction(denominator(), numerator()).reduce();
    }

    @Override
    public String decimalValue() {
        return "" + (double) numerator() / (double) denominator();
    }

    @Override
    public int compareTo(Fraction<Long> o) {
        Long lcm = Utility.lcm(denominator(), o.denominator());
        Long value1 = equivalent(lcm).numerator();
        Long value2 = o.equivalent(lcm).numerator();
        return value1.compareTo(value2);
    }

}
