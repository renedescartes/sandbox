package com.work.tdd.euler;

import org.testng.annotations.Test;

import java.util.logging.Logger;

import static org.testng.Assert.assertEquals;

public class Problem72 {

    private static final Logger logger = Logger.getLogger(Problem73.class.getName());

    public static long countOfFractions(long d) {
        long count = 0;
        for(long den = 2; den <= d; den++) {
            int step = den % 2 == 0? 2 : 1;
            for(long num = 1; num < den; num+= step) {
                if(Utility.gcd(num, den) == 1) {
                    count++;
                }
            }
            if(den % 1000 == 0) {
                logger.info("Denominator [" + den + "] and count[" + count + "]");
            }
        }
        return count;
    }

    @Test
    public void testSimple() {
        assertEquals(countOfFractions(1000000), 7295372);
    }

    @Test
    public void testBits() {
        Long count = countOfFractions(8);
        assertEquals(count.longValue(), 21);
        count = countOfFractions(50);
        assertEquals(count.longValue(), 773);
    }
}
