package com.work.tdd.euler.util;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.apache.commons.lang.StringUtils.join;

public class Permutations {

    public static Iterable<BigInteger> permutationIterator(final BigInteger number) {
        final Iterator<BigInteger> iterator = new Iterator<BigInteger>() {
            List<Integer> digits = digits(number.toString());
            int totalCount = factorial(digits.size());
            int current = 0;
            @Override
            public boolean hasNext() {
                return current < totalCount;
            }

            @Override
            public BigInteger next() {
                return new BigInteger(join(getPermutation(digits, ++current), ""));
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException("Remove operation not implemented");
            }
        };
        return new Iterable<BigInteger>() {
            @Override
            public Iterator<BigInteger> iterator() {
                return iterator;
            }
        };
    }

    public static List<Integer> getPermutation(List<Integer> array, int permutationNumber) {
        List<Integer> inputs = new ArrayList<>(array);
        List<Integer> permutation = new ArrayList<>();
        int permutationIndex = permutationNumber;
        for(int i = inputs.size(); i > 2 ; i--) {
            Integer index = (permutationIndex -1)/ factorial(i - 1);
            permutationIndex = permutationIndex - (index * factorial(i-1));
            moveItems(permutation, inputs, index);
        }
        moveItems(permutation, inputs, (permutationIndex - 1));
        moveItems(permutation, inputs, 0);
        return permutation;
    }

    private static void moveItems(List<Integer> permutation, List<Integer> inputs, int index) {
        Integer element = inputs.get(index);
        permutation.add(element);
        inputs.remove(element);
    }

    private static int factorial(int n) {
        int f = 1;
        for(int i = 2 ; i <= n; i++) {
            f = f * i;
        }
        return f;
    }

    private static List<Integer> digits(String s) {
        List<Integer> digits = new ArrayList<>();
        for (int i = 0; i < s.length(); i++) {
            digits.add(s.charAt(i) - '0');
        }
        return digits;
    }
}
