package com.work.tdd.euler.util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Permutations {

    public static <T> Iterable<List<T>> permutationIterator(final List<T> inputs) {
        final Iterator<List<T>> iterator = new Iterator<List<T>>() {
            int totalCount = factorial(inputs.size());
            int current = 0;

            @Override
            public boolean hasNext() {
                return current < totalCount;
            }

            @Override
            public List<T> next() {
                return getPermutation(inputs, ++current);
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException("Remove operation not implemented");
            }
        };
        return new Iterable<List<T>>() {
            @Override
            public Iterator<List<T>> iterator() {
                return iterator;
            }
        };
    }

    public static <T> List<T> getPermutation(List<T> array, int permutationNumber) {
        List<T> inputs = new ArrayList<>(array);
        List<T> permutation = new ArrayList<>();
        int permutationIndex = permutationNumber;
        for (int i = inputs.size(); i > 2; i--) {
            Integer index = (permutationIndex - 1) / factorial(i - 1);
            permutationIndex = permutationIndex - (index * factorial(i - 1));
            moveItems(permutation, inputs, index);
        }
        moveItems(permutation, inputs, (permutationIndex - 1));
        moveItems(permutation, inputs, 0);
        return permutation;
    }

    private static <T> void moveItems(List<T> permutation, List<T> inputs, int index) {
        T element = inputs.get(index);
        permutation.add(element);
        inputs.remove(element);
    }

    private static int factorial(int n) {
        int f = 1;
        for (int i = 2; i <= n; i++) {
            f = f * i;
        }
        return f;
    }
}
