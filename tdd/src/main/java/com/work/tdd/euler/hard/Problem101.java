package com.work.tdd.euler.hard;

import java.util.ArrayList;
import java.util.List;

import static com.work.tdd.euler.util.Polynomial.evaluatePolynomial;
import static com.work.tdd.euler.util.Polynomial.findPolynomialFunction;

public class Problem101 {

    public static List<Long> firstNTerms(List<Double> polynomial) {
        List<Long> firstNTerms = new ArrayList<>();
        for (int i = 1; i <= polynomial.size(); i++) {
            firstNTerms.add(evaluatePolynomial(polynomial, (long) i));
        }
        return firstNTerms;
    }

    public static Long answer(List<Double> polynomial) {
        List<Long> firstNTerms = firstNTerms(polynomial);
        for (int i = 1; i < firstNTerms.size(); i++) {
            List<Double> polynomialFunction = findPolynomialFunction(firstNTerms.subList(0, i));
        }
        return 0L;
    }

}
