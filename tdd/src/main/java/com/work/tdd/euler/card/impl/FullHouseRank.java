package com.work.tdd.euler.card.impl;

import com.work.tdd.euler.card.HandRank;
import com.work.tdd.euler.card.Rank;

import static com.google.common.base.Preconditions.checkNotNull;
import static com.work.tdd.euler.card.impl.RankFunction.rankComparison;
import static java.util.Arrays.asList;

public class FullHouseRank extends AbstractRank {

    private Rank threeKindRank, twoKindRank;

    public FullHouseRank(Rank threeKindRank, Rank twoKindRank) {
        checkNotNull(threeKindRank);
        checkNotNull(twoKindRank);
        this.threeKindRank = threeKindRank;
        this.twoKindRank = twoKindRank;
    }

    @Override
    public Integer getLevel() {
        return 7;
    }

    @Override
    int compareCurrentLevel(HandRank o) {
        FullHouseRank next = (FullHouseRank) o;
        return rankComparison(asList(threeKindRank, twoKindRank),
                asList(next.threeKindRank, next.twoKindRank));
    }

    @Override
    public String toString() {
        return "FullHouseRank{" +
                "threeKindRank=" + threeKindRank +
                ", twoKindRank=" + twoKindRank +
                '}';
    }
}
