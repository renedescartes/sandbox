package com.work.tdd.euler.hard;

import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
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
import static com.work.tdd.euler.util.NumberUtil.largestWithNDigits;
import static com.work.tdd.euler.util.NumberUtil.smallestWithNDigits;
import static org.testng.Assert.*;

public class Problem98 {
    private static final Logger logger = Logger.getLogger(Problem98.class.getName());

    public static long largestNumber(Integer[] arrangement) {
        long start = largestWithNDigits(arrangement.length);
        long end = smallestWithNDigits(arrangement.length);
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
        return -1;
    }

    @Test
    public void testSimple() {
        assertEquals(largestNumber(new Integer[]{3, 8, 5, 6, 7, 2, 0, 4, 1}), 24);
    }

    @Test
    public void testBits() {
        assertEquals(anagramRepresentation("INTRODUCE", "REDUCTION"), new Integer[]{3, 8, 5, 6, 7, 2, 0, 4, 1});
        assertEquals(convert(712347432L, new Integer[]{3, 8, 5, 6, 7, 2, 0, 4, 1}).longValue(), 327432741);
        Collection<Tuple<String, String>> anagrams = explore();
        assertTrue(anagrams.contains(new Tuple<>("INTRODUCE", "REDUCTION")));
        assertTrue(isAnagram("CARE", "RACE"));
        assertFalse(isAnagram("CARER", "RACE"));
    }

    private static boolean isAnagram(String s1, String s2) {
        List<Character> c1 = newArrayList(charactersOf(s1));
        Collections.sort(c1);
        List<Character> c2 = newArrayList(charactersOf(s2));
        Collections.sort(c2);
        return Arrays.equals(c1.toArray(), c2.toArray());
    }

    private static Collection<Tuple<String, String>> explore() {
        String[] inputs = Utility.readFile("words.txt").get(0).split(",");
        Collection<Tuple<String, String>> tuples = new ArrayList<>();
        for (String s1 : inputs) {
            for (String s2 : inputs) {
                if (!s1.equals(s2) && isAnagram(s1, s2)) {
                    tuples.add(new Tuple<>(s1.replaceAll("\"", ""), s2.replaceAll("\"", "")));
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

    private static boolean distinctDigits(Long n) {
        return n.toString().length() == Sets.newHashSet(digits(n)).size();
    }

    private static Integer[] anagramRepresentation(String s1, String s2) {
        Collection<Integer> anagram = new ArrayList<>();
        for (Character c : Lists.charactersOf(s2)) {
            anagram.add(s1.indexOf(c));
        }
        return anagram.toArray(new Integer[anagram.size()]);
    }
}
