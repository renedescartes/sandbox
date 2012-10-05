package com.ekanathk.tdd.euler;

import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ListIterator;

import static org.testng.Assert.assertEquals;

/**
 * User: ekanathk
 * Date: 06/10/12
 */
public class Problem33 {

    /** Find gcd of two numbers*/
    private static int gcd(int a, int b) {
        if(a > b) {
            return gcd(b, a);
        }
        if(a == 0) {
            return b;
        }
        return gcd(b % a, a);
    }

    /** Cut common factors example 4/8 will become 1/2*/
    private static Fraction reduce(Fraction f) {
        Fraction reduced = f;
        int gcd;
        while ((gcd = gcd(reduced.numerator, reduced.denominator)) != 1) {
            reduced = new Fraction(f.numerator/gcd, f.denominator/gcd);
        }
        return reduced;
    }

    /** Incorrectly reduce example 43/83 will become 4/8*/
    private static Fraction incorrectReduce(Fraction f) {
        List<Character> nList = charList("" + f.numerator), dList = charList("" + f.denominator);
        ListIterator<Character> it = nList.listIterator();
        while(it.hasNext()) {
            Character c = it.next();
            if(dList.contains(c) && !c.equals('0')) {
                dList.remove(c);
                it.remove();
            }
        }
        return new Fraction(join(nList), join(dList));
    }

    /** Join a list of char ints into a big integer*/
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

    /** Separate a number into a list of digits*/
    private static List<Character> charList(String s) {
        char[] array = s.toCharArray();
        List<Character> l = new ArrayList<>();
        for (int i = 0; i < array.length; i++) {
            l.add(array[i]);
        }
        return l;
    }

    /** Iterate and find the fractions*/
    protected static List<Fraction> getFractions(int number) {
        List<Fraction> fractions = new ArrayList<>();
        for(int n = 1; n < number; n++) {
            for(int d = n+1; d < number; d++) {
                Fraction f = new Fraction(n, d);
                boolean incorrectReduced = !f.equals(incorrectReduce(f));
                if(incorrectReduced && reduce(f).equals(reduce(incorrectReduce(f)))) {
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
        System.out.println(getFractions(100));
    }
}

class Fraction {
    int numerator, denominator;

    Fraction(int numerator, int denominator) {
        this.numerator = numerator;
        this.denominator = denominator;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Fraction fraction = (Fraction) o;

        if (denominator != fraction.denominator) return false;
        if (numerator != fraction.numerator) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = numerator;
        result = 31 * result + denominator;
        return result;
    }

    @Override
    public String toString() {
        return "Fraction {" + numerator +
                " / " + denominator +
                '}';
    }

}