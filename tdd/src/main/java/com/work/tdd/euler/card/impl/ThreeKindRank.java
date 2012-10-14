package com.work.tdd.euler.card.impl;

import com.work.tdd.euler.card.HandRank;
import com.work.tdd.euler.card.Rank;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;
import static com.google.common.collect.Lists.newArrayList;
import static com.work.tdd.euler.card.impl.RankFunction.rankComparison;
import static java.util.Arrays.asList;

public class ThreeKindRank extends AbstractRank {

    private Rank threeKindRank, otherRank1, otherRank2;

    public ThreeKindRank(Rank threeKindRank, Rank otherRank1, Rank otherRank2) {
        checkNotNull(threeKindRank);
        checkNotNull(otherRank1);
        checkNotNull(otherRank2);
        checkArgument(otherRank1 != otherRank2, "In three kind the other ranks have to be different");
        this.threeKindRank = threeKindRank;
        this.otherRank1 = otherRank1;
        this.otherRank2 = otherRank2;
    }

    @Override
    public Integer getLevel() {
        return 4;
    }

    @Override
    int compareCurrentLevel(HandRank o) {
        ThreeKindRank next = (ThreeKindRank) o;
        return rankComparison(
                newArrayList(asList(threeKindRank, otherRank1, otherRank2)),
                newArrayList(asList(next.threeKindRank, next.otherRank1, next.otherRank2))
        );
    }

    @Override
    public String toString() {
        return "ThreeKindRank{" +
                "threeKindRank=" + threeKindRank +
                ", otherRank1=" + otherRank1 +
                ", otherRank2=" + otherRank2 +
                '}';
    }
}
