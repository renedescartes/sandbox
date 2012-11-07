package com.work.tdd.euler.hard;

import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

import static com.work.tdd.euler.util.Polynomial.*;

public class Problem101 {

    private static final Logger logger = Logger.getLogger(Problem101.class.getName());

    public static List<Long> firstNTerms(List<Double> polynomial) {
        List<Long> firstNTerms = new ArrayList<>();
        for (int i = 1; i <= polynomial.size(); i++) {
            firstNTerms.add(evaluatePolynomial(polynomial, (long) i));
        }
        return firstNTerms;
    }

    public static Long answer(List<Double> polynomial) {
        List<Long> firstNTerms = firstNTerms(polynomial);
        logger.info("First N terms " + firstNTerms);
        for (int i = 1; i < firstNTerms.size(); i++) {
            List<Long> polynomialFunction = findPolynomialFunction(firstNTerms.subList(0, i));
            logger.info("Sub list " + firstNTerms.subList(0, i) + " Polynomial function [" + prettyPrint(polynomialFunction) + "]");
        }
        return 0L;
    }

    @Test
    public void testSimple() {
        answer(Arrays.asList(1d, -1d, 1d, -1d, 1d, -1d, 1d, -1d, 1d, -1d, 1d));
    }

}
