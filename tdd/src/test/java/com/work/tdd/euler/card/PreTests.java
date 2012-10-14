package com.work.tdd.euler.card;

import com.work.tdd.euler.card.impl.OnePairRank;
import org.testng.annotations.Test;

import java.util.List;
import java.util.logging.Logger;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class PreTests {

    private static final Logger logger = Logger.getLogger(PreTests.class.getName());

    @Test
    public void testSimple() {
        Hand hand1 = HandParser.parseHand("5H 5C 6S 7S KD");
        assertTrue(hand1.getHandRank() instanceof OnePairRank);
        logger.info(hand1.toString());

        Hand hand2 = HandParser.parseHand("2C 3S 8S 8D TD");
        assertTrue(hand2.getHandRank() instanceof OnePairRank);
        logger.info(hand2.toString());

        assertTrue(hand1.compareTo(hand2) < 0);
    }

    @Test
    public void testParsing() {
        List<List<Hand>> games = HandParser.parseListOfGames("poker.txt");
        assertEquals(games.size(), 1000);
    }
}
