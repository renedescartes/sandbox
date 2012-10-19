package com.work.tdd.euler;

import org.apache.commons.lang.StringUtils;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

/**
 * User: renedescartes
 * Date: 08/10/12
 */
public class Problem37 {

    private static Map<Long, Boolean> primeMap = new HashMap<>();

    private static Logger logger = Logger.getLogger(Problem37.class.getName());

    public static long sumOfTruncatablePrimes(int required) {
        long sum = 0;
        long number = 8;
        while (required > 0) {
            logger.fine("Checking number " + number);
            if (isTruncatablePrime(number)) {
                logger.info("Found truncatable prime " + number);
                sum += number;
                required--;
            }
            number++;
        }
        return sum;
    }

    private static boolean isTruncatablePrime(long number) {
        if (!isPrimeNumber(number)) {
            return false;
        }
        List<List<Integer>> options = Utility.truncationOptions(Arrays.asList(Utility.digits(number)));
        for (List<Integer> option : options) {
            if (!isPrimeNumber(Long.valueOf(StringUtils.join(option, "")))) {
                return false;
            }
        }
        return true;
    }

    private static boolean isPrimeNumber(Long number) {
        if (!primeMap.containsKey(number)) {
            primeMap.put(number, Utility.isPrime(number));
        }
        return primeMap.get(number);
    }

    @Test
    public void testSimple() {
        System.out.println(sumOfTruncatablePrimes(11));
    }
}
