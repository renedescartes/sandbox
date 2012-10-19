package com.work.tdd.euler;

import com.google.common.collect.Lists;
import org.testng.annotations.Test;

import java.util.*;
import java.util.logging.Logger;

import static org.testng.Assert.assertEquals;

public class Problem76 {
    private static final Logger logger = Logger.getLogger(Problem76.class.getName());

    public static long splitSum(int n) {
        int level = 2;
        Set<List<Integer>> permutes = new HashSet<>();
        for (int i = 1; i <= n / 2; i++) {
            permutes.add(Arrays.asList(i, (n - i)));
        }
        long sum = permutes.size();
        Set<List<Integer>> newPermutes = new HashSet<>();
        while (level <= n) {
            logger.info("Level [" + level + "] stepCount [" + permutes.size() + "] count [" + sum + "]");
            for (List<Integer> digits : permutes) {
                for (int i = 0; i < digits.size(); i++) {
                    if (digits.get(i) != 1) {
                        for (int j = 1; j < digits.get(i); j++) {
                            List<Integer> newAttempt = Lists.newArrayList(digits.subList(0, i));
                            newAttempt.add(j);
                            newAttempt.add(digits.get(i) - j);
                            newAttempt.addAll(digits.subList(i + 1, digits.size()));
                            Collections.sort(newAttempt);
                            newPermutes.add(newAttempt);
                        }
                    }
                }
            }
            permutes = newPermutes;
            sum += permutes.size();
            newPermutes = new HashSet<>();
            level++;
        }
        return sum;
    }

    @Test
    public void testSimple() {
        assertEquals(splitSum(100), 10);
    }

    @Test
    public void testBits() {
        assertEquals(splitSum(10), 41);
    }
}

