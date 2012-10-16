package com.work.tdd.euler.util;

import org.apache.commons.lang.StringUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static com.google.common.base.Strings.padStart;
import static com.google.common.collect.Lists.reverse;

class CombinationIterator<T> implements Iterator<List<T>> {
    private static final BigDecimal TWO = new BigDecimal("2");
    BigDecimal current = new BigDecimal("0");
    BigDecimal next = new BigDecimal("0");
    BigDecimal powerMax = new BigDecimal("0");
    final Integer c;
    final List<T> elements;

    CombinationIterator(List<T> elements, int c) {
        this.elements = reverse(elements);
        this.c = c;
        this.powerMax = new BigDecimal("2").pow(elements.size());
        this.next = nextFeasibleNumber(current, elements.size(), c);
    }

    @Override
    public boolean hasNext() {
        return next.compareTo(powerMax) < 0;
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

    private static BigDecimal nextFeasibleNumber(BigDecimal from, int size, int c) {
        while(numberOfOnes(toPaddedBinary((from = from.add(new BigDecimal("1"))), size)) != c);
        return from;
    }

    private static String toPaddedBinary(BigDecimal n, int requiredDigits) {
        return padStart(toBinary(n), requiredDigits, '0');
    }

    private static String toBinary(BigDecimal n) {
        StringBuffer b = new StringBuffer();
        while(!n.equals(new BigDecimal("0"))) {
            BigDecimal[] div = n.divideAndRemainder(TWO);
            n = div[0];
            b.append(div[1]);
        }
        return StringUtils.reverse(b.toString());
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
