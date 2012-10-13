package com.work.tdd.euler;

import org.apache.commons.lang.StringUtils;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.google.common.collect.Lists.reverse;
import static org.testng.Assert.assertEquals;

public class Problem41 {

    public static int largestPandigitalPrimeNumber() {
        for(int i = 9; i >=2; i--) {
            List<Integer> integers = reverse(listOfPandigitalNumbers(i));
            for (Integer integer : integers) {
                if(Utility.isPrime(integer)) {
                    return integer;
                }
            }
        }
        return -1;
    }

    private static List<Integer> listOfPandigitalNumbers(int length) {
        if(length > 9) {
            throw new IllegalArgumentException();
        }
        List<List<Integer>> permutes = Utility.permutes(digits(length));
        List<Integer> numbers = new ArrayList<>();
        for (List<Integer> permute : permutes) {
            numbers.add(Integer.parseInt(StringUtils.join(permute, "")));
        }
        Collections.sort(numbers);
        return numbers;
    }

    private static List<Integer> digits(int length) {
        List<Integer> digits = new ArrayList<>();
        for(int i = 1; i <= length; i++) {
            digits.add(i);
        }
        return digits;
    }

    @Test
    public void testSimple() {
        assertEquals(largestPandigitalPrimeNumber(), 7652413);
    }

}
