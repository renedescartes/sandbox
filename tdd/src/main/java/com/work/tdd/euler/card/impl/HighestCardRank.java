package com.work.tdd.euler.card.impl;

import com.work.tdd.euler.card.HandRank;
import com.work.tdd.euler.card.Rank;

import java.util.List;

import static com.work.tdd.euler.card.RankFunction.rankComparison;

public class HighestCardRank extends AbstractRank {

    private List<Rank> ranks;

    public HighestCardRank(List<Rank> ranks) {
        this.ranks = ranks;
    }

    @Override
    public Integer getLevel() {
        return 1;
    }

    @Override
    int compareCurrentLevel(HandRank o) {
        HighestCardRank next = (HighestCardRank) o;
        return rankComparison(ranks, next.ranks);
    }
}
