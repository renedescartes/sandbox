package com.ekanathk.tdd.euler;

import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import static com.ekanathk.tdd.euler.Utility.properDivisors;
import static com.ekanathk.tdd.euler.Utility.summation;
import static org.testng.Assert.assertFalse;

/**
 * User: EkanathK
 * Date: 29/09/12
 */
public class Problem23 {

    private static final Logger logger = Logger.getLogger(Problem23.class.getName());
    private static Map<Integer, Boolean> cache = new HashMap<>();
    public static int computeSum() {
        int sum = 0;
        for(int i = 24; i < 28123; i++) {
            if(isSumOfTwoAbundantNumbers(i)) {
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
        for(int i = 1; i < (n/2) + 1; i++) {
            if(isAbundantNumber(i) && isAbundantNumber(n-i)) {
                logger.info("Number [" + n + "] is abundant sum of [" + i + "] and [" + (n-i) + "]");
                return true;
            }
        }
        return false;
    }

    protected static boolean isAbundantNumber(int n) {
        if(!cache.containsKey(n)) {
            cache.put(n, summation(properDivisors(n)) > n);
        }
        return cache.get(n);
    }


}
