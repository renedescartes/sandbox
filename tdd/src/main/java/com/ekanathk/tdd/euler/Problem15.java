package com.ekanathk.tdd.euler;

import org.junit.Test;

import java.math.BigInteger;
import java.util.logging.Logger;

import static org.testng.Assert.assertEquals;

/**
 * User: EkanathK
 * Date: 29/09/12
 */

public class Problem15 {

    int solutions = 0;
    static final Logger logger = Logger.getLogger(Problem15.class.getName());

    public static long routesSimple(int gridSize) {
        BigInteger f = factorial(gridSize);
        return factorial(2 * gridSize).divide(f).divide(f).longValue();
    }

    public static BigInteger factorial(int n) {
        BigInteger f = new BigInteger("1");
        for(int i = 2 ; i <= n; i++) {
            f = f.multiply(new BigInteger(""+ i));
        }
        return f;
    }

    @Test
    public void testSimple() {
        assertEquals(6, routesSimple(2));
        assertEquals(20, routesSimple(3));
        assertEquals(70, routesSimple(4));
        assertEquals(252, routesSimple(5));
        assertEquals(137846528820L, routesSimple(20));
    }
}