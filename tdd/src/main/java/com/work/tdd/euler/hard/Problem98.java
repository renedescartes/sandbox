package com.work.tdd.euler.hard;

import com.google.common.base.Preconditions;
import com.google.common.collect.Sets;
import com.work.tdd.euler.medium.Utility;
import com.work.tdd.euler.util.Tuple;
import org.apache.commons.lang.StringUtils;
import org.testng.annotations.Test;

import java.util.*;
import java.util.logging.Logger;

import static com.google.common.collect.Lists.charactersOf;
import static com.google.common.collect.Lists.newArrayList;
import static com.work.tdd.euler.medium.Utility.digits;
import static com.work.tdd.euler.medium.Utility.isPerfectSquare;
import static org.testng.Assert.*;

public class Problem98 {
    private static final Logger logger = Logger.getLogger(Problem98.class.getName());

    private static boolean isAnagram(String s1, String s2) {
        List<Character> c1 = newArrayList(charactersOf(s1));
        Collections.sort(c1);
        List<Character> c2 = newArrayList(charactersOf(s2));
        Collections.sort(c2);
        return Arrays.equals(c1.toArray(), c2.toArray());
    }

    private static String[] inputs(String resource) {
        return Utility.readFile(resource).get(0).split(",");
    }

    private static Collection<Tuple<String, String>> explore() {
        int max = 0;
        String[] inputs = inputs("words.txt");
        Collection<Tuple<String, String>> tuples = new ArrayList<>();
        for (String s1 : inputs) {
            for (String s2 : inputs) {
                if (!s1.equals(s2) && isAnagram(s1, s2)) {
                    if (s1.length() >= max) {
                        max = s1.length();
                        tuples.add(new Tuple<>(s1, s2));
                    }
                }
            }
        }
        logger.info("Tuples " + tuples);
        return tuples;
    }

    private static Long convert(Long number, Integer[] arrangement) {
        Preconditions.checkArgument(number.toString().length() == arrangement.length);
        Integer[] originalDigits = Utility.digits(number);
        Integer[] newDigits = new Integer[originalDigits.length];
        for (int i = 0; i < arrangement.length; i++) {
            newDigits[i] = originalDigits[arrangement[i]];
        }
        return Long.valueOf(StringUtils.join(newDigits, ""));
    }

    public static long largestNumber(Integer[] arrangement) {
        long start = (long) Math.sqrt(999999999);
        long end = (long) Math.sqrt(100000000);
        for (long i = start; i >= end; i--) {
            Long square = i * i;
            if (distinctDigits(square)) {
                logger.info("i = " + i + " square " + square);
                Long convert = convert(square, arrangement);
                if (isPerfectSquare(convert)) {
                    return Math.max(square, convert);
                }
            }
        }
        return 0;
    }

    private static boolean distinctDigits(Long n) {
        return n.toString().length() == Sets.newHashSet(digits(n)).size();
    }

    @Test
    public void testSimple() {
        assertEquals(largestNumber(new Integer[]{3, 8, 5, 6, 7, 2, 0, 4, 1}), 24);
    }

    @Test
    public void testBits() {
        assertEquals(convert(712347432L, new Integer[]{3, 8, 5, 6, 7, 2, 0, 4, 1}).longValue(), 327432741);
        Collection<Tuple<String, String>> anagrams = explore();
        assertTrue(anagrams.contains(new Tuple<>("\"INTRODUCE\"", "\"REDUCTION\"")));
        assertTrue(isAnagram("CARE", "RACE"));
        assertFalse(isAnagram("CARER", "RACE"));
    }
}
