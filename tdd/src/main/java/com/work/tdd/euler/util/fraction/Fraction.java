package com.work.tdd.euler.util.fraction;

public interface Fraction<T extends Number> extends Comparable<Fraction<? extends Number>> {

    public Fraction reduce();

    public Fraction multiply(Fraction f);

    public Fraction subtract(Fraction f);

    public Fraction divide(Fraction f);

    public Fraction add(Fraction f);

    public T numerator();

    public T denominator();

    public Fraction equivalent(Number denominator);

    public Fraction reciprocal();

    public String decimalValue();

}
