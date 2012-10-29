package com.work.tdd.euler.medium;

import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;

import static com.google.common.collect.Lists.reverse;
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
            logger.fine("BitSet " + combination + " one = " + one + " two = " + two);
            if (!Utility.isCachedPrime(one) || !Utility.isCachedPrime(two)) {
                return false;
            }
        }
        return true;
    }

    private static List<Long> listPrimes(int SIZE) {
        List<Long> primes = new ArrayList<>();
        long count = 2;
        while (primes.size() < SIZE) {
            if (Utility.isCachedPrime(count)) {
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
            if (i % 1000 == 0) {
                logger.info("Examining combination [" + (i + 1) + "] -> " + combination);
            }
            if (isRemarkableCombination(combination)) {
                return combination;
            }
            i++;
        }
        return Collections.emptyList();
    }

    public static List<Long> differentAnswer(int SIZE) {
        List<Long> primes = listPrimes(SIZE);
        for (int a = 0; a < SIZE; a++) {
            for (int b = 0; b < SIZE; b++) {
                if (b != a) {
                    List<Long> doublePrimes = Arrays.asList(primes.get(a), primes.get(b));
                    if (isRemarkableCombination(doublePrimes)) {
                        logger.info("Remarkable Level 2 " + doublePrimes);
                        for (int c = 0; c < SIZE; c++) {
                            if (c != a && c != b) {
                                List<Long> triplePrimes = Arrays.asList(primes.get(a), primes.get(b), primes.get(c));
                                if (isRemarkableCombination(triplePrimes)) {
                                    logger.info("Remarkable Level 3 " + triplePrimes);
                                    for (int d = 0; d < SIZE; d++) {
                                        if (d != c && d != b && d != a) {
                                            List<Long> fourPrimes = Arrays.asList(primes.get(a), primes.get(b), primes.get(c), primes.get(d));
                                            if (isRemarkableCombination(fourPrimes)) {
                                                logger.info("Remarkable Level 4 " + fourPrimes);
                                                for (int e = 0; e < SIZE; e++) {
                                                    if (e != a && e != b && e != c && e != d) {
                                                        List<Long> fivePrimes = Arrays.asList(primes.get(a), primes.get(b), primes.get(c), primes.get(d), primes.get(e));
                                                        if (isRemarkableCombination(fivePrimes)) {
                                                            logger.info("Remarkable Level 5 " + fivePrimes);
                                                            return fivePrimes;
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return null;
    }

    @Test
    public void testSimple() {
        //System.out.println(Utility.nCr(100, 4));
        System.out.println(differentAnswer(3000));
    }

}
