package com.work.tdd.euler.medium;

import com.google.common.collect.Lists;
import org.apache.commons.lang.builder.CompareToBuilder;
import org.testng.annotations.Test;

import java.util.*;
import java.util.logging.Logger;

import static org.testng.Assert.assertEquals;

public class Problem76 {
    private static final Logger logger = Logger.getLogger(Problem76.class.getName());

    public static long splitSum(int n) {
        int level = 2;
        Set<List<Integer>> permutes = new TreeSet<>(listComparator());
        for (int i = 1; i <= n / 2; i++) {
            permutes.add(Arrays.asList(i, (n - i)));
        }
        long sum = permutes.size();
        Set<List<Integer>> newPermutes = new TreeSet<>(listComparator());
        while (level <= n) {
            logger.info("Level [" + level + "] stepCount [" + permutes.size() + "] count [" + sum + "] options " + permutes);
            for (List<Integer> digits : permutes) {
                for (int i = 0; i < digits.size(); i++) {
                    if (digits.get(i) != 1) {
                        for (int j = 1; j < digits.get(i); j++) {
                            List<Integer> newAttempt = Lists.newArrayList(digits.subList(0, i));
                            newAttempt.add(j);
                            newAttempt.add(digits.get(i) - j);
                            newAttempt.addAll(digits.subList(i + 1, digits.size()));
                            Collections.sort(newAttempt);
                            newPermutes.add(Lists.reverse(newAttempt));
                        }
                    }
                }
            }
            permutes = newPermutes;
            sum += permutes.size();
            newPermutes = new TreeSet<>(listComparator());
            level++;
        }
        return sum;
    }

    private static Comparator<? super List<Integer>> listComparator() {
        return new Comparator<List<Integer>>() {
            @Override
            public int compare(List<Integer> o1, List<Integer> o2) {
                CompareToBuilder e = new CompareToBuilder();
                for (int i = 0; i < o1.size(); i++) {
                    e.append(o2.get(i), o1.get(i));
                }
                return e.toComparison();
            }
        };
    }

    @Test
    public void testSimple() {
        assertEquals(splitSum(100), 10);
    }

    @Test
    public void testBits() {
        splitSum(50);
        splitSum(10);
        splitSum(12);
    }
}

