package com.work.tdd.euler;

import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;

public class Problem42 {
    private static final Logger logger = Logger.getLogger(Problem42.class.getName());

    private static List<Integer> scores(List<String> words) {
        List<Integer> scores = new ArrayList<>();
        for(String s : words) {
            scores.add(score(s.substring(1, s.length()-1)));
        }
        Collections.sort(scores);
        return scores;
    }

    private static int countTriangular(List<Integer> numbers) {
        logger.info(numbers.toString());
        int count = 0;
        for(Integer i : numbers) {
            if(isTriangular(i)) {
                logger.info(i.toString());
                count++;
            }
        }
        return count;
    }

    private static boolean isTriangular(int number) {
        for(int i = 1; i < number; i++) {
            if((i * (i+1)/2) == number) {
                return true;
            }
        }
        return false;
    }

    private static List<String> readWords(String resourceName) {
        List<String> strings = Utility.readFile(resourceName);
        return Arrays.asList(strings.get(0).split(","));
    }

    private static int score(String word) {
        int score = 0;
        for(int i = 0; i < word.length(); i++) {
            score += (word.charAt(i) - 'A' + 1);
        }
        return score;
    }

    public static int answer(String resource) {
        return countTriangular(scores(readWords(resource)));
    }

    @Test
    public void testSimple() {
        System.out.println(Problem42.answer("words.txt"));
    }
}
