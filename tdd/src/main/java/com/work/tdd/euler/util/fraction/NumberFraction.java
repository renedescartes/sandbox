package com.work.tdd.euler.util.fraction;

import com.google.common.base.Preconditions;

abstract class NumberFraction<T extends Number> implements Fraction<T> {
    private final T numerator, denominator;

    public NumberFraction(T numerator, T denominator) {
        Preconditions.checkNotNull(denominator, "Denominator cannot be zero");
        this.numerator = numerator;
        this.denominator = denominator;
    }

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        NumberFraction fraction = (NumberFraction) o;

        if (denominator != null ? !denominator.equals(fraction.denominator) : fraction.denominator != null)
            return false;
        if (numerator != null ? !numerator.equals(fraction.numerator) : fraction.numerator != null) return false;

        return true;
    }

    @Override
    public final int hashCode() {
        int result = numerator != null ? numerator.hashCode() : 0;
        result = 31 * result + (denominator != null ? denominator.hashCode() : 0);
        return result;
    }

    @Override
    public final String toString() {
        return "NumberFraction {" + numerator +
                " / " + denominator +
                '}';
    }

    @Override
    public final T numerator() {
        return numerator;
    }

    @Override
    public final T denominator() {
        return denominator;
    }

}
