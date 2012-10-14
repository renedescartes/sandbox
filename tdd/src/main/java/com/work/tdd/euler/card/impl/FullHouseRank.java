package com.work.tdd.euler.card.impl;

import com.work.tdd.euler.card.HandRank;
import com.work.tdd.euler.card.Rank;
import org.apache.commons.lang.builder.CompareToBuilder;

import static com.google.common.base.Preconditions.checkNotNull;

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
        return new CompareToBuilder().append(threeKindRank, next.threeKindRank).
                append(twoKindRank, next.twoKindRank).toComparison();
    }
}
