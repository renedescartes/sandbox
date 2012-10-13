package com.work.tdd.euler;

import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

/**
 * User: renedescartes
 * Date: 13/10/12
 */
public class Problem48 {

    private static int DIGITS = 10;

    private static long lastDigitsForPower(long n) {
        long product = lastDigits(n);
        for (int i = 1; i < n; i++) {
            product = lastDigits(product * n);
        }
        return product;
    }

    private static long sumOfDigits(int n) {
        long sum = 0;
        for (int i = 1; i <= n; i++) {
            sum += lastDigitsForPower(i);
        }
        return lastDigits(sum);
    }

    private static long lastDigits(Long n) {
        String s = n.toString();
        int beginIndex = Math.max(s.length() - DIGITS, 0);
        return Long.parseLong(s.substring(beginIndex));
    }

    @Test
    public void testSimple() {
        assertEquals(2345123345L, lastDigits(542345123345L));
        assertEquals(lastDigitsForPower(3), 27L);
        assertEquals(lastDigitsForPower(10), 0L);
        assertEquals(sumOfDigits(10), 405071317L);
        assertEquals(sumOfDigits(1000), 9110846700L);
    }
}
