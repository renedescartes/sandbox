package com.work.tdd.euler.util.fraction;

import com.work.tdd.euler.Utility;

class LongFraction extends NumberFraction {

    public LongFraction(Long numerator, Long denominator) {
        super(numerator, denominator);
    }

    @Override
    public Fraction reduce() {
        long gcd = Utility.gcd(numerator().longValue(), denominator().longValue());
        return gcd == 1 ? this : new LongFraction(numerator.longValue()/gcd, denominator.longValue()/gcd);
    }

    @Override
    public Fraction multiply(Fraction f) {
        LongFraction product = new LongFraction(numerator().longValue() * f.numerator().longValue(),
                denominator().longValue() * f.denominator().longValue());
        return product.reduce();
    }

    @Override
    public Fraction subtract(Fraction f) {
        return add(((LongFraction) f).negate());
    }

    @Override
    public Fraction divide(Fraction f) {
        return multiply(((LongFraction)f).reciprocal());
    }

    @Override
    public Fraction add(Fraction f) {
        long den = Utility.lcm(denominator().longValue(), f.denominator().longValue());
        long num = equivalent(den).numerator().longValue() + f.equivalent(den).numerator().longValue();
        return new LongFraction(num, den).reduce();
    }

    @Override
    public Fraction equivalent(Number requiredDenominator) {
        long f = requiredDenominator.longValue()/denominator().longValue();
        return new LongFraction(numerator().longValue() * f, requiredDenominator.longValue());
    }

    public Fraction negate() {
        return new LongFraction(-1 * numerator().longValue(), denominator().longValue());
    }

    public Fraction reciprocal() {
        return new LongFraction(denominator().longValue(), numerator().longValue()).reduce();
    }
}
