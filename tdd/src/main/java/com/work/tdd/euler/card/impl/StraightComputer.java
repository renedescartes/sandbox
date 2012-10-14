package com.work.tdd.euler.card.impl;

import com.work.tdd.euler.card.Hand;
import com.work.tdd.euler.card.RankComputer;

import static com.work.tdd.euler.card.impl.RankFunction.checkStraight;
import static com.work.tdd.euler.card.impl.RankFunction.last;

public class StraightComputer implements RankComputer<StraightRank> {
    @Override
    public StraightRank handRank(Hand h) {
        return checkStraight(h) ? new StraightRank(last(h.getCards()).getRank()) : null;
    }
}
