package com.work.tdd.euler;

import org.testng.annotations.Test;

import java.math.BigInteger;
import java.util.logging.Logger;

import static org.testng.Assert.assertEquals;

public class Problem53 {

    private static final Logger logger = Logger.getLogger(Problem53.class.getName());

    private static BigInteger c(int n, int r) {
        return factorial(n).divide((factorial(r).multiply(factorial(n - r))));
    }

    public static int count(int max) {
        int count = 0;
        for(int i = 1; i <= max; i++) {
            for(int j = 1; j <= i; j++) {
                logger.info("i = " + i + " j = " + j);
                if(c(i, j).compareTo(new BigInteger("1000000")) > 0) {
                    count++;
                }
            }
        }
        return count;
    }

    @Test
    public void testSimple() {
        //assertEquals(c(66, 3), 25);
        assertEquals(count(100), 4075);
    }

    public static BigInteger factorial(long n) {
        BigInteger product = new BigInteger("1");
        for(long i = 2; i <= n; i++) {
            product = product.multiply(new BigInteger("" + i));
        }
        return product;
    }
}
