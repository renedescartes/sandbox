package com.work.tdd.euler;

import org.testng.annotations.Test;

import java.util.logging.Logger;

import static org.testng.Assert.assertEquals;

public class Problem73 {

    private static final Logger logger = Logger.getLogger(Problem73.class.getName());

    public static int fractionBetween(final Fraction f1, final Fraction f2, final long d) {
        int count = 0;
        for(long den = d; den >= 2; den--) {
            long start = (long) Math.floor(equivalent(f1, den));
            long end = (long) Math.ceil(equivalent(f2, den));
            logger.fine("Denominator [" + den + "] start [" + start + "] end [" + end + "]");
            for(long num = start; num <= end; num++) {
                Fraction f = new Fraction(num, den);
                if(f.compareTo(f1) > 0 && f.compareTo(f2) < 0) {
                    logger.fine(f.toString() + " is between " + f1 + " and " + f2);
                    count++;
                }
            }
        }
        return count;
    }

    private static double equivalent(Fraction f, long denominator) {
        return ((double) f.numerator.longValue() * ((double) denominator / (double) f.denominator.longValue()));
    }

    @Test
    public void testSimple() {
        int count =  fractionBetween(new Fraction(1, 3), new Fraction(1, 2), 1000000);
        assertEquals(count, 25);
    }

    @Test
    public void testBits() {
        int count = fractionBetween(new Fraction(1, 3), new Fraction(1, 2), 8);
        assertEquals(count, 3);
    }
}
