package com.work.tdd.euler.hard;

import com.work.tdd.euler.medium.Utility;
import org.testng.annotations.Test;

import java.util.logging.Logger;

import static org.testng.Assert.assertEquals;
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
        return -1;
    }

    @Test
    public void testSimple() {
        assertTrue(canBeSplit(12, 12, 6));
        assertEquals(kthMinimalNumber(6), 12);
        assertEquals(kthMinimalNumber(7), 12);
        assertEquals(kthMinimalNumber(8), 12);
        assertEquals(kthMinimalNumber(9), 15);
        assertEquals(kthMinimalNumber(10), 16);
        assertEquals(kthMinimalNumber(11), 16);
        assertEquals(kthMinimalNumber(12), 16);
    }

    private static boolean canBeSplit(int product, int sum, int numberOfTerms) {
        logger.info("product = [" + product + "] sum [" + sum + "] numberOfTerms [" + numberOfTerms + "]");
        //case of all 1s
        if (product == 1) {
            return sum == numberOfTerms;
        }
        if (numberOfTerms == 1) {
            return sum == product;
        }
        if (sum <= 0 || product <= 0 || sum < numberOfTerms) {
            return false;
        }
        Integer[] divisors = cachedDivisors(product);
        for (int numberOfOnes = numberOfTerms - 1; numberOfOnes >= 1; numberOfOnes--) {
            for (Integer divisor : divisors) {
                int newProduct = product / divisor;
                int newSum = sum - numberOfOnes - divisor;
                int newNumberOfTerms = numberOfTerms - numberOfOnes - 1;
                if (divisor != 1 && canBeSplit(newProduct, newSum, newNumberOfTerms)) {
                    return true;
                }
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
