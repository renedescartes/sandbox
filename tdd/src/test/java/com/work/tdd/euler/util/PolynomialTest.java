package com.work.tdd.euler.util;

import org.testng.annotations.Test;

import static com.work.tdd.euler.util.Polynomial.findPolynomialFunction;
import static java.util.Arrays.asList;
import static org.testng.Assert.assertEquals;

public class PolynomialTest {
    @Test
    public void testBits() {
        assertEquals(findPolynomialFunction(asList(1D, 8D)), asList(7D, -6D));
        assertEquals(findPolynomialFunction(asList(1D, 8D, 27D)), asList(6D, -11D, 6D));
    }
}
