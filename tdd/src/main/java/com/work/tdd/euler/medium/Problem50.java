package com.work.tdd.euler.medium;

import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class Problem50 {

    private static final Logger logger = Logger.getLogger(Problem50.class.getName());

    private static int longestGap(List<Long> lists, int existingMax, int MAX) {
        long summation = Utility.summation(lists);
        for (int i = 0; i < lists.size() - existingMax; i++) {
            if (summation <= MAX && Utility.isPrime(summation)) {
                logger.fine("Summation [" + summation + "] subList " + lists.subList(i, lists.size()));
                return lists.size() - i;
            }
            summation -= lists.get(i);
        }
        return 0;
    }

    public static long answer(List<Long> elements, int MAX) {
        int longestGap = 0;
        long longestSum = 0;
        for (int i = 1; i < elements.size(); i++) {
            List<Long> subList = elements.subList(0, i);
            int gap = longestGap(subList, longestGap, MAX);
            if (gap > longestGap) {
                longestGap = gap;
                List<Long> maxList = subList.subList(subList.size() - gap, subList.size());
                longestSum = Utility.summation(maxList);
                logger.info("New gap [" + longestGap + "] and sum [" + longestSum + "] maxList [" + maxList + "]");
            }
        }
        return longestSum;
    }

    @Test
    public void testSimple() {
        System.out.println(answer(primesLessThan(1000000L), 1000000));
    }

    private static List<Long> primesLessThan(long MAX) {
        List<Long> primes = new ArrayList<>();
        for (long i = 2; i <= MAX; i++) {
            if (Utility.isPrime(i)) {
                primes.add(i);
            }
        }
        return primes;
    }

}
