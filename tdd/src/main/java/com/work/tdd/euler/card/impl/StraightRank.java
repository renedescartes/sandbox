package com.work.tdd.euler.card.impl;

import com.work.tdd.euler.card.HandRank;
import com.work.tdd.euler.card.Rank;

public class StraightRank extends AbstractRank {
    private Rank highestRank;

    public StraightRank(Rank rank) {
        this.highestRank = rank;
    }

    @Override
    int compareCurrentLevel(HandRank o) {
        return highestRank.compareTo(((StraightRank) o).highestRank);
    }

    @Override
    public Integer getLevel() {
        return 5;
    }
}
