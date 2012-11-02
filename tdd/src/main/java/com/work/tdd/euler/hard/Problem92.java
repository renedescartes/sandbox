package com.work.tdd.euler.hard;

import com.work.tdd.euler.medium.Utility;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static org.testng.Assert.assertEquals;

public class Problem92 {
    public static long answer(int target, int MAX) {
        int[] array = new int[MAX + 1];
        array[0] = -1;
        array[1] = 1;
        array[89] = 89;
        long answer = 0;
        int count = 2;
        while (count < MAX) {
            int sum = count;
            List<Integer> path = new ArrayList<>();
            while (array[sum] == 0) {
                path.add(sum);
                sum = sumOfSquareOfDigits(sum);
            }
            for (Integer pathEntry : path) {
                array[pathEntry] = array[sum];
            }
            if (array[sum] == target) {
                answer++;
            }
            count++;
        }
        return answer;
    }

    private static int sumOfSquareOfDigits(int n) {
        Integer[] digits = Utility.digits((long) n);
        int sum = 0;
        for (Integer digit : digits) {
            sum += (digit * digit);
        }
        return sum;
    }

    @Test
    public void testSimple() {
        assertEquals(answer(89, 10_000_000), 8581146);
    }

    @Test
    public void testBits() {
        assertEquals(answer(89, 1000), 857);
    }
}
