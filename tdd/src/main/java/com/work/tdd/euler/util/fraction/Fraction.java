package com.work.tdd.euler.util.fraction;

public interface Fraction<T extends Number> extends Comparable<Fraction<T>> {

    public Fraction<T> reduce();

    public Fraction<T> multiply(Fraction<T> f);

    public Fraction<T> subtract(Fraction<T> f);

    public Fraction<T> divide(Fraction<T> f);

    public Fraction<T> add(Fraction<T> f);

    public T numerator();

    public T denominator();

    public Fraction<T> equivalent(T denominator);

    public Fraction<T> reciprocal();

    public String decimalValue();

}
