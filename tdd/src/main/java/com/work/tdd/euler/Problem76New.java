package com.work.tdd.euler;

import org.apache.commons.lang.builder.CompareToBuilder;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.logging.Logger;

import static org.testng.Assert.assertEquals;

public class Problem76New {

    private static final Logger logger = Logger.getLogger(Problem76.class.getName());

    public static int count(List<Integer> array) {
        int count = 1;
        while (nextElement(array) != null) {
            count++;
        }
        return count;
    }

    private static List<Integer> nextElement(List<Integer> list) {
        return null;
    }

    private static int firstDecreasingIndex(List<Integer> list) {
        for (int i = 0; i < list.size() - 1; i++) {
            if (list.get(i) > list.get(i + 1)) {
                return i;
            }
        }
        return -1;
    }

    private static Comparator<? super List<Integer>> listComparator() {
        return new Comparator<List<Integer>>() {
            @Override
            public int compare(List<Integer> o1, List<Integer> o2) {
                CompareToBuilder e = new CompareToBuilder();
                for (int i = 0; i < o1.size(); i++) {
                    e.append(o1.get(i), o2.get(i));
                }
                return e.toComparison();
            }
        };
    }

    public static long countCombine(int n) {
        return 0;
    }

    @Test
    public void testSimple() {
        assertEquals(countCombine(100), 10);
    }

    @Test
    public void testBits() {
        assertEquals(count(Arrays.asList(6, 1, 1, 1, 1)), 10);
    }
}

