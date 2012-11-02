package com.work.tdd.euler.util.fraction;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.math.BigDecimal;
import java.math.BigInteger;

import static com.work.tdd.euler.util.fraction.Continuations.convergentFractions;
import static com.work.tdd.euler.util.fraction.Continuations.sequenceNumber;
import static com.work.tdd.euler.util.fraction.Fractions.bigIntegerFraction;
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
        assertEquals(convergentFractions(2, 4), bigIntegerFraction(17, 12));
        assertEquals(convergentFractions(2, 1), bigIntegerFraction(1, 1));
        assertEquals(convergentFractions(2, 2), bigIntegerFraction(3, 2));
        assertEquals(convergentFractions(2, 3), bigIntegerFraction(7, 5));
    }

    @Test
    public void testSquareRoot() {
        assertEquals(Continuations.squareRoot(2, 12), new BigDecimal("1.414213562373"));
        assertEquals(Continuations.squareRoot(9, 12).intValue(), 3);
    }

    @Test
    public void testSquareRootBigInt() {
        assertEquals(Continuations.squareRoot(new BigInteger("9"), 12).toString(), "3.0"); // only one precision
        assertEquals(Continuations.squareRoot(new BigInteger("45"), 12).toString(), "6.708203932499");
        BigInteger bigInteger = new BigInteger("243635463465").multiply(new BigInteger("243635463465"));
        BigDecimal root = Continuations.squareRoot(bigInteger, 12);
        assertEquals(root.toString(), "243635463465.0");
        assertEquals(root.precision(), 0);
    }
}
