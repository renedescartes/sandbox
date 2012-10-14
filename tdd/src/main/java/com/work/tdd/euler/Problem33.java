package com.work.tdd.euler;

import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import static com.work.tdd.euler.Utility.*;
import static org.testng.Assert.assertEquals;

/**
 * User: work
 * Date: 06/10/12
 */
public class Problem33 {

    /**
     * Incorrectly reduce example 43/83 will become 4/8
     */
    private static Fraction incorrectReduce(Fraction f) {
        List<Character> nList = charList("" + f.numerator), dList = charList("" + f.denominator);
        ListIterator<Character> it = nList.listIterator();
        while (it.hasNext()) {
            Character c = it.next();
            if (dList.contains(c) && !c.equals('0')) {
                dList.remove(c);
                it.remove();
            }
        }
        return new Fraction(join(nList), join(dList));
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
    protected static List<Fraction> getFractions(int number) {
        List<Fraction> fractions = new ArrayList<>();
        for (int n = 1; n < number; n++) {
            for (int d = n + 1; d < number; d++) {
                Fraction f = new Fraction(n, d);
                boolean incorrectReduced = !f.equals(incorrectReduce(f));
                if (incorrectReduced && reduce(f).equals(reduce(incorrectReduce(f)))) {
                    fractions.add(f);
                }
            }
        }
        return fractions;
    }

    @Test
    public void testGcd() {
        assertEquals(5, gcd(25, 30));
        assertEquals(2, gcd(58, 60));
        assertEquals(1, gcd(19, 14));

        assertEquals(new Fraction(1, 2), reduce(new Fraction(16, 32)));
        assertEquals(new Fraction(1, 3), incorrectReduce(new Fraction(14, 34)));
        List<Fraction> fractions = getFractions(100);
        Fraction product = new Fraction(1, 1);
        for (Fraction fraction : fractions) {
            product = multiply(product, fraction);
        }
        System.out.println(product.denominator);
    }
}

