package com.work.tdd.euler;

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

    /** Length of Sequence. For Denominator = 7 => 1/7 = 0.(142857) this will return 6 as 6 digits are repeated*/
    public static int getRepetitionLength(int denominator) {
        String quotient = getDivisionWithRepetition(denominator);
        int start = quotient.indexOf("(");
        int end = quotient.indexOf(")");
        return start == -1 ? 0 : (end - start - 1);
    }

    /** Gets the String representation so if 7 is passed then we get back "0.(142857" */
    public static String getDivisionWithRepetition(int denominator) {
        List<Integer> quotients = new ArrayList<>();
        List<Integer> remainders = new ArrayList<>();
        int remainder = 1, quotient = 1, repetitionStartPoint = -1;
        while(remainder != 0) {
            logger.fine("Quotient " + quotient + " Remainder " + remainder + " QuotientArray " + quotients + " Remainders " + remainders);
            remainder = (quotient * 10) % denominator;
            quotient = (quotient * 10) / denominator;
            if((repetitionStartPoint = isRepeatFound(quotients, remainders, quotient, remainder)) != -1) {
                remainder = 0;
            } else {
                quotients.add(quotient);
                remainders.add(remainder);
                quotient = remainder;
            }
        }
        String answer = "0." + prettyFormat(quotients, repetitionStartPoint);
        logger.fine("The quotient is " + answer);
        return answer;
    }

    private static int isRepeatFound(List<Integer> quotients, List<Integer> remainders, int quotient, int remainder) {
        if(remainder == 0) {
            return -1;
        }
        for(int i = 0; i < quotients.size(); i++) {
            if(quotients.get(i).equals(quotient) && remainders.get(i).equals(remainder)) {
                return i;
            }
        }
        return -1;
    }

    /** Just pretty format it*/
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

    @DataProvider(name = "quotient-string")
    private Object[][] quotientData() {
        return new Object[][] {
                {2, "0.5", 0},
                {3, "0.(3)", 1},
                {4, "0.25", 0},
                {5, "0.2", 0},
                {6, "0.1(6)", 1},
                {7, "0.(142857)", 6},
                {8, "0.125", 0},
                {9, "0.(1)", 1},
                {10, "0.1", 0}
        };
    }

    @Test(dataProvider = "quotient-string")
    public void testSimple(int denominator, String output, int repetitionLength) {
        assertEquals(getDivisionWithRepetition(denominator), output);
        assertEquals(getRepetitionLength(denominator), repetitionLength);
    }

    @Test
    public void testAnswer() {
        int longestCycle = -1; int d = -1;
        for(int i = 2; i < 1000; i++) {
            int cycleLength = getRepetitionLength(i);
            logger.info("number = " + i + " cycleLength = " + cycleLength + " longestCycle = " + longestCycle);
            if(cycleLength > longestCycle) {
                longestCycle = cycleLength;
                d = i;
            }
        }
        System.out.println("The answer is " + d);
        System.out.println("The answer is " + getDivisionWithRepetition(983));
    }


}
