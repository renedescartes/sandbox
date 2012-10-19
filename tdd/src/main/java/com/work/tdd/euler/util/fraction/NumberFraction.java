package com.work.tdd.euler.util.fraction;

abstract class NumberFraction implements Fraction {
    final Number numerator, denominator;

    public NumberFraction(Number numerator, Number denominator) {
        this.numerator = numerator;
        this.denominator = denominator;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        NumberFraction fraction = (NumberFraction) o;

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
        return "Fraction {" + numerator +
                " / " + denominator +
                '}';
    }

    @Override
    public Number numerator() {
        return numerator;
    }

    @Override
    public Number denominator() {
        return denominator;
    }
}
