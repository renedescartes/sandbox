package com.work.tdd.euler.card;

import static com.work.tdd.euler.card.RankFunction.checkStraightFlush;

public class StraightFlushComputer implements RankComputer {
    @Override
    public HandRank handRank(Hand h) {
        Rank rank = checkStraightFlush(h);
        return rank == null ? new StraightFlushRank(rank) : null;
    }
}
