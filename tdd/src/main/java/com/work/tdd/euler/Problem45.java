package com.work.tdd.euler;

import org.testng.annotations.Test;

import java.util.logging.Logger;

import static org.testng.Assert.*;

public class Problem45 {

    private static final Logger logger = Logger.getLogger(Problem45.class.getName());

    private static boolean isTriangular(long number) {
        return isPerfectSquare((8 * number) + 1);
    }

    private static boolean isPerfectSquare(long number) {
        long sqrt = (long) Math.sqrt(number);
        return sqrt * sqrt == number;
    }

    private static boolean isPentagonal(long number) {
        long check = (24 * number) + 1;
        return isPerfectSquare(check) && (Math.sqrt(check) % 6 == 5);
    }

    public static long findCommon(Long from) {
        long i = 1;
        while (true) {
            long p = (i * ( (2 * i) - 1));
            logger.info("P = [" + p + "]");
            if(p > from && isPentagonal(p) && isTriangular(p)) {
                return p;
            }
            i++;
        }
    }

    @Test
    public void testSimple() {
        assertTrue(isPerfectSquare(64));
        assertTrue(isPerfectSquare(49));
        assertTrue(isPerfectSquare(1));
        assertFalse(isPerfectSquare(3));
        assertFalse(isPerfectSquare(8));
        assertTrue(isPentagonal(40755));
        assertTrue(isTriangular(40755));
        assertEquals(1533776805, findCommon(40755L));
    }
}
