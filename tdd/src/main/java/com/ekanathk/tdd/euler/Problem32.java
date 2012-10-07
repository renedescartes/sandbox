package com.ekanathk.tdd.euler;

import org.junit.Test;

import java.util.*;
import java.util.logging.Logger;

import static com.ekanathk.tdd.euler.Utility.digits;
import static java.util.Arrays.asList;
import static org.testng.Assert.assertEquals;

/**
 * User: EkanathK
 * Date: 07/10/12
 */
public class Problem32 {

    private static final Logger logger = Logger.getLogger(Problem32.class.getName());

    private static boolean isFull(Long a, Long b, Long product) {
        List<Integer> digits = new ArrayList<>();
        digits.addAll(asList(digits(a)));
        digits.addAll(asList(digits(b)));
        digits.addAll(asList(digits(product)));
        Set<Integer> digitSet = new HashSet<>(digits);
        return digits.size() == 9 && digitSet.size() == 9 && !digitSet.contains(new Integer(0));
    }

    public static Long[] listOfProducts() {
        Collection<Long> numbers = new HashSet<>();
        for(long a = 1; a < 1111; a++) {
            for(long b = 1; b < 1111; b++) {
                if(isFull(a, b, a *b)) {
                    logger.info("a = " + a + " b = " + b + " product " + (a*b));
                    numbers.add(a * b);
                }
            }
        }
        logger.info("List of products " + numbers);
        return numbers.toArray(new Long[numbers.size()]);
    }

    public static long answer() {
        return Utility.summation(listOfProducts());
    }

    @Test
    public void testSimple() {
        assertEquals(answer(), 30424);
    }
}
