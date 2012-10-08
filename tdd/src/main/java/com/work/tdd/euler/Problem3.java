package com.work.tdd.euler;

import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

/**
 * User: kannan
 * Date: 04/10/12
 */
public class Problem3 {

    private static boolean isPrime(long n) {
        for(long i = 2; i < Math.sqrt(n); i++) {
            if(n % i == 0) {
                return false;
            }
        }
        return true;
    }

    public static long largestPrimeFactor(long n) {
        for(long i = (long)Math.sqrt(n); i > 1; i--) {
            if(isPrime(i) && n % i == 0) {
                return i;
            }
        }
        return -1;
    }

    @Test
    public void testSimple() {
        assertEquals(29, largestPrimeFactor(13195));
        System.out.println(largestPrimeFactor(600851475143L));
    }
}
