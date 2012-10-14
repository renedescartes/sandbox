package com.work.tdd.euler.card;

import com.work.tdd.euler.card.impl.OnePairRank;
import org.testng.annotations.Test;

import java.util.logging.Logger;

import static org.testng.Assert.assertTrue;

public class PreTests {

    private static final Logger logger = Logger.getLogger(PreTests.class.getName());

    @Test
    public void testSimple() {
        Hand hand = HandParser.parseHand("5H 5C 6S 7S KD");
        assertTrue(hand.getHandRank() instanceof OnePairRank);
        logger.info(hand.toString());
    }
}
