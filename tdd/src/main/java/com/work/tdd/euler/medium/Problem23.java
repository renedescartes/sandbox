package com.work.tdd.euler.medium;

import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

/**
 * User: renedescartes
 * Date: 29/09/12
 */
public class Problem23 {

    private static final Logger logger = Logger.getLogger(Problem23.class.getName());
    private static Map<Integer, Boolean> cache = new HashMap<>();

    public static int computeSum() {
        int sum = 0;
        for (int i = 1; i <= 28123; i++) {
            if (!isSumOfTwoAbundantNumbers(i)) {
                logger.info("Number [" + i + "] cannot be expressed as sum of two abundant numbers");
                sum += i;
            }
        }
        return sum;
    }

    @Test
    public void testSimple() {
        System.out.println(computeSum());
    }

    protected static boolean isSumOfTwoAbundantNumbers(int n) {
        for (int i = 1; i < (n / 2) + 1; i++) {
            if (isAbundantNumber(i) && isAbundantNumber(n - i)) {
                logger.fine("Number [" + n + "] is abundant sum of [" + i + "] and [" + (n - i) + "]");
                return true;
            }
        }
        return false;
    }

    protected static boolean isAbundantNumber(int n) {
        if (!cache.containsKey(n)) {
            cache.put(n, Utility.summation(Utility.properDivisors(n)) > n);
        }
        return cache.get(n);
    }


}
