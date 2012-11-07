package com.work.tdd.euler.util;

import org.testng.annotations.Test;

import static com.work.tdd.euler.util.Polynomial.findPolynomialFunction;
import static java.util.Arrays.asList;
import static org.testng.Assert.assertEquals;

public class PolynomialTest {
    @Test
    public void testBits() {
        assertEquals(findPolynomialFunction(asList(1L, 8L)), asList(7L, -6L));
        assertEquals(findPolynomialFunction(asList(1L, 8L, 27L)), asList(6L, -11L, 6L));
    }
}
