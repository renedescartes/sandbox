package com.work.tdd.euler;

import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.logging.Logger;

import static com.work.tdd.euler.Problem69.phi;
import static org.testng.Assert.assertEquals;

public class Problem70 {

    private static Logger logger = Logger.getLogger(Problem70.class.getName());

    public static long permutationCount(long start, long max) {
        double phiRatio = Double.MAX_VALUE;
        long answer = 0;
        for (long i = start; i < max; i++) {
            long phi = phi(i);
            if (isPermute(i, phi)) {
                double ratio = (double) i / (double) phi;
                if (ratio < phiRatio) {
                    logger.info("Number [" + i + "] and phi[" + phi + "] preferred over answer[" + answer + "] with ratio [" + phiRatio + "]");
                    phiRatio = ratio;
                    answer = i;
                }
            }
        }
        return answer;
    }

    private static boolean isPermute(long n1, long n2) {
        Integer[] d1 = Utility.digits(n1);
        Arrays.sort(d1);
        Integer[] d2 = Utility.digits(n2);
        Arrays.sort(d2);
        return Arrays.equals(d1, d2);
    }

    @Test
    public void testSimple() {
        assertEquals(permutationCount(2, 10000000), 75841);
    }
}
