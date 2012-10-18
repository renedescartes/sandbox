package com.work.tdd.euler;

import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

public class Problem69 {
    private static final Logger logger = Logger.getLogger(Problem45.class.getName());
    private static Map<Long, Boolean> primeMap = new HashMap<>();
    private static Map<Long, Long> phiMap = new HashMap<>();

    private static boolean isCachedPrime(Long number) {
        if(!primeMap.containsKey(number)) {
            primeMap.put(number, Utility.isPrime(number));
        }
        return primeMap.get(number);
    }

    private static long cachedPhi(long number) {
        if(!phiMap.containsKey(number)) {
            phiMap.put(number, phi(number));
        }
        return phiMap.get(number);
    }

    private static long phi(long n) {
        if(n <= 2) {
            return 1;
        }
        long a;
        for (a = (long) Math.sqrt(n); a <= n; a++) {
            if (Utility.isPerfectSquare(a * a - n)) {
                long b = (long) Math.sqrt(a * a - n);
                if (a + b < n) {
                    if(a + b == 1) {
                        return a - b - 1;
                    } else if(a - b == 1) {
                        return a + b - 1;
                    } else {
                        return cachedPhi(a+b) * cachedPhi(a - b);
                    }
                }
            }
        }
        return n - 1;
    }

    public static long explore(long n) {
        for(long i = 2; i < n; i++) {
            logger.info("i = " + i + " phi = " + phi(i));
        }
        return -1;
    }

    @Test
    public void testSimple() {
        explore(10);
    }

}
