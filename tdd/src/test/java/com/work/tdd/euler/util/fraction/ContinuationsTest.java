package com.work.tdd.euler.util.fraction;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static com.work.tdd.euler.util.fraction.Continuations.sequenceNumber;
import static org.testng.Assert.assertEquals;

public class ContinuationsTest {

    @DataProvider(name = "term-data")
    private Object[][] termData() {
        return new Object[][]{
                {2, 4, 2},
                {2, 5, 2},
                {2, 2, 2},
                {2, 1, 1},
                {13, 1, 3},
                {13, 5, 1},
                {13, 11, 6},
                {19, 3, 1},
                {19, 7, 8},
                {19, 8, 2},
                {19, 9, 1},
                {19, 12, 2},
                {19, 13, 8},
                {19, 14, 2},
        };
    }

    @Test(dataProvider = "term-data")
    public void testSequenceNumber(int number, int term, int value) {
        assertEquals(sequenceNumber(number, term).intValue(), value);
    }

    @Test
    public void testSimple() {
        Fraction fraction = Continuations.convergentFractions(2, 4);
        assertEquals(fraction, Fractions.bigIntegerFraction(17, 12));
    }
}
