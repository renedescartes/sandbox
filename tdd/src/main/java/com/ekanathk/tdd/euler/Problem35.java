package com.ekanathk.tdd.euler;

import org.apache.commons.lang.StringUtils;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

/**
 * User: kannan
 * Date: 08/10/12
 */
public class Problem35 {

    private static Map<Integer, Boolean> primeMap = new HashMap<>();

    private static Logger logger = Logger.getLogger(Problem35.class.getName());

    public static boolean isCircularPrime(Integer number) {
        if(!isCachedPrime(number)) {
            return false;
        }
        List<Integer> digits = Arrays.asList(Utility.digits((long) number));
        List<List<Integer>> variations = Utility.rotations(digits);
        for (List<Integer> variation : variations) {
            Integer rotatedNumber = Integer.parseInt(StringUtils.join(variation, ""));
            if(!isCachedPrime(rotatedNumber)) {
                return false;
            }
        }
        return true;
    }

    private static boolean isCachedPrime(Integer number) {
        if(!primeMap.containsKey(number)) {
            primeMap.put(number, Utility.isPrime(number));
        }
        return primeMap.get(number);
    }

    public static int numberOfCircularPrimes(int max) {
        int number = 0;
        for(int i = 0; i < max; i++) {
            if(isCircularPrime(i)) {
                logger.info("Circular prime " + i);
                number++;
            }
        }
        return number;
    }

    @Test
    public void testSimple() {
        System.out.println(numberOfCircularPrimes(1000000));
    }
}
