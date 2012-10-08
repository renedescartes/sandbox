package com.work.tdd.euler;

import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.logging.Logger;

import static org.testng.Assert.assertEquals;

/**
 * User: renedescartes
 * Date: 29/09/12
 */
public class UtilityTest {

    private static final Logger logger = Logger.getLogger(UtilityTest.class.getName());
    @Test
    public void testDivisors() {
        Integer[] divisors = Utility.properDivisors(220);
        logger.info("Divisors " + Arrays.toString(divisors));
        assertEquals(divisors, new Integer[] {1, 2, 4, 5, 10, 11, 20, 22, 44, 55, 110});
        assertEquals(284, Utility.summation(divisors).intValue());
    }
}
