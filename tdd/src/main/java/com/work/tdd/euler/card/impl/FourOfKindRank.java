package com.work.tdd.euler.card.impl;

import com.work.tdd.euler.card.HandRank;
import com.work.tdd.euler.card.Rank;

import static com.google.common.base.Preconditions.checkNotNull;
import static com.work.tdd.euler.card.impl.RankFunction.rankComparison;
import static java.util.Arrays.asList;

public class FourOfKindRank extends AbstractRank {

    private Rank fourKindRank, otherRank;

    public FourOfKindRank(Rank fourKindRank, Rank otherRank) {
        checkNotNull(fourKindRank);
        checkNotNull(otherRank);
        this.fourKindRank = fourKindRank;
        this.otherRank = otherRank;
    }

    @Override
    public Integer getLevel() {
        return 8;
    }

    @Override
    int compareCurrentLevel(HandRank o) {
        FourOfKindRank next = (FourOfKindRank) o;
        return rankComparison(asList(fourKindRank, otherRank),
                asList(next.fourKindRank, next.otherRank));
    }

    @Override
    public String toString() {
        return "FourOfKindRank{" +
                "fourKindRank=" + fourKindRank +
                ", otherRank=" + otherRank +
                '}';
    }
}
