package com.work.tdd.euler;

import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class Problem77 {

    private static Logger logger = Logger.getLogger(Problem77.class.getName());

    private static int[] primePartitions = new int[1000000];

    public static List<Integer> primes(int n) {
        int count = 2;
        List<Integer> primes = new ArrayList<>();
        while (primes.size() <= n) {
            if (Utility.isPrime(count)) {
                primes.add(count);
            }
            count++;
        }
        return primes;
    }

    @Test
    public void testBits() {
        logger.info(primes(5000).toString());
    }
}
