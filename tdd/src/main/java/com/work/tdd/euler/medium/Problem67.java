package com.work.tdd.euler.medium;

import org.testng.annotations.Test;

import java.util.List;
import java.util.logging.Logger;

import static org.testng.Assert.assertEquals;

public class Problem67 {

    private static final Logger logger = Logger.getLogger(Problem71.class.getName());

    private static int[][] numberTree(String resource) {
        List<String> numbers = Utility.readFile(resource);
        int[][] answer = new int[numbers.size()][];
        for (int i = 0; i < numbers.size(); i++) {
            String[] split = numbers.get(i).split(" ");
            answer[i] = new int[i + 1];
            for (int j = 0; j < answer[i].length; j++) {
                answer[i][j] = Integer.parseInt(split[j]);
            }
        }
        return answer;
    }

    @Test
    public void testSimple() {
        int[][] array = numberTree("triangle.txt");
        logger.info("" + array);
        assertEquals(Problem18.maximalSum(array), 7273);
    }
}
