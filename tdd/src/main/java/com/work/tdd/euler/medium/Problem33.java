package com.work.tdd.euler.medium;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import static org.testng.Assert.assertEquals;

/**
 * User: work
 * Date: 06/10/12
 */
public class Problem33 {

    /**
     * Incorrectly reduce example 43/83 will become 4/8
     */
    private static BigIntegerFraction incorrectReduce(BigIntegerFraction f) {
        List<Character> nList = charList("" + f.numerator), dList = charList("" + f.denominator);
        ListIterator<Character> it = nList.listIterator();
        while (it.hasNext()) {
            Character c = it.next();
            if (dList.contains(c) && !c.equals('0')) {
                dList.remove(c);
                it.remove();
            }
        }
        return new BigIntegerFraction(join(nList), join(dList));
    }

    /**
     * Join a list of char ints into a big integer
     */
    private static int join(List<Character> l) {
        if (l.isEmpty()) {
            return 1;
        }
        StringBuffer b = new StringBuffer();
        for (Character character : l) {
            b.append(character);
        }
        return Integer.parseInt(b.toString());
    }

    /**
     * Separate a number into a list of digits
     */
    private static List<Character> charList(String s) {
        char[] array = s.toCharArray();
        List<Character> l = new ArrayList<>();
        for (int i = 0; i < array.length; i++) {
            l.add(array[i]);
        }
        return l;
    }

    /**
     * Iterate and find the fractions
     */
    protected static List<BigIntegerFraction> getFractions(int number) {
        List<BigIntegerFraction> fractions = new ArrayList<>();
        for (int n = 1; n < number; n++) {
            for (int d = n + 1; d < number; d++) {
                BigIntegerFraction f = new BigIntegerFraction(n, d);
                boolean incorrectReduced = !f.equals(incorrectReduce(f));
                if (incorrectReduced && Utility.reduce(f).equals(Utility.reduce(incorrectReduce(f)))) {
                    fractions.add(f);
                }
            }
        }
        return fractions;
    }

    @Test
    public void testGcd() {
        Assert.assertEquals(5, Utility.gcd(25, 30));
        Assert.assertEquals(2, Utility.gcd(58, 60));
        Assert.assertEquals(1, Utility.gcd(19, 14));

        assertEquals(new BigIntegerFraction(1, 2), Utility.reduce(new BigIntegerFraction(16, 32)));
        assertEquals(new BigIntegerFraction(1, 3), incorrectReduce(new BigIntegerFraction(14, 34)));
        List<BigIntegerFraction> fractions = getFractions(100);
        BigIntegerFraction product = new BigIntegerFraction(1, 1);
        for (BigIntegerFraction fraction : fractions) {
            product = Utility.multiply(product, fraction);
        }
        System.out.println(product.denominator);
    }
}

