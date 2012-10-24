package com.work.tdd.euler;

import org.apache.commons.lang.builder.CompareToBuilder;
import org.testng.annotations.Test;
import org.testng.internal.annotations.Sets;

import java.util.Arrays;
import java.util.Collection;
import java.util.logging.Logger;

import static org.testng.Assert.assertEquals;

class Triple implements Comparable<Triple> {
    long a, b, c;

    Triple(long a, long b, long c) {
        long[] array = new long[]{a, b, c};
        Arrays.sort(array);
        this.a = array[0];
        this.b = array[1];
        this.c = array[2];
    }

    @Override
    public int compareTo(Triple o) {
        return new CompareToBuilder().append(a, o.a).append(b, o.b).append(c, o.c).toComparison();
    }

    @Override
    public String toString() {
        return "{" + a + ", " + b + ", " + c + "}";
    }

    long perimeter() {
        return a + b + c;
    }

    Triple times(int factor) {
        return new Triple(a * factor, b * factor, c * factor);
    }
}

public class Problem75 {
    private static final Logger logger = Logger.getLogger(Problem75.class.getName());

    public static Object[] explorePrimitiveTriples(int MAX) {
        Object[] array = new Object[MAX];
        for (long n = 1; n < MAX; n++) {
            for (long m = n + 1; m < MAX; m++) {
                if (Utility.gcd(m, n) == 1 && ((m - n) % 2 == 1)) {
                    long a = m * m - n * n;
                    long b = 2 * m * n;
                    long c = m * m + n * n;
                    if ((a + b + c) > (long) MAX) {
                        break;
                    }
                    Triple triple = new Triple(a, b, c);
                    Triple original = triple;
                    int factor = 1;
                    long perimeter;
                    while ((perimeter = triple.perimeter()) < MAX) {
                        if (array[(int) perimeter] == null) {
                            array[(int) perimeter] = Sets.<Triple>newHashSet();
                        }
                        @SuppressWarnings("unchecked")
                        Collection<Triple> triples = (Collection<Triple>) array[(int) perimeter];
                        triples.add(triple);
                        logger.fine("Triple [" + triple + "]");
                        factor++;
                        triple = original.times(factor);
                    }
                }
            }
        }
        return array;
    }

    public static long countSinglePrimitives(int MAX) {
        Object[] objects = explorePrimitiveTriples(MAX);
        long sum = 0;
        for (int i = 0; i < objects.length; i++) {
            @SuppressWarnings("unchecked")
            Collection<Triple> object = (Collection<Triple>) objects[i];
            if (object != null && object.size() == 1) {
                logger.info("Number [" + i + "] is a solution");
                sum++;
            }
        }
        return sum;
    }


    @Test
    public void testSimple() {
        assertEquals(countSinglePrimitives(1500000), 161667);
    }

    @Test
    public void testBits() {
        Object[] triplesCollection = explorePrimitiveTriples(1500000);
        for (int i = 0; i < triplesCollection.length; i++) {
            Object triple = triplesCollection[i];
            if (triple != null) {
                logger.info("i = [" + i + "] triples " + triple);
            }
        }
    }
}
