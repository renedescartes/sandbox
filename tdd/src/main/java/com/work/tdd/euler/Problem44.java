package com.work.tdd.euler;

import org.testng.annotations.Test;

import java.util.logging.Logger;

public class Problem44 {

    private static final Logger logger = Logger.getLogger(Problem44.class.getName());

    private static boolean isPerfectSquare(long number) {
        long sqrt = (long) Math.sqrt(number);
        return sqrt * sqrt == number;
    }

    private static boolean isPentagonal(long number) {
        long check = (24 * number) + 1;
        return isPerfectSquare(check) && (Math.sqrt(check) % 6 == 5);
    }

    private static long getNthPentagonal(long n) {
        return n * (3 * n -1)/2;
    }

    public static long checkIndex(long j) {
        long pj = getNthPentagonal(j);
        for(int i = 1; i < j; i++) {
            long pi = getNthPentagonal(i);
            if(isPentagonal(pj - pi) && isPentagonal(pj + pi)) {
                return i;
            }
        }
        return -1;
    }

    public static long answer() {
        long j = 2, i;
        while((i = checkIndex(j++)) == -1);
        logger.info("i [" + i + "] j [" + j + "]");
        return getNthPentagonal(j) - getNthPentagonal(i);
    }

    @Test
    public void testSimple() {
        System.out.println(answer());
    }
}
