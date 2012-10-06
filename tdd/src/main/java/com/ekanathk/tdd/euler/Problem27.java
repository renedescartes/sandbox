package com.ekanathk.tdd.euler;

import org.testng.annotations.Test;

import java.util.logging.Logger;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;

/**
 * User: EkanathK
 * Date: 06/10/12
 */
public class Problem27 {

    private static final Logger logger = Logger.getLogger(Problem27.class.getName());

    public static int bestFunction(int low, int high) {
        int maxPrimes = 0, maxProduct = 0;
        for(int a = low + 1; a < high; a++) {
            for(int b = low + 1; b < high; b++) {
                int numPrimes = numberOfPrimes(a, b);
                if(numPrimes > maxPrimes) {
                    maxPrimes = numPrimes;
                    maxProduct = a * b;
                }
            }
        }
        return maxProduct;
    }
    private static int numberOfPrimes(int a, int b) {
        int count = 0;
        while(Utility.isPrime(evaluate(a, b, count))) {
            logger.fine("Evaluating a [" + a + "] b [" + b + "] n [" + count + "] value [" + evaluate(a, b, count) + "]");
            count++;
        }
        return count;
    }

    private static long evaluate(int a, int b, int n) {
        return (n * n) + (a * n) + b;
    }

    @Test
    public void testSimple() {
        assertFalse(Utility.isPrime(1681));
        assertEquals(numberOfPrimes(1, 41), 40);
        assertEquals(numberOfPrimes(-79, 1601), 80);
        assertEquals(bestFunction(-1000, 1000), 25);
    }
}
