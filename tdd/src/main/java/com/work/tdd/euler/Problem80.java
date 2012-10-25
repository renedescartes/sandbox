package com.work.tdd.euler;

import com.work.tdd.euler.util.fraction.Continuations;
import org.testng.annotations.Test;

import java.util.logging.Logger;

public class Problem80 {
    private static final Logger logger = Logger.getLogger(Problem79.class.getName());

    @Test
    public void testBits() {
        System.out.println(Continuations.convergentFractions(2, 4).decimalValue());
        System.out.println(Continuations.convergentFractions(2, 6).decimalValue());
        System.out.println(Continuations.convergentFractions(2, 20).decimalValue());
    }
}
