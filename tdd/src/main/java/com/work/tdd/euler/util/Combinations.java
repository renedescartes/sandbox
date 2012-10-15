package com.work.tdd.euler.util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static com.google.common.base.Strings.padStart;
import static com.google.common.collect.Lists.reverse;

class CombinationIterator<T> implements Iterator<List<T>> {
    Long current = 0L;
    Long next = 0L;
    Long powerMax = 0L;
    final Integer c;
    final List<T> elements;

    CombinationIterator(List<T> elements, int c) {
        this.elements = reverse(elements);
        this.c = c;
        this.powerMax = (long) Math.pow(2, elements.size());
        this.next = nextFeasibleNumber(current, elements.size(), c);
    }

    @Override
    public boolean hasNext() {
        return next <= powerMax;
    }

    @Override
    public List<T> next() {
        if(!hasNext()) {
            throw new IllegalStateException("Done iteration");
        }
        current = next;
        next = nextFeasibleNumber(current, elements.size(), c);
        return constructCombination(elements, toPaddedBinary(current, elements.size()));
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException("this operation is not supported");
    }

    private static long nextFeasibleNumber(Long from, int size, int c) {
        while(numberOfOnes(toPaddedBinary(++from, size)) != c);
        return from;
    }

    private static String toPaddedBinary(long n, int requiredDigits) {
        return padStart(Long.toBinaryString(n), requiredDigits, '0');
    }

    private static int numberOfOnes(String binary) {
        int count = 0;
        for(int i = 0; i < binary.length(); i++) {
            if(binary.charAt(i) == '1') {
                count++;
            }
        }
        return count;
    }

    private static <T> List<T> constructCombination(List<T> elements, String binary) {
        List<T> newList = new ArrayList<>();
        for(int i = 0; i < binary.length(); i++) {
            if(binary.charAt(i) == '1') {
                newList.add(elements.get(i));
            }
        }
        return newList;
    }

}

public class Combinations {

    public static <T> Iterable<List<T>> combinationIterator(final List<T> elements, int c) {

        final Iterator<List<T>> i = new CombinationIterator<>(elements, c);
        return new Iterable<List<T>>() {
            @Override
            public Iterator<List<T>> iterator() {
                return i;
            }
        };
    }


}
