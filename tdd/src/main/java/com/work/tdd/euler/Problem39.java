package com.work.tdd.euler;

import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

import static org.testng.Assert.assertEquals;

public class Problem39 {

    private static final Logger logger = Logger.getLogger(Problem39.class.getName());

    private static boolean isRightTriangle(int a, int b, int c) {
        int[] sides = new int[]{a, b, c};
        Arrays.sort(sides);
        if(sides[0] == 0) {
            throw new IllegalArgumentException("Side cannot be zero");
        }
        return Math.pow(sides[2], 2) == Math.pow(sides[1], 2) + Math.pow(sides[0], 2);
    }

    public static List<List<Integer>> rightTriangleOptions(int perimeter) {
        List<List<Integer>> options = new ArrayList<>();
        for(int a = 1; a < perimeter - 1;a++) {
            for(int b = 1; b < perimeter -a;b++) {
                int c = perimeter - a - b;
                if(isRightTriangle(a, b ,c)) {
                    logger.fine("a = " + a + " b= " + b + " c= " + c);
                    options.add(Arrays.asList(a, b, c));
                }
            }
        }
        return options;
    }

    public static int bestPerimeter(int MAX) {
        int maxOptions = -1, answer = -1;
        for(int i = 0 ; i < MAX; i++) {
            int numberOfOptions = rightTriangleOptions(i).size();
            if(numberOfOptions > maxOptions) {
                maxOptions = numberOfOptions;
                answer = i;
            }
        }
        return answer;
    }

    @Test
    public void testSimple() {
        assertEquals(rightTriangleOptions(840).size(), 48);
        assertEquals(bestPerimeter(1001), 840);
    }
}
