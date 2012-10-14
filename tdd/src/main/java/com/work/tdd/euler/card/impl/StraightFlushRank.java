package com.work.tdd.euler.card.impl;

import com.work.tdd.euler.card.HandRank;
import com.work.tdd.euler.card.Rank;

public class StraightFlushRank extends AbstractRank {
    private Rank highestRank;

    public StraightFlushRank(Rank rank) {
        this.highestRank = rank;
    }

    @Override
    int compareCurrentLevel(HandRank o) {
        return highestRank.compareTo(((StraightFlushRank) o).highestRank);
    }

    @Override
    public Integer getLevel() {
        return 9;
    }

    @Override
    public String toString() {
        return "StraightFlushRank{" +
                "highestRank=" + highestRank +
                '}';
    }
}
