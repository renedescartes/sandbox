package com.work.tdd.euler;

import com.google.common.collect.Multimap;
import com.google.common.collect.TreeMultimap;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.Collection;
import java.util.logging.Logger;

import static org.testng.Assert.assertEquals;

public class Problem76 {
    private static final Logger logger = Logger.getLogger(Problem76.class.getName());

    public static Multimap<Integer, String> splitSum(int n) {
        int level = 2;
        Multimap<Integer, String> map = TreeMultimap.create();
        for (int i = 1; i < n; i++) {
            map.put(level, sortString("" + i + (n - i)));
        }
        while (level <= n) {
            Collection<String> levelStrings = map.get(level);
            level++;
            for (String s : levelStrings) {
                Integer[] digits = Utility.digits(s);
                for (int i = 0; i < digits.length; i++) {
                    if (digits[i] != 1) {
                        for (int j = 1; j < digits[i]; j++) {
                            String newAttempt = s.substring(0, i) + j + (digits[i] - j) + s.substring(i + 1);
                            map.put(level, sortString(newAttempt));
                        }
                    }
                }
            }
        }
        return map;
    }

    private static String sortString(String s) {
        Integer[] digits = Utility.digits(s);
        Arrays.sort(digits);
        StringBuilder b = new StringBuilder();
        for (int i = digits.length - 1; i > 0; i--) {
            b.append(digits[i]).append(" ");
        }
        b.append(digits[0]);
        return b.toString();
    }

    @Test
    public void testSimple() {
        Multimap<Integer, String> strings = splitSum(100);
        logger.info(strings.toString());
        assertEquals(strings.size(), 100);
    }

    @Test
    public void testBits() {
        Multimap<Integer, String> strings = splitSum(6);
        logger.info(strings.toString());
    }
}

