package com.work.tdd.euler;

import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class Problem50 {

    private static final Logger logger = Logger.getLogger(Problem50.class.getName());

    public static int longestSum(List<Integer> lists) {
        int longestSequence = 6, longestSum = 41, currentSum = 41;
        for (int i = 6; i < lists.size(); i++) {
            Integer num = lists.get(i);
            logger.info("i = " + i + " num = " + num + " currentSum = " + currentSum + " longestSum = " +
                    longestSum + " longestSequence = " + longestSequence + " subList = " + lists.subList(i - longestSequence, i));
            int newSum = currentSum + num;
            if (Utility.isPrime(newSum)) {
                longestSequence++;
                currentSum = longestSum = newSum;
            } else {
                currentSum = newSum - lists.get(i - longestSequence);
            }
        }
        return longestSum;
    }

    @Test
    public void testSimple() {
        System.out.println(longestSum(primesLessThan(1000L)));
    }

    private static List<Integer> primesLessThan(long MAX) {
        List<Integer> primes = new ArrayList<>();
        for (int i = 2; i <= MAX; i++) {
            if (Utility.isPrime(i)) {
                primes.add(i);
            }
        }
        return primes;
    }

}
