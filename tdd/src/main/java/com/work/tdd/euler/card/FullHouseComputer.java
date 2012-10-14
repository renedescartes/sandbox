package com.work.tdd.euler.card;

public class FullHouseComputer implements RankComputer<FourOfKindRank> {
    @Override
    public FourOfKindRank handRank(Hand h) {
        Rank[] ranks = RankFunction.fullHouse(h);
        return ranks != null ? new FourOfKindRank(ranks[0], ranks[1]) : null;
    }
}
