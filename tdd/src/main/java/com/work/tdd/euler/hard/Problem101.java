package com.work.tdd.euler.hard;

import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import static com.work.tdd.euler.util.Polynomial.*;
import static java.util.Arrays.asList;
import static org.testng.Assert.assertEquals;

public class Problem101 {

    private static final Logger logger = Logger.getLogger(Problem101.class.getName());

    public static List<Double> firstNTerms(List<Double> polynomial) {
        List<Double> firstNTerms = new ArrayList<>();
        for (int i = 1; i <= polynomial.size(); i++) {
            firstNTerms.add(evaluatePolynomial(polynomial, (long) i));
        }
        return firstNTerms;
    }

    public static Double answer(List<Double> polynomial) {
        List<Double> firstNTerms = firstNTerms(polynomial);
        logger.info("First N terms " + firstNTerms);
        Double sum = 0d;
        for (int i = 1; i < firstNTerms.size(); i++) {
            List<Double> approximatePolynomial = findPolynomialFunction(firstNTerms.subList(0, i));
            logger.info("Sub list " + firstNTerms.subList(0, i) + " Polynomial function [" + prettyPrint(approximatePolynomial) + "]");
            Double firstIncorrectTerm = firstIncorrectTerm(approximatePolynomial, polynomial);
            sum += firstIncorrectTerm;
            logger.info("First incorrect term [" + firstIncorrectTerm + "] sum [" + sum + "]");
        }
        return sum;
    }

    private static Double firstIncorrectTerm(List<Double> approximatePolynomial, List<Double> polynomial) {
        for (long i = 1; i <= polynomial.size(); i++) {
            Double approximation = evaluatePolynomial(approximatePolynomial, i);
            if (!approximation.equals(evaluatePolynomial(polynomial, i))) {
                return approximation;
            }
        }
        return 0D;
    }

    @Test
    public void testSimple() {
        assertEquals(answer(asList(1D, -1D, 1D, -1D, 1D, -1D, 1D, -1D, 1D, -1D, 1D)), 1433323928L);
    }

    @Test
    public void testBits() {
        assertEquals(answer(asList(1D, 0D, 0D, 0D)), 74D);
        findPolynomialFunction(asList(1D, 683D, 44287D, 838861D, 8138021D, 51828151D, 247165843D, 954437177D, 3138105961D));
        firstIncorrectTerm(asList(1111D, 353D, -1334D, -1171D, 26D, 51D, -884D, -944D, -854D), asList(1D, -1D, 1D, -1D, 1D, -1D, 1D, -1D, 1D, -1D, 1D));
    }

}
