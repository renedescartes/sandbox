package com.work.tdd.euler;

import org.testng.annotations.Test;

import java.util.logging.Logger;

import static org.testng.Assert.assertEquals;

public class Problem71 {

    private static final Logger logger = Logger.getLogger(Problem71.class.getName());

    public static BigIntegerFraction fractionBefore(final BigIntegerFraction target, final long d) {
        BigIntegerFraction answer = new BigIntegerFraction((long) Math.floor(equivalent(target, d)), d);
        for(long den = d; den >= 2; den--) {
            long start = (long) Math.floor(equivalent(answer, den));
            long end = (long) Math.ceil(equivalent(target, den));
            logger.fine("Denominator [" + den + "] start [" + start + "] end [" + end + "]");
            for(long num = start; num <= end; num++) {
                BigIntegerFraction f = new BigIntegerFraction(num, den);
                if(f.compareTo(target) < 0) {
                    if(f.compareTo(answer) > 0) {
                        logger.info("Prefer [" + f + "] against [" + answer + "]");
                        answer = f;
                    }
                }
            }
        }
        return answer;
    }

    private static double equivalent(BigIntegerFraction f, long denominator) {
        return ((double) f.numerator.longValue() * ((double) denominator / (double) f.denominator.longValue()));
    }

    @Test
    public void testSimple() {
        BigIntegerFraction f = fractionBefore(new BigIntegerFraction(3, 7), 1000000);
        assertEquals(f, new BigIntegerFraction(428570, 999997));
    }

    @Test
    public void testBits() {
        BigIntegerFraction fraction = fractionBefore(new BigIntegerFraction(3, 7), 8);
        assertEquals(fraction, new BigIntegerFraction(2, 5));
    }
}
