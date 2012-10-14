package com.work.tdd.euler.card;

public class FullHouseComputer implements RankComputer<FullHouseRank> {
    @Override
    public FullHouseRank handRank(Hand h) {
        Rank[] ranks = RankFunction.fullHouse(h);
        return ranks != null ? new FullHouseRank(ranks[0], ranks[1]) : null;
    }
}
