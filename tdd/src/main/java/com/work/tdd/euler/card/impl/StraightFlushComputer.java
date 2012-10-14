package com.work.tdd.euler.card.impl;

import com.work.tdd.euler.card.Hand;
import com.work.tdd.euler.card.Rank;
import com.work.tdd.euler.card.RankComputer;

import static com.work.tdd.euler.card.impl.RankFunction.checkStraightFlush;

public class StraightFlushComputer implements RankComputer<StraightFlushRank> {
    @Override
    public StraightFlushRank handRank(Hand h) {
        Rank rank = checkStraightFlush(h);
        return rank != null ? new StraightFlushRank(rank) : null;
    }
}
