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

    public static List<Long> firstNTerms(List<Long> polynomial) {
        List<Long> firstNTerms = new ArrayList<>();
        for (int i = 1; i <= polynomial.size(); i++) {
            firstNTerms.add(evaluatePolynomial(polynomial, (long) i));
        }
        return firstNTerms;
    }

    public static Long answer(List<Long> polynomial) {
        List<Long> firstNTerms = firstNTerms(polynomial);
        logger.info("First N terms " + firstNTerms);
        Long sum = 0L;
        for (int i = 1; i < firstNTerms.size(); i++) {
            List<Long> approximatePolynomial = findPolynomialFunction(firstNTerms.subList(0, i));
            logger.info("Sub list " + firstNTerms.subList(0, i) + " Polynomial function [" + prettyPrint(approximatePolynomial) + "]");
            Long firstIncorrectTerm = firstIncorrectTerm(approximatePolynomial, polynomial);
            sum += firstIncorrectTerm;
            logger.info("First incorrect term [" + firstIncorrectTerm + "] sum [" + sum + "]");
        }
        return sum;
    }

    private static Long firstIncorrectTerm(List<Long> approximatePolynomial, List<Long> polynomial) {
        for (long i = 1; i <= polynomial.size(); i++) {
            Long approximation = evaluatePolynomial(approximatePolynomial, i);
            if (!approximation.equals(evaluatePolynomial(polynomial, i))) {
                return approximation;
            }
        }
        return 0L;
    }

    @Test
    public void testSimple() {
        assertEquals(answer(asList(1L, -1L, 1L, -1L, 1L, -1L, 1L, -1L, 1L, -1L, 1L)).longValue(), 1433323928L);
    }

    @Test
    public void testBits() {
        assertEquals(answer(asList(1L, 0L, 0L, 0L)).longValue(), 74L);
        findPolynomialFunction(asList(1L, 683L, 44287L, 838861L, 8138021L, 51828151L, 247165843L, 954437177L, 3138105961L));
        firstIncorrectTerm(asList(1111L, 353L, -1334L, -1171L, 26L, 51L, -884L, -944L, -854L), asList(1L, -1L, 1L, -1L, 1L, -1L, 1L, -1L, 1L, -1L, 1L));
    }

}
