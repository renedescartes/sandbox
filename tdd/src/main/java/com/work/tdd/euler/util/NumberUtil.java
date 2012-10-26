package com.work.tdd.euler.util;

import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;

import java.util.*;

import static com.google.common.collect.Iterables.concat;
import static com.google.common.collect.Lists.newArrayList;
import static java.util.Arrays.asList;
import static java.util.Collections.unmodifiableList;

public class NumberUtil {

    public static List<Integer> splitIntoDigits(int n, int digitLength) {
        Preconditions.checkArgument(n > 0);
        int base = (int) Math.pow(10, digitLength);
        List<Integer> splits = new ArrayList<>();
        while (n > 0) {
            splits.add(n % base);
            n = n / base;
        }
        return Lists.reverse(splits);
    }

    public static List<Integer> digits(Number n) {
        String s = n.toString();
        List<Integer> digits = new ArrayList<>();
        for (int i = 0; i < s.length(); i++) {
            digits.add(s.charAt(i) - '0');
        }
        return digits;
    }

    public static boolean isPerfectSquare(Number number) {
        long sqrt = (long) Math.sqrt(number.longValue());
        return sqrt * sqrt == number.longValue();
    }

    public static long summation(Collection<? extends Number> array) {
        long sum = 0;
        for (Number n : array) {
            sum += n.longValue();
        }
        return sum;
    }

    public static List<Long> primeFactors(long n) {
        if (n % 2 == 0) {
            return unmodifiableList(newArrayList(concat(Arrays.asList(2L), primeFactors(n / 2))));
        }
        long a;
        for (a = (long) Math.sqrt(n); a <= n; a++) {
            if (isPerfectSquare(a * a - n)) {
                long b = (long) Math.sqrt(a * a - n);
                if (a + b < n) {
                    return unmodifiableList(newArrayList(concat(primeFactors(a + b), primeFactors(a - b))));
                }
            }
        }
        return n == 1 ? Collections.<Long>emptyList() : asList(n);
    }
}
