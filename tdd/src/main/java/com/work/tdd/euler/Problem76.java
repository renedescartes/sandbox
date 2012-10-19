package com.work.tdd.euler;

import org.apache.commons.lang.StringUtils;
import org.testng.annotations.Test;

import java.util.*;
import java.util.logging.Logger;

import static org.testng.Assert.assertEquals;

public class Problem76 {
    private static final Logger logger = Logger.getLogger(Problem76.class.getName());

    private static Map<Integer, Set<String>> splits = new HashMap<>();

    static {
        splits.put(1, asTreeSet("1"));
        splits.put(2, asTreeSet("11"));
        splits.put(3, asTreeSet("12"));
        splits.put(4, asTreeSet("31", "22", "211", "1111"));
    }

    public static Set<String> cachedSplitSum(int n) {
        if (!splits.containsKey(n)) {
            splits.put(n, splitSum(n));
        }
        return splits.get(n);
    }

    public static Set<String> splitSum(int n) {
        Set<String> answers = new TreeSet<>();
        for (int i = 1; i <= n / 2; i++) {
            answers.add("" + i + (n - i));
            Set<String> morePermutes1 = cachedSplitSum(i);
            Set<String> morePermutes2 = cachedSplitSum(n - i);
            for (String p1 : morePermutes1) {
                for (String p2 : morePermutes2) {
                    answers.add(sortString(p1 + p2));
                }
            }
        }
        return answers;
    }

    private static String sortString(String s) {
        Integer[] digits = Utility.digits(s);
        Arrays.sort(digits);
        return StringUtils.join(digits);
    }

    @SafeVarargs
    private static <T> Set<T> asTreeSet(T... element) {
        TreeSet<T> set = new TreeSet<>();
        Collections.addAll(set, element);
        return set;
    }

    @Test
    public void testSimple() {
        Set<String> strings = cachedSplitSum(100);
        logger.info(strings.toString());
        assertEquals(strings.size(), 100);
    }

    @Test
    public void testBits() {
        Set<String> strings = cachedSplitSum(4);
        logger.info(strings.toString());
        logger.info(cachedSplitSum(5).toString());
        logger.info(cachedSplitSum(6).toString());
        logger.info(cachedSplitSum(7).toString());
        logger.info(cachedSplitSum(8).toString());
        assertEquals(strings.size(), 4);
    }
}

