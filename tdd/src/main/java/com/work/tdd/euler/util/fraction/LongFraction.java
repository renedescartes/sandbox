package com.work.tdd.euler.util.fraction;

import com.work.tdd.euler.Utility;

class LongFraction extends NumberFraction<Long> {

    public LongFraction(Long numerator, Long denominator) {
        super(numerator, denominator);
    }

    @Override
    public Fraction reduce() {
        long gcd = Utility.gcd(numerator(), denominator());
        return gcd == 1 ? this : new LongFraction(numerator() / gcd, denominator() / gcd);
    }

    @Override
    public Fraction multiply(Fraction f) {
        LongFraction product = new LongFraction(numerator() * f.numerator().longValue(),
                denominator() * f.denominator().longValue());
        return product.reduce();
    }

    @Override
    public Fraction subtract(Fraction f) {
        return add(((LongFraction) f).negate());
    }

    @Override
    public Fraction divide(Fraction f) {
        return multiply(f.reciprocal());
    }

    @Override
    public Fraction add(Fraction f) {
        long den = Utility.lcm(denominator(), f.denominator().longValue());
        long num = equivalent(den).numerator().longValue() + f.equivalent(den).numerator().longValue();
        return new LongFraction(num, den).reduce();
    }

    @Override
    public Fraction equivalent(Long requiredDenominator) {
        long f = requiredDenominator / denominator();
        return new LongFraction(numerator() * f, requiredDenominator);
    }

    public Fraction negate() {
        return new LongFraction(-1 * numerator(), denominator());
    }

    public Fraction reciprocal() {
        return new LongFraction(denominator(), numerator()).reduce();
    }

    @Override
    public String decimalValue() {
        return "" + (double) numerator() / (double) denominator();
    }

    @Override
    public int compareTo(Fraction<? extends Number> other) {
        if (!(other instanceof LongFraction)) {
            throw new IllegalStateException("Cant compare to other fractions");
        }
        LongFraction o = (LongFraction) other;
        Long lcm = Utility.lcm(denominator(), o.denominator());
        Long value1 = (Long) equivalent(lcm).numerator();
        Long value2 = (Long) o.equivalent(lcm).numerator();
        return value1.compareTo(value2);
    }
}
