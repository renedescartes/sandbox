package com.work.tdd.euler.medium;

import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.logging.Logger;

/**
 * User: renedescartes
 * Date: 29/09/12
 */
public class Problem22 {

    private static final Logger logger = Logger.getLogger(Problem22.class.getName());

    public static long computeScore(String[] names) {
        long score = 0;
        for (int i = 0; i < names.length; i++) {
            String name = removeQuotes(names[i]);
            int stringScore = score(name);
            score += (stringScore * (i + 1));
            logger.info("String [" + name + "] score [" + stringScore + "] position [" + (i + 1) + "] cumulative score [" + score + "]");
        }
        return score;
    }

    @Test
    public void testSimple() {
        String[] strings = Utility.readFile("names.txt").get(0).split(",");
        Arrays.sort(strings);
        System.out.println(computeScore(strings));
    }

    private static String removeQuotes(String s) {
        return s.replaceAll("\"", "");
    }

    private static int score(String s) {
        int score = 0;
        for (int i = 0; i < s.length(); i++) {
            score += score(s.charAt(i));
        }
        return score;
    }

    private static int score(char c) {
        char a = Character.isUpperCase(c) ? 'A' : 'a';
        return (c - a) + 1;
    }
}
