package com.work.tdd.euler.util.fraction;

public interface Fraction extends Comparable<Fraction> {

    public Fraction reduce();

    public Fraction multiply(Fraction f);

    public Fraction subtract(Fraction f);

    public Fraction divide(Fraction f);

    public Fraction add(Fraction f);

    public Number numerator();

    public Number denominator();

    public Fraction equivalent(Number denominator);

    public Fraction reciprocal();

    public String decimalValue();

}
