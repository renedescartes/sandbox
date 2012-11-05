package com.work.tdd.euler.hard;

import com.work.tdd.euler.medium.Utility;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;

import static com.google.common.collect.Lists.charactersOf;
import static com.google.common.collect.Lists.newArrayList;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

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

    private static void explore() {
        String[] inputs = inputs("words.txt");
        for (String s1 : inputs) {
            for (String s2 : inputs) {
                if (!s1.equals(s2) && isAnagram(s1, s2)) {
                    logger.info("Inputs " + s1 + " and " + s2);
                }
            }
        }
    }

    @Test
    public void testBits() {
        explore();
        assertTrue(isAnagram("CARE", "RACE"));
        assertFalse(isAnagram("CARER", "RACE"));
    }
}
