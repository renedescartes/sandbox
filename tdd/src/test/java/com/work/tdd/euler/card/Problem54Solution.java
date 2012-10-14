package com.work.tdd.euler.card;

import org.testng.annotations.Test;

import java.util.List;
import java.util.logging.Logger;

import static org.testng.Assert.assertEquals;

public class Problem54Solution {
    private static final Logger logger = Logger.getLogger(Problem54Solution.class.getName());

    @Test
    public void testParsing() {
        List<List<Hand>> games = HandParser.parseListOfGames("poker.txt");
        assertEquals(games.size(), 1000);

        int player1Win = 0;
        for (int i = 0; i < games.size(); i++) {
            logger.info("Game number [" + (i + 1) + "]");
            Hand hand1 = games.get(i).get(0), hand2 = games.get(i).get(1);
            int compare = hand1.compareTo(hand2);
            logger.info("Hand1 " + hand1 + " Hand2 " + hand2 + " Winner - " +
                    ((compare > 0) ? "Hand1" : "Hand2"));
            player1Win += compare > 0 ? 1 : 0;
        }
        assertEquals(player1Win, 376);


    }
}
