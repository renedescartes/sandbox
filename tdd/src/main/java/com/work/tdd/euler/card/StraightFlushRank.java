package com.work.tdd.euler.card;

public class StraightFlushRank implements HandRank {
    private Rank highestRank;

    public StraightFlushRank(Rank rank) {
        this.highestRank = rank;
    }

    @Override
    public int compareTo(HandRank o) {
        int levelCompare = getLevel().compareTo(o.getLevel());
        return levelCompare == 0 ? highestRank.compareTo(((StraightFlushRank) o).highestRank) : levelCompare;
    }

    @Override
    public Integer getLevel() {
        return 9;
    }
}
