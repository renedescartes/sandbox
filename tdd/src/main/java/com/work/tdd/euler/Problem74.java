package com.work.tdd.euler;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.logging.Logger;

import static org.testng.Assert.assertEquals;

public class Problem74 {
    private static final Logger logger = Logger.getLogger(Problem74.class.getName());
    private static int[] chainLengths = new int[10000000];

    static {
        Arrays.fill(chainLengths, -1);
    }

    private static int chainLength(int n) {
        if (chainLengths[n] != -1) {
            return chainLengths[n];
        }
        int next = sumOfFactorialOfDigits(n);
        int answer;
        if (chainLengths[next] != -1) {
            answer = 1 + chainLengths[next];
            chainLengths[n] = answer;
            return answer;
        } else {
            answer = 1;
            Map<Integer, Integer> traversal = new LinkedHashMap<>();
            traversal.put(n, 0);
            while (!traversal.containsKey(next)) {
                traversal.put(next, answer);
                next = sumOfFactorialOfDigits(next);
                answer++;
            }
            boolean found = false;
            for (Map.Entry<Integer, Integer> entry : traversal.entrySet()) {
                if (!found && !entry.getKey().equals(next)) {
                    chainLengths[entry.getKey()] = traversal.size() - entry.getValue();
                } else {
                    found = true;
                    chainLengths[entry.getKey()] = traversal.size() - traversal.get(next);
                }
            }
            answer = chainLengths[n];
            return answer;
        }
    }

    public static int sumOfFactorialOfDigits(int n) {
        Integer[] digits = Utility.digits((long) n);
        int sum = 0;
        for (Integer digit : digits) {
            sum += Utility.factorial(digit);
        }
        return sum;
    }

    @DataProvider(name = "chains-data")
    public Object[][] chainData() {
        return new Object[][]{
                {69, 5},
                {363600, 4},
                {1454, 3},
                {169, 3},
                {363601, 3},
                {145, 1},
                {540, 2},
        };
    }

    public int explore(int start, int end) {
        int count = 0;
        for (int i = start; i <= end; i++) {
            int chainLength = chainLength(i);
            logger.fine("i = " + i + " chainLength[" + chainLength + "]");
            if (chainLength == 60) {
                logger.info("Number [" + i + "] added to solution");
                count++;
            }
        }
        return count;
    }

    @Test
    public void testSimple() {
        assertEquals(explore(1, 999999), 402);
    }

    @Test(dataProvider = "chains-data")
    public void testBits(int n, int l) {
        assertEquals(chainLength(n), l);
    }

}
