package com.work.tdd.euler.util;

import org.testng.annotations.Test;

import static com.work.tdd.euler.util.Polynomial.findPolynomialFunction;
import static java.util.Arrays.asList;
import static org.testng.Assert.assertEquals;

public class PolynomialTest {
    @Test
    public void testBits() {
        assertEquals(findPolynomialFunction(asList(1L, 8L, 15L)), asList(7.0, -6.0));
        assertEquals(findPolynomialFunction(asList(1L, 8L, 27L, 58L)), asList(6.0, -11.0, 6.0));
    }
}
