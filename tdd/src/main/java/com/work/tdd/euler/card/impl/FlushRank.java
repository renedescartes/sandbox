package com.work.tdd.euler.card.impl;

import com.work.tdd.euler.card.HandRank;
import com.work.tdd.euler.card.Rank;

import java.util.List;

import static com.work.tdd.euler.card.impl.RankFunction.rankComparison;

public class FlushRank extends AbstractRank {
    private List<Rank> ranks;

    public FlushRank(List<Rank> ranks) {
        this.ranks = ranks;
    }

    @Override
    public Integer getLevel() {
        return 6;
    }

    @Override
    int compareCurrentLevel(HandRank o) {
        return rankComparison(ranks, ((FlushRank) o).ranks);
    }

    @Override
    public String toString() {
        return "FlushRank{" +
                "ranks=" + ranks +
                '}';
    }
}
