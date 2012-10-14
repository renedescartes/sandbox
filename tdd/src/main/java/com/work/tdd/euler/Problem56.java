package com.work.tdd.euler;

import org.testng.annotations.Test;

import java.math.BigInteger;
import java.util.logging.Logger;

import static com.work.tdd.euler.Utility.digits;
import static com.work.tdd.euler.Utility.summation;
import static org.testng.Assert.assertEquals;

public class Problem56 {

    private static final Logger logger = Logger.getLogger(Problem56.class.getName());

    public static long maximumDigitalSum(int MAX) {
        long maxSum = 0;
        for (int a = 2; a <= MAX; a++) {
            BigInteger product = new BigInteger("" + a);
            for (int b = 2; b <= MAX; b++) {
                product = product.multiply(new BigInteger("" + a));
                long digitalSum = sumOfDigits(product);
                if (digitalSum > maxSum) {
                    maxSum = digitalSum;
                    logger.info("MaxSum = " + maxSum + " a = " + a + " b = " + b + " product = " + product);
                }
            }
        }
        return maxSum;
    }

    private static long sumOfDigits(BigInteger n) {
        return summation(digits(n.toString()));
    }

    @Test
    public void testSimple() {
        assertEquals(maximumDigitalSum(100), 972);
    }
}
