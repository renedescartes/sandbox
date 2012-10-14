package com.work.tdd.euler.card;

import static com.work.tdd.euler.card.RankFunction.checkStraight;
import static com.work.tdd.euler.card.RankFunction.last;

public class StraightComputer implements RankComputer<StraightRank> {
    @Override
    public StraightRank handRank(Hand h) {
        return checkStraight(h) ? new StraightRank(last(h.getCards()).getRank()) : null;
    }
}
