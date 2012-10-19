package com.work.tdd.euler;

import org.testng.annotations.Test;

import java.util.Set;
import java.util.logging.Logger;

import static com.google.common.collect.Sets.newHashSet;

public class Problem69 {
    private static final Logger logger = Logger.getLogger(Problem45.class.getName());

    private static final Object[] factors = new Object[1000000];

    public static Set<Long> cachedPrimeFactors(Long n) {
        if(factors[n.intValue()] == null) {
            factors[n.intValue()] = fastPrimeFactors(n);
        }
        return (Set<Long>) factors[n.intValue()];
    }

    public static Set<Long> fastPrimeFactors(Long n) {
        if (n % 2 == 0) {
            Set<Long> factors = newHashSet(cachedPrimeFactors(n / 2));
            factors.add(2L);
            return factors;
        }
        long a;
        for (a = (long) Math.sqrt(n); a <= n; a++) {
            if (Utility.isPerfectSquare(a * a - n)) {
                long b = (long) Math.sqrt(a * a - n);
                if (a + b < n) {
                    Set<Long> factors = newHashSet();
                    factors.addAll(cachedPrimeFactors(a + b));
                    factors.addAll(cachedPrimeFactors(a - b));
                    return factors;
                }
            }
        }
        return newHashSet(n);
    }

    public static long phi(long n) {
        Set<Long> factors = cachedPrimeFactors(n);
        BigIntegerFraction f = new BigIntegerFraction(n , 1);
        for(Long l : factors) {
        }
        return -1;
    }

    public static long explore(long n) {
        double ratio = 0;
        long answer = 0;
        for(long i = 2; i < n; i++) {
            logger.info("i = " + i + " primeFactors = " + cachedPrimeFactors(i));
        }
        return -1;
    }

    @Test
    public void testSimple() {
        System.out.println(explore(1000000));
    }

}
