package com.work.tdd.euler;

import org.apache.commons.lang.StringUtils;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class Problem43 {

    private static final Logger logger = Logger.getLogger(Problem43.class.getName());

    private static boolean isSubStringDivisible(Long number) {
        String s = number.toString();
        if(s.length() != 10) {
            return false;
        }
        return subDigit(number, 1, 4) % 2 == 0
                && subDigit(number, 2, 5) % 3 == 0
                && subDigit(number, 3, 6) % 5 == 0
                && subDigit(number, 4, 7) % 7 == 0
                && subDigit(number, 5, 8) % 11 == 0
                && subDigit(number, 6, 9) % 13 == 0
                && subDigit(number, 7, 10) % 17 == 0;


    }

    private static Integer subDigit(Long number, int i, int j) {
        return Integer.parseInt(number.toString().substring(i, j));
    }

    private static List<Long> listOfPandigitalNumbers(int length) {
        if(length > 10) {
            throw new IllegalArgumentException();
        }
        List<List<Integer>> permutes = Utility.permutes(digits(length));
        List<Long> numbers = new ArrayList<>();
        for (List<Integer> permute : permutes) {
            numbers.add(Long.parseLong(StringUtils.join(permute, "")));
        }
        Collections.sort(numbers);
        return numbers;
    }

    private static List<Integer> digits(int length) {
        List<Integer> digits = new ArrayList<>();
        for(int i = 0; i < length; i++) {
            digits.add(i);
        }
        return digits;
    }

    public static long answer() {
        List<Long> numbers = listOfPandigitalNumbers(10);
        long sum = 0;
        for (Long number : numbers) {
            if(isSubStringDivisible(number)) {
                logger.info("Pandigital number [" + number + "]");
                sum += number;
            }
        }
        return sum;
    }

    @Test
    public void testSimple() {
        assertEquals(subDigit(1406357389L, 1, 4).intValue(), 406);
        assertTrue(isSubStringDivisible(1406357289L));
        assertEquals(answer(), 16695334890L);
    }
}
