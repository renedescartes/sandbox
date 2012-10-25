package com.work.tdd.euler.util;

import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.List;

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
}
