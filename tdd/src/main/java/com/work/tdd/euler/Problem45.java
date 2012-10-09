package com.work.tdd.euler;

import org.testng.annotations.Test;

public class Problem45 {

    private static boolean isTriangular(long number) {
        if(number == 1) {
            return true;
        }
        for(int i = 1; i <= number; i++) {
            if((i * (i+1)/2) == number) {
                return true;
            }
        }
        return false;
    }

    private static boolean isPentagonal(long number) {
        if(number == 1) {
            return true;
        }
        for(int i = 1; i <= number; i++) {
            if((i * (3 * i -1)/2) == number) {
                return true;
            }
        }
        return false;
    }

    private static boolean isHexagonal(long number) {
        if(number == 1) {
            return true;
        }
        for(int i = 1; i <= number; i++) {
            if((i * ( 2 * i - 1)) == number) {
                return true;
            }
        }
        return false;
    }

    public static int findCommon(int from) {
        while(!(isTriangular(from) && isPentagonal(from) && isHexagonal(from))) {
            if(from % 10000 == 0) {
                System.out.println(from);
            }
            from++;
        }
        return from;
    }

    @Test
    public static void main(String[] args) {
        System.out.println(findCommon(40756));
    }
}
