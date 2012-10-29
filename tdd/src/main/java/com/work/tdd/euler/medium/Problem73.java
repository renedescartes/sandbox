package com.work.tdd.euler.medium;

import org.testng.annotations.Test;

import java.util.logging.Logger;

import static org.testng.Assert.assertEquals;

public class Problem73 {

    private static final Logger logger = Logger.getLogger(Problem73.class.getName());

    public static long fractionBetween(final BigIntegerFraction f1, final BigIntegerFraction f2, final long d) {
        long count = 0;
        for (long den = d; den >= 2; den--) {
            long start = (long) Math.ceil(equivalent(f1, den));
            long end = (long) Math.floor(equivalent(f2, den));
            for (long num = start; num <= den; num++) {
                BigIntegerFraction f = new BigIntegerFraction(num, den);
                if (f.compareTo(f1) > 0 && f.compareTo(f2) < 0) {
                    if (f.equals(f.reduce())) {
                        count++;
                    }
                }
            }
            if (den % 1000 == 0) {
                logger.info("Denominator [" + den + "] start [" + start + "] end [" + end + "] has [" + count + "] fractions");
            }
        }
        return count;
    }

    private static double equivalent(BigIntegerFraction f, long denominator) {
        return ((double) f.numerator.longValue() * ((double) denominator / (double) f.denominator.longValue()));
    }

    @Test
    public void testSimple() {
        assertEquals(fractionBetween(new BigIntegerFraction(1, 3), new BigIntegerFraction(1, 2), 12000), 7295372);
    }

    @Test
    public void testBits() {
        Long count = fractionBetween(new BigIntegerFraction(1, 3), new BigIntegerFraction(1, 2), 8);
        assertEquals(count.longValue(), 3);
        count = fractionBetween(new BigIntegerFraction(1, 3), new BigIntegerFraction(1, 2), 50);
        assertEquals(count.longValue(), 129);
    }
}
