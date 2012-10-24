package com.work.tdd.euler;

import org.apache.commons.lang.builder.CompareToBuilder;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.Collection;
import java.util.TreeSet;
import java.util.logging.Logger;

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
        return a + ", " + b + ", " + c;
    }
}

public class Problem75 {
    private static final Logger logger = Logger.getLogger(Problem75.class.getName());

    public static Collection<Triple> explorePrimitiveTriples(long MAX) {
        Collection<Triple> triples = new TreeSet<>();
        for (long n = 1; n < MAX; n++) {
            for (long m = n + 1; m < MAX; m++) {
                if (Utility.gcd(m, n) == 1 && ((m - n) % 2 == 1)) {
                    long a = m * m - n * n;
                    long b = 2 * m * n;
                    long c = m * m + n * n;
                    if (a + b + c > MAX) {
                        break;
                    }
                    triples.add(new Triple(a, b, c));
                    logger.fine("Primitive (" + a + "," + b + "," + c + ")");
                }
            }
        }
        return triples;
    }

    @Test
    public void testBits() {
        Collection<Triple> triples = explorePrimitiveTriples(300);
        for (Triple triple : triples) {
            logger.info("Triple [" + triple + "]");
        }

    }
}
