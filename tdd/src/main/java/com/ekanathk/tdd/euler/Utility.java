package com.ekanathk.tdd.euler;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.TreeSet;

/**
 * User: EkanathK
 * Date: 29/09/12
 */
public class Utility {

    public static Integer[] properDivisors(int n) {
        if(n == 0) {
            return new Integer[0];
        }
        Collection<Integer> divisors = new TreeSet<>();
        divisors.add(1);
        for(int i = 2; i <= Math.sqrt(n); i++) {
            if(n % i == 0) {
                divisors.add(i);
                divisors.add(n/i);
            }
        }
        return divisors.toArray(new Integer[divisors.size()]);
    }

    public static <T> boolean isValidIndex(T[] array, int index) {
        return index >=0 && index < array.length;
    }

    public static Integer summation(Integer[] array) {
        Integer sum = 0;
        for (int i = 0; i < array.length; i++) {
            sum = sum + array[i];
        }
        return sum;
    }
}
