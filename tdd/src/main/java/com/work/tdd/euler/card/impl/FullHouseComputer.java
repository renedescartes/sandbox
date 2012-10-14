package com.work.tdd.euler.card.impl;

import com.work.tdd.euler.card.Hand;
import com.work.tdd.euler.card.Rank;
import com.work.tdd.euler.card.RankComputer;
import com.work.tdd.euler.card.RankFunction;

public class FullHouseComputer implements RankComputer<FullHouseRank> {
    @Override
    public FullHouseRank handRank(Hand h) {
        Rank[] ranks = RankFunction.fullHouse(h);
        return ranks != null ? new FullHouseRank(ranks[0], ranks[1]) : null;
    }
}
