package com.work.tdd.euler;

import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import static com.work.tdd.euler.Utility.isPrime;
import static com.work.tdd.euler.Utility.summation;

public class Problem50 {

    private static final Logger logger = Logger.getLogger(Problem50.class.getName());

    private static int longestGap(List<Integer> lists, int existingMax, int MAX) {
        long summation = summation(lists);
        for (int i = 0; i < lists.size() - existingMax; i++) {
            if (summation <= MAX && isPrime(summation)) {
                logger.fine("Summation [" + summation + "] subList " + lists.subList(i, lists.size()));
                return lists.size() - i;
            }
            summation -= lists.get(i);
        }
        return 0;
    }

    public static long answer(List<Integer> elements, int MAX) {
        int longestGap = 0, longestSum = 0;
        for (int i = 1; i < elements.size(); i++) {
            List<Integer> subList = elements.subList(0, i);
            int gap = longestGap(subList, longestGap, MAX);
            if (gap > longestGap) {
                longestGap = gap;
                List<Integer> maxList = subList.subList(subList.size() - gap, subList.size());
                longestSum = summation(maxList);
                logger.info("New gap [" + longestGap + "] and sum [" + longestSum + "] maxList [" + maxList + "]");
            }
        }
        return longestSum;
    }

    @Test
    public void testSimple() {
        System.out.println(answer(primesLessThan(1000000L), 1000000));
    }

    private static List<Integer> primesLessThan(long MAX) {
        List<Integer> primes = new ArrayList<>();
        for (int i = 2; i <= MAX; i++) {
            if (isPrime(i)) {
                primes.add(i);
            }
        }
        return primes;
    }

}
