package com.work.tdd.euler.card;

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