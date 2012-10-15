package com.work.tdd.euler;

import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;

import static com.google.common.collect.Lists.reverse;
import static com.work.tdd.euler.Utility.combinations;
import static com.work.tdd.euler.Utility.isCachedPrime;
import static java.lang.Long.parseLong;
import static org.apache.commons.lang.StringUtils.join;

public class Problem60 {

    private static final Logger logger = Logger.getLogger(Problem60.class.getName());

    private static boolean isRemarkableCombination(List<Long> primes) {
        List<List<Long>> combinations = combinations(primes, 2);
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
        List<List<Long>> combinations = combinations(primes, 5);
        logger.info("Number of combinations [" + combinations.size() + "]");
        for(int i = 0; i < combinations.size(); i++) {
            List<Long> combination = combinations.get(i);
            if(i % 100 == 0) {
                logger.info("Examining combination [" + (i+1) + "] -> " + combination);
            }
            if(isRemarkableCombination(combination)) {
                return combination;
            }
        }
        return Collections.emptyList();
    }

    @Test
    public void testSimple() {
        System.out.println(answer(20));
    }

}
