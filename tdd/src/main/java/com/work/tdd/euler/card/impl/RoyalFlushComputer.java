package com.work.tdd.euler.card.impl;

import com.work.tdd.euler.card.Hand;
import com.work.tdd.euler.card.RankComputer;

import static com.work.tdd.euler.card.RankFunction.isRoyalFlush;

public class RoyalFlushComputer implements RankComputer<RoyalFlushRank> {
    @Override
    public RoyalFlushRank handRank(Hand h) {
        return isRoyalFlush(h) ? new RoyalFlushRank() : null;
    }
}
