package com.work.tdd.euler;

import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;

import static com.google.common.collect.Lists.reverse;
import static com.work.tdd.euler.Utility.isCachedPrime;
import static com.work.tdd.euler.util.Combinations.combinationIterator;
import static java.lang.Long.parseLong;
import static org.apache.commons.lang.StringUtils.join;

public class Problem60 {

    private static final Logger logger = Logger.getLogger(Problem60.class.getName());

    private static boolean isRemarkableCombination(List<Long> primes) {
        Iterable<List<Long>> combinations = combinationIterator(primes, 2);
        for (List<Long> combination : combinations) {
            Long one = parseLong(join(combination, ""));
            Long two = parseLong(join(reverse(combination), ""));
            logger.fine("Combination " + combination + " one = " + one + " two = " + two);
            if(!isCachedPrime(one) || !isCachedPrime(two)) {
                return false;
            }
        }
        return true;
    }

    private static List<Long> listPrimes(int SIZE) {
        List<Long> primes = new ArrayList<>();
        long count = 2;
        while(primes.size() < SIZE) {
            if(isCachedPrime(count)) {
                primes.add(count);
            }
            count++;
        }
        return primes;
    }

    public static List<Long> answer(int SIZE) {
        List<Long> primes = listPrimes(SIZE);
        Iterable<List<Long>> listIterable = combinationIterator(primes, 4);
        int i = 0;
        for (List<Long> combination : listIterable) {
            if(i % 1000 == 0) {
                logger.info("Examining combination [" + (i+1) + "] -> " + combination);
            }
            if(isRemarkableCombination(combination)) {
                return combination;
            }
            i++;
        }
        return Collections.emptyList();
    }

    @Test
    public void testSimple() {
        System.out.println(Utility.nCr(100, 4));
        //System.out.println(answer(100));
    }

}
