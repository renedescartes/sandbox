package com.work.tdd.euler.card;

import org.testng.annotations.Test;

public class PreTests {

    @Test
    public void testSimple() {
        HandParser.parseHand("5H 5C 6S 7S KD");
    }
}
