package com.work.tdd.euler.hard;

import com.google.common.base.Preconditions;
import com.google.common.base.Predicate;
import com.work.tdd.euler.medium.Utility;
import org.apache.commons.lang.StringUtils;
import org.testng.annotations.Test;

import javax.annotation.Nullable;
import java.util.*;
import java.util.logging.Logger;

import static com.google.common.collect.Collections2.filter;
import static com.google.common.collect.Lists.charactersOf;
import static com.google.common.collect.Lists.newArrayList;
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

    private static Collection<String> explore() {
        int max = 0;
        String[] inputs = inputs("words.txt");
        Collection<String> maxStrings = new HashSet<>();
        for (String s1 : inputs) {
            for (String s2 : inputs) {
                if (!s1.equals(s2) && isAnagram(s1, s2)) {
                    if (s1.length() >= max) {
                        max = s1.length();
                        maxStrings.add(s1);
                        maxStrings.add(s2);
                    }
                }
            }
        }
        maxStrings = filter(maxStrings, lengthPredicate(max));
        logger.info("Maximum length " + maxStrings);
        return maxStrings;
    }

    private static Predicate<String> lengthPredicate(final int length) {
        return new Predicate<String>() {
            @Override
            public boolean apply(@Nullable String input) {
                return length == input.toString().length();
            }
        };
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
        return 0;
    }

    @Test
    public void testBits() {
        assertEquals(convert(712347432L, new Integer[]{3, 8, 5, 6, 7, 2, 0, 4, 1}).longValue(), 327432741);
        Collection<String> anagrams = explore();
        assertTrue(anagrams.contains("\"INTRODUCE\""));
        assertTrue(anagrams.contains("\"REDUCTION\""));
        assertTrue(isAnagram("CARE", "RACE"));
        assertFalse(isAnagram("CARER", "RACE"));
    }
}
