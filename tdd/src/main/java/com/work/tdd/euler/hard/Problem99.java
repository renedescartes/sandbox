package com.work.tdd.euler.hard;

import com.google.common.collect.Lists;
import com.work.tdd.euler.medium.Utility;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

class BigExponent implements Comparable<BigExponent> {
    double base;
    double exponent;

    BigExponent(double base, double exponent) {
        this.base = base;
        this.exponent = exponent;
    }

    @Override
    public String toString() {
        return base + " ^ " + exponent;
    }

    @Override
    public int compareTo(BigExponent o) {
        double equivalentBase = Math.pow(base, exponent / o.exponent);
        return Double.compare(equivalentBase, o.base);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BigExponent that = (BigExponent) o;

        if (Double.compare(that.base, base) != 0) return false;
        if (Double.compare(that.exponent, exponent) != 0) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        temp = base != +0.0d ? Double.doubleToLongBits(base) : 0L;
        result = (int) (temp ^ (temp >>> 32));
        temp = exponent != +0.0d ? Double.doubleToLongBits(exponent) : 0L;
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }
}

public class Problem99 {
    private static final Logger logger = Logger.getLogger(Problem99.class.getName());

    private static List<BigExponent> readValues(String resource) {
        List<BigExponent> bigExponents = new ArrayList<>();
        List<String> strings = Utility.readFile(resource);
        for (String string : strings) {
            String[] split = string.split(",");
            bigExponents.add(new BigExponent(Double.parseDouble(split[0]), Double.parseDouble(split[1])));
        }
        return bigExponents;
    }

    public static int answer(String resource) {
        List<BigExponent> inputExponents = readValues(resource);
        logger.info("Read [" + inputExponents + "] exponential numbers");
        List<BigExponent> sortedExponents = Lists.newArrayList(inputExponents);
        Collections.sort(sortedExponents);
        BigExponent biggest = sortedExponents.get(sortedExponents.size() - 1);
        logger.info("Biggest exponential number [" + biggest + "]");
        for (int i = 0; i < inputExponents.size(); i++) {
            if (inputExponents.get(i).equals(biggest)) {
                logger.info("Returning [" + i + "]");
                return i + 1;
            }
        }
        throw new RuntimeException("Should not reach here");
    }

    @Test
    public void testSimple() {
        assertEquals(answer("base_exp.txt"), 709);
    }

    @Test
    public void testBits() {
        BigExponent e1 = new BigExponent(2, 11);
        BigExponent e2 = new BigExponent(3, 7);
        assertTrue(e1.compareTo(e2) < 0);
    }
}
