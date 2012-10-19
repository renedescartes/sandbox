package com.work.tdd.euler;

import org.testng.annotations.Test;

import java.util.Collection;
import java.util.TreeSet;
import java.util.logging.Logger;

import static com.work.tdd.euler.Utility.digits;
import static com.work.tdd.euler.Utility.factorial;

/**
 * User: renedescartes
 * Date: 08/10/12
 */
public class Problem34 {

    private static final Logger logger = Logger.getLogger(Problem34.class.getName());

    private static boolean isCurious(Long number) {
        Integer[] digits = digits(number);
        long sum = 0;
        for (int i = 0; i < digits.length; i++) {
            sum += factorial(digits[i]);
        }
        return sum == number;
    }

    public static Collection<Long> findCuriousNumbers() {
        Collection<Long> numbers = new TreeSet<>();
        for (long l = 3; l <= 99999999; l++) {
            if (isCurious(l)) {
                logger.info("Curious number " + l);
                numbers.add(l);
            }
        }
        return numbers;
    }

    @Test
    public void testSimple() {
        System.out.println(findCuriousNumbers());
    }

}
