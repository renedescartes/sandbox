package com.work.tdd.euler;

import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import static com.work.tdd.euler.Utility.primeFactors;

public class Problem47 {

    private static final Logger logger = Logger.getLogger(Problem47.class.getName());

    private static final Map<Long, Integer> cache = new HashMap<>();

    private static int numberOfDistinctPrimeFactors(long number) {
        if(!cache.containsKey(number)) {
            List<Long> factors = primeFactors(number);
            int size = new HashSet(factors).size();
            logger.info("Number " + number + " Size " + size + " Factors " + factors);
            cache.put(number, size);
        }
        return cache.get(number);
    }

    public static long findNumbersWithDistinctPrimeFactors() {
        long count = 1;
        while (true) {
            if(numberOfDistinctPrimeFactors(count) == 4 &&
                    numberOfDistinctPrimeFactors(count+1) == 4 &&
                    numberOfDistinctPrimeFactors(count+2) == 4 &&
                    numberOfDistinctPrimeFactors(count+3) == 4) {
                return count;
            }
            count++;
        }
    }

    @Test
    public void testSimple() {
        //System.out.println(primeFactors(9L));
        System.out.println(findNumbersWithDistinctPrimeFactors());
    }
}
