package com.work.tdd.euler;

import com.google.common.collect.Sets;
import org.testng.annotations.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.TreeSet;
import java.util.logging.Logger;

import static org.testng.Assert.assertEquals;

public class Problem71 {

    private static final Logger logger = Logger.getLogger(Problem71.class.getName());

    public static Fraction fractionBefore(final Fraction target, final int d) {
        Fraction answer = new Fraction(0,1);
        for(int den = 2; den <= d; den++) {
            for(int num = 1; num < den; num++) {
                Fraction f = new Fraction(num, den);
                if(f.compareTo(target) > 0) {
                    break;
                } else if(f.compareTo(target) < 0) {
                    if(f.compareTo(answer) > 0) {
                        logger.info("Prefer [" + f + "] against [" + answer + "]");
                        answer = f;
                    }
                }

            }
        }
        return answer;
    }

    private static Iterable<Fraction> fractionsForD(final int d) {
        final Iterator<Fraction> iterator = new Iterator<Fraction>() {
            int denominator = 2;
            int numerator = 1;
            @Override
            public boolean hasNext() {
                return denominator <= d;
            }

            @Override
            public Fraction next() {
                if(!hasNext()) {
                    throw new NoSuchElementException("No more elements");
                }
                Fraction f = new Fraction(numerator, denominator);
                numerator++;
                if(numerator == denominator) {
                    numerator = 1;
                    denominator++;
                }
                return f;
            }

            @Override
            public void remove() {
            }
        };
        return new Iterable<Fraction>() {
            @Override
            public Iterator<Fraction> iterator() {
                return iterator;
            }
        };
    }

    @Test
    public void testSimple() {
        Fraction f = fractionBefore(new Fraction(3, 7), 1000000);
        assertEquals(f, new Fraction(2, 5));
    }

    @Test
    public void testBits() {
        TreeSet<Fraction> fractions = Sets.newTreeSet(fractionsForD(8));
        assertEquals(fractions.size(), 21);
        logger.info(fractions.toString());
        Fraction fraction = fractionBefore(new Fraction(3, 7), 8);
        assertEquals(fraction, new Fraction(2, 5));
    }
}
