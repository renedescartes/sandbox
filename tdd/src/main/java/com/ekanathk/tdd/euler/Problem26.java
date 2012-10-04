package com.ekanathk.tdd.euler;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import static org.testng.Assert.assertEquals;

/**
 * User: kannan
 * Date: 04/10/12
 */
public class Problem26 {

    private static final Logger logger = Logger.getLogger(Problem26.class.getName());

    public static String getDivision(int denominator) {
        List<Integer> quotients = new ArrayList<>();
        List<Integer> remainders = new ArrayList<>();
        int remainder = 1, quotient = 1, repetitionStartPoint = -1;
        while(remainder != 0 && repetitionStartPoint == -1) {
            logger.info("Quotient " + quotient + " Remainder " + remainder + " QuotientArray " + quotients + " Remainders " + remainders);
            remainder = (quotient * 10) % denominator;
            quotient = (quotient * 10) / denominator;
            if(remainder != 0 && isRepeatFound(quotients, remainders, quotient, remainder) != -1) {
                repetitionStartPoint = isRepeatFound(quotients, remainders, quotient, remainder);
            } else {
                quotients.add(quotient);
                remainders.add(remainder);
                quotient = remainder;
            }
        }
        String answer = "0." + prettyFormat(quotients, repetitionStartPoint);
        logger.info("The quotient is " + answer);
        return answer;
    }

    private static int isRepeatFound(List<Integer> quotients, List<Integer> remainders, int quotient, int remainder) {
        for(int i = 0; i < quotients.size(); i++) {
            if(quotients.get(i).equals(quotient) && remainders.get(i).equals(remainder)) {
                return i;
            }
        }
        return -1;
    }

    private static String prettyFormat(List<Integer> elements, int repetitionPoint) {
        StringBuffer b = new StringBuffer();
        for(int i = 0; i < elements.size(); i++) {
            if(i == repetitionPoint) {
                b.append("(");
            }
            b.append(elements.get(i));
        }
        if(repetitionPoint >= 0) {
            b.append(")");
        }
        return b.toString();
    }

    private static int findFirstOccurenceOf(List<Integer> elements, Integer find) {
        for(int i = 0; i < elements.size(); i++) {
            if(elements.get(i).equals(find)) {
                return i;
            }
        }
        return -1;
    }

    @DataProvider(name = "quotient-string")
    private Object[][] quotientData() {
        return new Object[][] {
                {2, "0.5"},
                {3, "0.(3)"},
                {4, "0.25"},
                {5, "0.2"},
                {6, "0.1(6)"},
                {7, "0.(142857)"},
                {8, "0.125"},
                {9, "0.(1)"},
                {10, "0.1"}
        };
    }

    @Test(dataProvider = "quotient-string")
    public void testSimple(int denominator, String output) {
        assertEquals(output, getDivision(denominator));
    }


}
