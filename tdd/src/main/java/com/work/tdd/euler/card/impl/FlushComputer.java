package com.work.tdd.euler.card.impl;

import com.work.tdd.euler.card.Hand;
import com.work.tdd.euler.card.RankComputer;

import static com.work.tdd.euler.card.RankFunction.checkFlush;

public class FlushComputer implements RankComputer<FlushRank> {
    @Override
    public FlushRank handRank(Hand h) {
        return checkFlush(h) ? new FlushRank(h.getCards()) : null;
    }
}
