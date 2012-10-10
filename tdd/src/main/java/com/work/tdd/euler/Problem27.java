package com.work.tdd.euler;

import org.testng.annotations.Test;

import java.util.logging.Logger;

import static com.work.tdd.euler.Utility.isPrime;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;

/**
 * User: renedescartes
 * Date: 06/10/12
 */
public class Problem27 {

    private static final Logger logger = Logger.getLogger(Problem27.class.getName());

    public static int bestFunction(int low, int high) {
        int maxPrimes = 0, maxProduct = 0;
        for(int a = low + 1; a < high; a++) {
            for(int b = low + 1; b < high; b++) {
                if(isPrime(b) && isPrime(b+1+a) && checkNumberOfPrimes(a, b, maxPrimes)) {
                    int numPrimes = numberOfPrimes(a, b, maxPrimes + 1);
                    logger.info("a = " + a + " b = " + b + " numPrimes = " + numPrimes + " maxPrimes = " + maxPrimes);
                    if(numPrimes > maxPrimes) {
                        maxPrimes = numPrimes;
                        maxProduct = a * b;
                    }
                }
            }
        }
        return maxProduct;
    }

    private static boolean checkNumberOfPrimes(int a, int b, int count) {
        while(count >= 0) {
            if(isPrime(evaluate(a, b, count))) {
                count--;
            } else {
                return false;
            }
        }
        return true;
    }

    private static int numberOfPrimes(int a, int b, int start) {
        int count = start;
        while(isPrime(evaluate(a, b, count))) {
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
        assertFalse(isPrime(1681));
        assertEquals(bestFunction(-1000, 1000), -59231);
    }
}
