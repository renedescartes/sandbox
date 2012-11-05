package com.work.tdd.euler.hard;

import com.work.tdd.euler.medium.Utility;
import org.testng.annotations.Test;

import java.util.logging.Logger;

import static org.testng.Assert.assertTrue;

public class Problem88 {

    private static final Logger logger = Logger.getLogger(Problem88.class.getName());

    private static Integer[][] divisors = new Integer[100000][];

    public static int kthMinimalNumber(int k) {
        for (int current = k; current <= 2 * k; current++) {
            if (canBeSplit(current, current, k)) {
                return current;
            }
        }
        throw new RuntimeException("Cannot find minimal number");
    }

    @Test
    public void testSimple() {
        assertTrue(canBeSplit(12, 12, 6));
        //assertEquals(kthMinimalNumber(6), 12);
    }

    private static boolean canBeSplit(int productOfTerms, int sumOfTerms, int numberOfTerms) {
        logger.info("productOfTerms = " + productOfTerms + " sumOfTerms [" + sumOfTerms + "] numberOfTerms [" + numberOfTerms + "]");
        if (productOfTerms == 1) {
            return sumOfTerms == 0 && numberOfTerms == 0;
        }
        if (numberOfTerms == 0 || sumOfTerms <= 0) {
            return false;
        }
        Integer[] divisors = cachedDivisors(productOfTerms);
        for (Integer divisor : divisors) {
            if (canBeSplit(productOfTerms / divisor, sumOfTerms - divisor, numberOfTerms - 1)) {
                return true;
            }
        }
        return false;
    }

    private static Integer[] cachedDivisors(Integer n) {
        if (divisors[n] == null) {
            divisors[n] = Utility.properDivisors(n);
        }
        return divisors[n];
    }

}
