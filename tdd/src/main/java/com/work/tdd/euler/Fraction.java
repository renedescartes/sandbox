package com.work.tdd.euler;

public class Fraction {
    final int numerator, denominator;

    Fraction(int numerator, int denominator) {
        this.numerator = numerator;
        this.denominator = denominator;
    }

    public Fraction add(Fraction b) {
        int den = Utility.gcd(1, 2);
        return null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Fraction fraction = (Fraction) o;

        if (denominator != fraction.denominator) return false;
        if (numerator != fraction.numerator) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = numerator;
        result = 31 * result + denominator;
        return result;
    }

    @Override
    public String toString() {
        return "Fraction {" + numerator +
                " / " + denominator +
                '}';
    }


}
