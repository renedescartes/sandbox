package com.work.tdd.euler;

import org.testng.annotations.Test;

import java.util.logging.Logger;

import static org.testng.Assert.assertEquals;

public class Problem73 {

    private static final Logger logger = Logger.getLogger(Problem73.class.getName());

    public static long fractionBetween(final Fraction f1, final Fraction f2, final long d) {
        long count = 0;
        for(long den = d; den >= 2; den--) {
            long start = (long) Math.ceil(equivalent(f1, den));
            long end = (long) Math.floor(equivalent(f2, den));
            long diff = end - start + 1; //maximum possible fractions in this interval;
            Fraction startFraction = new Fraction(start, den).reduce();
            Fraction endFraction = new Fraction(end, den).reduce();
            if(startFraction.equals(f1)) {
                diff--;
            }
            if(endFraction.equals(f2)) {
                diff--;
            }
            if(diff > 0) {
                count += diff;
            }
            logger.info("Denominator [" + den + "] start [" + start + "] end [" + end + "] has [" + diff + "] fractions and total [" + count + "]");
        }
        return count;
    }

    private static double equivalent(Fraction f, long denominator) {
        return ((double) f.numerator.longValue() * ((double) denominator / (double) f.denominator.longValue()));
    }

    @Test
    public void testSimple() {
        long count =  fractionBetween(new Fraction(1, 3), new Fraction(1, 2), 12000);
        assertEquals(count, 25);
    }

    @Test
    public void testBits() {
        long count = fractionBetween(new Fraction(1, 3), new Fraction(1, 2), 8);
        assertEquals(count, 3);
    }
}
