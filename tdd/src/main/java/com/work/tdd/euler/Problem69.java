package com.work.tdd.euler;

import com.google.common.base.Preconditions;
import com.work.tdd.euler.util.fraction.Fraction;
import org.testng.annotations.Test;

import java.util.Collections;
import java.util.Set;
import java.util.logging.Logger;

import static com.google.common.collect.Sets.newHashSet;
import static com.work.tdd.euler.util.fraction.Fractions.longFraction;
import static org.testng.Assert.assertEquals;

public class Problem69 {
    private static final Logger logger = Logger.getLogger(Problem45.class.getName());

    private static final Object[] factors = new Object[1000000];

    @SuppressWarnings("unchecked")
    public static Set<Long> cachedPrimeFactors(Long n) {
        if(factors[n.intValue()] == null) {
            factors[n.intValue()] = fastPrimeFactors(n);
        }
        return (Set<Long>) factors[n.intValue()];
    }

    public static Set<Long> fastPrimeFactors(Long n) {
        if(n == 1) {
            return Collections.emptySet();
        }
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
        Fraction fraction = longFraction(n);
        Fraction one = longFraction(1);
        for(Long l : factors) {
            fraction = fraction.multiply(one.subtract(longFraction(l).reciprocal()));
        }
        Preconditions.checkState(fraction.denominator().longValue() == 1L, "The denominator is not 1");
        return fraction.numerator().longValue();
    }

    public static long explore(long n) {
        double bestRatio = 0;
        long answer = 0;
        for(long i = 2; i < n; i++) {
            Long phi = phi(i);
            double phiRatio = i/phi;
            if(phiRatio > bestRatio) {
                logger.info("Prefer [" + i + "] with phi [" + phiRatio + "] over [" + answer + "] with phi [" + bestRatio + "]");
                bestRatio = phiRatio;
                answer = i;
            }
            if(i % 1000 == 0) {
                logger.fine("i = " + i + " phi = " + phi);
            }
        }
        return answer;
    }

    @Test
    public void testBits() {
        assertEquals(210, explore(10000));
    }

}
