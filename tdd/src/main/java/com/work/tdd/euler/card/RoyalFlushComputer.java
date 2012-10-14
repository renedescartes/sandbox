package com.work.tdd.euler.card;

import static com.work.tdd.euler.card.RankFunction.isRoyalFlush;

public class RoyalFlushComputer implements RankComputer<RoyalFlushRank> {
    @Override
    public RoyalFlushRank handRank(Hand h) {
        return isRoyalFlush(h) ? new RoyalFlushRank() : null;
    }
}
